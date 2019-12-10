package org.yeastrc.proxl.xml.xlinkx.reader;

import org.yeastrc.proxl.xml.xlinkx.constants.DBConstants;
import org.yeastrc.proxl.xml.xlinkx.objects.XlinkXDynamicMod;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ModDBLookupUtils {

    /*
        Singleton
     */
    private static ModDBLookupUtils _INSTANCE = new ModDBLookupUtils();
    private ModDBLookupUtils() { };
    public static ModDBLookupUtils getInstance() { return _INSTANCE; }


    public XlinkXDynamicMod getModForId(int modId, Connection dataConnection, String linkerName) throws Exception {

        if(this.idCache.containsKey(modId)) {
            return this.idCache.get(modId);
        }

        String sql = "SELECT Name, DeltaMonoisotopicMass FROM " + DBConstants.TBL_MOD_LOOKUP_TABLE + " WHERE ModificationID = ?";

        PreparedStatement pstmt = null;

        try {
            pstmt = dataConnection.prepareStatement(sql);
            pstmt.setInt(1, modId);

            ResultSet rs = pstmt.executeQuery();

            if( !rs.next() ) {
                throw new Exception("Couldn't find mod for id: " + modId );
            }

            BigDecimal modMass = new BigDecimal( rs.getString("DeltaMonoisotopicMass"));
            String name = rs.getString("Name");
            boolean isMonolink = name.startsWith(linkerName + " ");

            this.idCache.put(modId, new XlinkXDynamicMod(modMass, isMonolink));

        } finally {
            try {
                pstmt.close();
            } catch( Throwable t) { ; }
        }

        return this.idCache.get(modId);
    }

    public XlinkXDynamicMod getModForName(String modName, Connection dataConnection, String linkerName) throws Exception {

        if(this.nameCache.containsKey(modName)) {
            return this.nameCache.get(modName);
        }

        String sql = "SELECT Name, DeltaMonoisotopicMass FROM " + DBConstants.TBL_MOD_LOOKUP_TABLE + " WHERE Name = ?";

        PreparedStatement pstmt = null;

        try {
            pstmt = dataConnection.prepareStatement(sql);
            pstmt.setString(1, modName);

            ResultSet rs = pstmt.executeQuery();

            if( !rs.next() ) {
                throw new Exception("Couldn't find mod for name: " + modName );
            }

            BigDecimal modMass = new BigDecimal( rs.getString("DeltaMonoisotopicMass"));
            String name = rs.getString("Name");
            boolean isMonolink = name.startsWith(linkerName + " ");

            this.nameCache.put(modName, new XlinkXDynamicMod(modMass, isMonolink));

        } finally {
            try {
                pstmt.close();
            } catch( Throwable t) { ; }
        }

        return this.nameCache.get(modName);
    }

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

    private Map<Integer, XlinkXDynamicMod> idCache = new HashMap<>();
    private Map<String, XlinkXDynamicMod> nameCache = new HashMap<>();

}
