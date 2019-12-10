package org.yeastrc.proxl.xml.xlinkx.reader;

import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;
import org.yeastrc.proxl.xml.xlinkx.database.PsmDTO;
import org.yeastrc.proxl.xml.xlinkx.objects.XlinkXReportedPeptide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class XlinkxResultsLoader {

    public XlinkxResultsLoader createInstance() { return new XlinkxResultsLoader(); }

    public Map<XlinkXReportedPeptide, Collection<PsmDTO>> getResults(Connection dataConnection) throws SQLException {

        Map<XlinkXReportedPeptide, Collection<PsmDTO>> results = new HashMap<>();
        String sql = "SELECT * FROM " + DBConstants.TBL_MS2_PSM_TABLE;

        try(PreparedStatement pstmt = dataConnection.prepareStatement( sql ) ) {
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

            }
        }

        return results;
    }


}
