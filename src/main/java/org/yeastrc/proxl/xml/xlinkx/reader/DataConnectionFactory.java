package org.yeastrc.proxl.xml.xlinkx.reader;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Get a JDBC connection to the search results sqlite file
 */
public class DataConnectionFactory {

    /**
     * Get a JDBC connection object to the given sqlite file
     *
     * @param dataFile
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionToDataFile(File dataFile) throws SQLException {

        Connection conn = null;

        try {

            String url = "jdbc:sqlite:" + dataFile.getAbsolutePath();
            conn = DriverManager.getConnection(url);

        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return conn;
    }
}
