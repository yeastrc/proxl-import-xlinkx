package org.yeastrc.proxl.xml.xlinkx.database;

import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsmDAO {

    public static PsmDTO getPsmDTOFromResultSet(ResultSet rs) throws SQLException {

        PsmDTOBuilder builder = new PsmDTOBuilder();

        builder.setId( rs.getInt("ID") );

        builder.setSequenceA( rs.getString( "SequenceA" ));
        builder.setPeptidePositionA( rs.getInt( "PeptidePositionA" ));
        builder.setNumberMatchesA( rs.getInt( "NumberMatchesA" ));
        builder.setPtmScoreA( rs.getBigDecimal( "PtmScoreA" ));
        builder.setSequenceCoverageA( rs.getBigDecimal( "SequenceCoverageA" ));
        builder.setModificationConnectionsA( rs.getString( "ModificationsConnectionA" ));

        builder.setSequenceB( rs.getString( "SequenceB" ));
        builder.setPeptidePositionB( rs.getInt( "PeptidePositionB" ));
        builder.setNumberMatchesB( rs.getInt( "NumberMatchesB" ));
        builder.setPtmScoreB( rs.getBigDecimal( "PtmScoreB" ));
        builder.setSequenceCoverageB( rs.getBigDecimal( "SequenceCoverageB" ));
        builder.setModificationConnectionsB( rs.getString( "ModificationsConnectionB" ));

        builder.setSpectrumFilePath( rs.getString( "SpectrumFilePath"));
        builder.setCrosslinkReagent( rs.getString( "CrosslinkReagent"));
        builder.setScanNumber( rs.getInt( "ScanNumber"));
        builder.setRetentionTime( rs.getBigDecimal( "RetentionTime" ));
        builder.setPrecursorMz( rs.getBigDecimal( "PrecursorMz" ));
        builder.setPrecursorChargeState( rs.getInt( "PrecursorChargeState" ));

        builder.setReporterScore( rs.getBigDecimal( "ReporterScore" ));
        builder.setScore( rs.getBigDecimal( "Score" ));
        builder.setScoreDifference( rs.getBigDecimal( "ScoreDifference" ));
        builder.setDeltaMass( rs.getBigDecimal( "DeltaMass" ));

        builder.setFractionIonsMatchedBSeries( rs.getBigDecimal( "FractionIonsMatchedBseries" ));
        builder.setFractionIonsMatchedYSeries( rs.getBigDecimal( "FractionIonsMatchedYseries" ));
        builder.setFragmentationEfficiency( rs.getBigDecimal( "FragmentationEfficiency" ));

        // check for percolator scores
        try {
            builder.setPercolatorSVMScore( rs.getBigDecimal( "PercolatorSVMScore"));
            builder.setPercolatorPValue( rs.getBigDecimal( "PercolatorPvalue"));
            builder.setPercolatorQValue( rs.getBigDecimal( "PercolatorQvalue"));
            builder.setPercolatorPEP( rs.getBigDecimal( "PercolatorPEP"));

        } catch(Exception e) { ; }

        // check for validator q value
        try {
            builder.setValidatorQValue( rs.getBigDecimal( "ValidatorQvalue"));
        } catch(Exception e) { ; }


        return builder.createPsmDTO();
    }

    public static PsmDTO getPsmDTOFromId(int psmId, Connection dataConnection) throws Exception {

        String sql = "SELECT * FROM " + DBConstants.TBL_MS2_PSM_TABLE + " WHERE ID = ?";

        PreparedStatement pstmt = null;

        try {
            pstmt = dataConnection.prepareStatement(sql);
            pstmt.setInt(1, psmId);

            ResultSet rs = pstmt.executeQuery();

            if( !rs.next() ) {
                throw new Exception("Couldn't find cross-link PSM for id: " + psmId );
            }

            return getPsmDTOFromResultSet(rs);

        } finally {
            try {
                pstmt.close();
            } catch( Throwable t) { ; }
        }

    }

}
