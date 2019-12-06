package org.yeastrc.proxl.xml.xlinkx.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;
import org.yeastrc.proxl.xml.xlinkx.objects.Linker;
import org.yeastrc.proxl.xml.xlinkx.objects.LinkerBuilder;
import org.yeastrc.proxl.xml.xlinkx.objects.LinkerEnd;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkerLoader {

    public static Linker loadLinker(Connection dataConnection) throws Exception {

        String linkerXML = getLinkerXMLStringFromDataFile(dataConnection);
        LinkerBuilder linkerBuilder = new LinkerBuilder();

        /*
            Example XML:

            <labile_crosslinker xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" title="DSS" description="" number_of_cleavages="0" composition="H(10) C(8) O(2)">
              <modification_site site="K">
                <neutralloss_collection />
                <diagnostic_collection />
              </modification_site>
              <aa_linkage_map />
            </labile_crosslinker>
         */

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(linkerXML));
        Document document = docBuilder.parse(is);

        NodeList nodeList = document.getElementsByTagName("labile_crosslinker");
        Node linkerNode = nodeList.item(0);   // assume there is only one crosslinker defined

        String linkerName = linkerNode.getAttributes().getNamedItem("title").getNodeValue();

        Byte cleavageNumber = Byte.valueOf(linkerNode.getAttributes().getNamedItem("number_of_cleavages").getNodeValue());
        boolean cleavable = cleavageNumber < 1 ? false : true;

        String formula = getFormula(linkerNode.getAttributes().getNamedItem("composition").getNodeValue());

        Collection<Double> linkerMasses = new ArrayList<>(1);
        linkerMasses.add( ModUtils.getModMassForModName(linkerName, dataConnection).doubleValue() );

        linkerBuilder.setCrosslinkMasses(linkerMasses);
        linkerBuilder.setFormula(formula);
        linkerBuilder.setIsCleavable(cleavable);
        linkerBuilder.setName(linkerName);
        linkerBuilder.setLinkerEnds(getLinkerEnds(linkerNode, dataConnection));

        Linker linker = linkerBuilder.createLinker();
        System.out.println(linker);

        return linker;
    }

    /**
     * Get the linkable ends of this cross-linker.
     *
     * In my testing, it looks as though XlinkX only defines a single linkable end--that is both
     * ends of the cross-linker have exactly the same reactivity.
     *
     * TODO: Need to test this wit a c-terminal linker to confirm c-terminal is not an option
     *
     * @param linkerNode
     * @return
     */
    public static List<LinkerEnd> getLinkerEnds(Node linkerNode, Connection dataConnection) throws Exception {

        List<LinkerEnd> linkerEnds = new ArrayList<>();
        Collection<String> linkedResidues = new ArrayList<>(1);

        boolean nTermLinkable = linkerLinksNTerm(dataConnection);

        NodeList nodeList = linkerNode.getChildNodes();
        for( int i = 0; i < nodeList.getLength(); i++ ) {
            Node childNode = nodeList.item(i);
            if( childNode.getNodeName().equals( "modification_site" ) ) {
                if(childNode.getAttributes().getNamedItem("site") != null)
                    linkedResidues.add(childNode.getAttributes().getNamedItem("site").getNodeValue());
            }
        }

        linkerEnds.add(new LinkerEnd(linkedResidues, nTermLinkable, false));

        return linkerEnds;
    }

    public static boolean linkerLinksNTerm(Connection dataConnection) throws Exception {

        List<Element> nodes = WorkflowUtils.getWorkflowNodes( "HlxlDetect", dataConnection );
        if( nodes.size() < 1 ) {
            throw new Exception("Did not find a node corresponding to HlxlDetect...");
        }
        if( nodes.size() > 1) {
            throw new Exception("Only expected one node corresponding to HlxlDetect...");
        }

        return getIsNTermInfoFromXMLProcessingNode(nodes.get(0));
    }

    public static boolean getIsNTermInfoFromXMLProcessingNode(Node processingNode) throws Exception {

        NodeList nodeList = ((Element)(processingNode)).getElementsByTagName("ProcessingNodeParameter");
        for( int i = 0; i < nodeList.getLength(); i++ ) {
            Node subNode = nodeList.item(i);
            if(subNode.getAttributes().getNamedItem("Name") != null &&
                subNode.getAttributes().getNamedItem("Name").getNodeValue().equals( "EnableProteinNterm" )) {

                String val = subNode.getTextContent();
                if( val.equals( "True" )) {
                    return true;
                }

                return false;
            }
        }

        throw new Exception("Unable to determine if linker is linking n-terminus.");
    }


    public static String getFormula(String messyFormula) throws Exception {
        Pattern p = Pattern.compile("^(\\w+)\\((.+)\\)$");

        String[] atomTypes = messyFormula.split(" " );
        String formula = "";

        for(String atomType : atomTypes) {
            Matcher m = p.matcher(atomType);
            if( m.matches() ) {
                formula += m.group(1) + Integer.parseInt(m.group(2));
            } else {
                formula += atomType;
            }
        }

        return formula;
    }

    public static String getLinkerXMLStringFromDataFile(Connection dataConnection) throws Exception {

        String sql = "SELECT CrosslinkerDefinition FROM " + DBConstants.TBL_XLINKX_WORKFLOWS;
        String xml = null;

        try(PreparedStatement pstmt = dataConnection.prepareStatement( sql ) ) {
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            xml = rs.getString(1);

            if(rs.next()) {
                throw new Exception("Got more than one row in " + DBConstants.TBL_XLINKX_WORKFLOWS + ". Only expecting one.");
            }
        }

        return xml;
    }

}
