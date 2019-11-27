/*
 * Original author: Michael Riffle <mriffle .at. uw.edu>
 *                  
 * Copyright 2018 University of Washington - Seattle, WA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yeastrc.proxl.xml.xlinkx.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.yeastrc.proxl.xml.xlinkx.constants.Constants;

import picocli.CommandLine;

@CommandLine.Command(name = "java -jar " + Constants.CONVERSION_PROGRAM_NAME,
		mixinStandardHelpOptions = true,
		version = Constants.CONVERSION_PROGRAM_NAME + " " + Constants.CONVERSION_PROGRAM_VERSION,
		sortOptions = false,
		synopsisHeading = "%n",
		descriptionHeading = "%n@|bold,underline Description:|@%n%n",
		optionListHeading = "%n@|bold,underline Options:|@%n",
		description = "Convert the results of a Proteome Discoverer XlinkX analysis to a ProXL XML file suitable for import into ProXL.\n\n" +
				"More info at: " + Constants.CONVERSION_PROGRAM_URI
)
public class MainProgram implements Runnable {

	@CommandLine.Option(names = { "-d", "--datafile" }, required = true, description = "Full path to the .msf file created by Proteome Discoverer.")
	private File dataFile;

	@CommandLine.Option(names = { "-f", "--fasta-file" }, required = true, description = "Full path to FASTA file used in the experiment.")
	private File fastaFile;

	@CommandLine.Option(names = { "-c", "--conf" },  required = true, description = "The full path to the configuration file used in the search. Has the file extension .pdStudy")
	private File confFile;

	@CommandLine.Option(names = { "-o", "--out-file" }, required = true, description = "Full path to use for the ProXL XML output file (including file name).")
	private File outFile;

	@CommandLine.Option(names = { "-v", "--verbose" }, required = false, description = "If this flag is present, the full stack trace from any errors will be printed. Helpful for problem solving.")
	private Boolean verboseRequested = false;


	public void run()  {

		printRuntimeInfo();

		if( !dataFile.exists() ) {
        	System.err.println( "The file: " + dataFile.getAbsolutePath() + " does not exist." );
        	System.exit( 1 );
        }
        
        if( !dataFile.canRead() ) {
        	System.err.println( "Can not read file: " + dataFile.getAbsolutePath() );
        	System.exit( 1 );
        }
        
        
        /*
         * Parse the conf files options
         */
        if( !confFile.exists() ) {
        	System.err.println( "The conf file: " + confFile.getAbsolutePath() + " does not exist." );
        	System.exit( 1 );
        }
        
        if( !confFile.canRead() ) {
        	System.err.println( "Can not read conf file: " + confFile.getAbsolutePath() );
        	System.exit( 1 );
        }
        
        /*
         * Parse the fasta file option
         */

        if( !fastaFile.exists() ) {
        	System.err.println( "The fasta file: " + fastaFile.getAbsolutePath() + " does not exist." );
        	System.exit( 1 );
        }
        
        if( !fastaFile.canRead() ) {
        	System.err.println( "Can not read fasta file: " + fastaFile.getAbsolutePath() );
        	System.exit( 1 );
        }
        
        // get the user supplied linker name

        System.err.println( "Converting XlinkX to ProXL XML with the following parameters:" );
        System.err.println( "\t.msf file path: " + dataFile.getAbsolutePath() );
        System.err.println( "\toutput file path: " + outFile.getAbsolutePath() );
        System.err.println( "\tfasta file path: " + fastaFile.getAbsolutePath() );
        System.err.println( "\tconf file path: " + confFile.getAbsolutePath() );

        /*
         * Run the conversion
         */
        try {
			(new ConverterRunner()).convertSearch( dataFile, outFile, fastaFile, confFile);

			System.err.println("Done.");
			System.exit(0);
		} catch( Throwable t ) {

        	System.out.println( "\nError encountered:" );
        	System.out.println( t.getMessage() );
        	System.exit( 1 );

		}
	}

	public static void main( String[] args ) {

		CommandLine.run(new MainProgram(), args);

	}

	/**
	 * Print runtime info to STD ERR
	 * @throws Exception
	 */
	public void printRuntimeInfo() {

		try( BufferedReader br = new BufferedReader( new InputStreamReader( MainProgram.class.getResourceAsStream( "run.txt" ) ) ) ) {

			String line = null;
			while ( ( line = br.readLine() ) != null ) {

				line = line.replace( "{{URL}}", Constants.CONVERSION_PROGRAM_URI );
				line = line.replace( "{{VERSION}}", Constants.CONVERSION_PROGRAM_VERSION );

				System.err.println( line );

			}

			System.err.println( "" );

		} catch ( Exception e ) {
			System.out.println( "Error printing runtime information." );
		}
	}
}
