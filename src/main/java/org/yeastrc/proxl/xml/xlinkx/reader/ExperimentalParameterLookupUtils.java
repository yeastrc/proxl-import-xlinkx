package org.yeastrc.proxl.xml.xlinkx.reader;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExperimentalParameterLookupUtils {

    /**
     * Determine if percolator was used, return true if i was, false if otherwise
     *
     * @param dataConnection
     * @return
     * @throws SQLException
     */
    public static boolean wasPercolatorUsed(Connection dataConnection) throws Exception {

        List<Element> nodes = WorkflowUtils.getWorkflowNodes("HlxlValidator", dataConnection);
        if(nodes.size() < 1) {
            return false;
        }

        if(nodes.size() > 1) {
            throw new Exception("Found more than one HlxlValidator node... Don't know how to proceed.");
        }

        Element workflowNode = nodes.get(0);
        NodeList nodeList = workflowNode.getElementsByTagName("ProcessingNodeParameter");

        for( int i = 0; i < nodeList.getLength(); i++ ) {
            Node subNode = nodeList.item(i);
            if(subNode.getAttributes().getNamedItem("Name") != null &&
                    subNode.getAttributes().getNamedItem("Name").getNodeValue().equals( "FdrStrategy" )) {

                String val = subNode.getTextContent();
                if( val.equals( "Percolator" )) {
                    return true;
                }

                return false;
            }
        }

        return false;   // if no fdr strategy node is found, assume percolator was not used
    }

    /**
     * Return true if a HlxlValidator workflow node was part of the workflow.
     *
     * @param dataConnection
     * @return
     * @throws Exception
     */
    public static boolean wasValidationPerformed(Connection dataConnection) throws Exception {

        List<Element> nodes = WorkflowUtils.getWorkflowNodes("HlxlValidator", dataConnection);
        return nodes.size() >= 1;
    }


    /**
     * If percolator was used, find and return the version. Will return null if percolator was not
     * used or if the version cannot be found
     *
     * @param dataConnection
     * @return
     * @throws SQLException
     */
    public static String getPercolatorVersion(Connection dataConnection) throws SQLException {

        String sql = "SELECT Message FROM " + DBConstants.TBL_WORKFLOW_MESSAGES + " WHERE Message LIKE 'Percolator version%'";

        try(PreparedStatement pstmt = dataConnection.prepareStatement( sql ) ) {
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getString(1);
            }
        }

        return null;
    }

    /**
     * Get the version of Xlinkx used. Currently this is set to the version of proteome discoverer, I could not
     * find where the xlinkx version number could specifically be found.
     *
     * @param dataConnection
     * @return
     * @throws SQLException
     */
    public static String getXlinkxVersion(Connection dataConnection) throws SQLException {
        return getProteomeDiscovererVersion(dataConnection);
    }

    /**
     * Get the version of proteome discoverer that generated these data. Will return null if it cannot be found
     *
     * @param dataConnection
     * @return
     * @throws SQLException
     */
    public static String getProteomeDiscovererVersion(Connection dataConnection) throws SQLException {

        String sql = "SELECT SoftwareVersion FROM " + DBConstants.TBL_SCHEMA_INFO + " WHERE Kind = 'Result'";

        try(PreparedStatement pstmt = dataConnection.prepareStatement( sql ) ) {
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getString(1);
            }
        }

        return null;
    }

}
