package org.yeastrc.proxl.xml.xlinkx.main;

import org.yeastrc.proxl.xml.xlinkx.reader.DataConnectionFactory;

import java.io.File;
import java.sql.Connection;

public class ConverterRunner {

    public void convertSearch(File dataFile, File outputFile, File fastaFile, File confFile) throws Exception {

        // get a connection to the sqlite data file
        Connection dataConnection = DataConnectionFactory.getConnectionToDataFile(dataFile);


//		XMLBuilder builder = new XMLBuilder();
//		builder.buildAndSaveXML(analysis, outFile );
    }

}
