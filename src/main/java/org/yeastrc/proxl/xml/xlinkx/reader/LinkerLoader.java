package org.yeastrc.proxl.xml.xlinkx.reader;

import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;
import org.yeastrc.proxl.xml.xlinkx.objects.Linker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LinkerLoader {

    public static Linker loadLinker(Connection dataConnection) throws Exception {

        String linkerXML = getLinkerXMLStringFromDataFile(dataConnection);
        System.out.println(linkerXML);

        return null;
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
