package org.yeastrc.proxl.xml.xlinkx.main;

import org.yeastrc.proxl.xml.xlinkx.objects.Linker;
import org.yeastrc.proxl.xml.xlinkx.reader.DataConnectionFactory;
import org.yeastrc.proxl.xml.xlinkx.reader.LinkerLoader;

import java.io.File;
import java.sql.Connection;

public class ConverterRunner {

    public void convertSearch(File dataFile, File outputFile, File fastaFile, File confFile) throws Exception {

        // get a connection to the sqlite data file
        Connection dataConnection = DataConnectionFactory.getConnectionToDataFile(dataFile);

        // load the linker
        Linker linker = LinkerLoader.loadLinker( dataConnection );
        System.out.println("\tLinker: " + linker);



//		XMLBuilder builder = new XMLBuilder();
//		builder.buildAndSaveXML(analysis, outFile );
    }

}
