package org.yeastrc.proxl.xml.xlinkx.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.yeastrc.proxl.xml.xlinkx.reader.LinkerLoader.getIsNTermInfoFromXMLProcessingNode;

public class WorkflowUtils {


    public static String getWorkflowXML(Connection dataConnection) throws Exception {

        String sql = "SELECT WorkflowXML FROM " + DBConstants.TBL_WORKFLOWS;
        String xml = null;

        try(PreparedStatement pstmt = dataConnection.prepareStatement( sql ) ) {
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            xml = rs.getString(1);

            if(rs.next()) {
                throw new Exception("Got more than one row in " + DBConstants.TBL_WORKFLOWS + ". Only expecting one.");
            }
        }

        return xml;
    }

    public static List<Element> getWorkflowNodes(String processingNodeName, Connection dataConnection) throws Exception {
        List<Element> nodes = new ArrayList<>();

        String xml = getWorkflowXML(dataConnection);

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document document = docBuilder.parse(is);

        NodeList nodeList = document.getElementsByTagName("WorkflowNode");
        for( int i = 0; i < nodeList.getLength(); i++ ) {
            Node node = nodeList.item(i);
            if(node.getAttributes().getNamedItem("ProcessingNodeName") != null &&
                    node.getAttributes().getNamedItem("ProcessingNodeName").getNodeValue().equals( processingNodeName )) {
                nodes.add((Element)node);
            }
        }

        return nodes;
    }

}
