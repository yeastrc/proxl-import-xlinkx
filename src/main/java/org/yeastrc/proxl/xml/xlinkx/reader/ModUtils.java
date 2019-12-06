package org.yeastrc.proxl.xml.xlinkx.reader;

import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModUtils {

    public static BigDecimal getModMassForModName(String name, Connection dataConnection) throws Exception {

        String sql = "SELECT DeltaMonoisotopicMass FROM " + DBConstants.TBL_MOD_LOOKUP_TABLE + " WHERE Name = ?";
        BigDecimal mass = null;

        PreparedStatement pstmt = null;

        try {
            pstmt = dataConnection.prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            if( !rs.next() ) {
                throw new Exception("Couldn't find mass for linker: " + name );
            }

            mass = new BigDecimal( rs.getString(1) );
        } finally {
            try {
                pstmt.close();
            } catch( Throwable t) { ; }
        }

        return mass;
    }

}
