/**
 * Copyright © 2015 Bridgei2i Analytics Pvt Ltd - All rights reserved.
 */
package com.bridgei2i.jiralogger;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
/**
 * @author      Saddam Hussain
 * @version     0.1 
 * @since       2015-03-01
 * This class is used to parse the command line arguments.
 */
public class CommandParser {
	private static final Logger log = Logger.getLogger(CommandParser.class.getName());
	private String[] args = null;
	private Options options = new Options();
	private int mode;
	private String filePath;
	private String jiraUrl;
	private String jiraUname;
	private String jiraPassword;
	private String jiraFilter;
	private String outFilePath;
	
	public CommandParser(String[] arg) {
		this.args = arg;
		options.addOption("f", "file", true, "File Path");
		options.addOption("u", "URL", true, "Jira URL");
		options.addOption("n", "Jira User Name", true, "Jira User Name");
		options.addOption("p", "Jira Password", true, "Jira Password");
		options.addOption("q", "Jira Query", true, "Jira JQL Query");
		options.addOption("o", "Output File Path", true, "Output File Path");
		parse();
	}
 
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getJiraUrl() {
		return jiraUrl;
	}
	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}
	public String getJiraUname() {
		return jiraUname;
	}
	public void setJiraUname(String jiraUname) {
		this.jiraUname = jiraUname;
	}
	public String getJiraPassword() {
		return jiraPassword;
	}
	public void setJiraPassword(String jiraPassword) {
		this.jiraPassword = jiraPassword;
	}
	public String getJiraFilter() {
		return jiraFilter;
	}
	public void setJiraFilter(String jiraFilter) {
		this.jiraFilter = jiraFilter;
	}
	public String getOutFilePath() {
		return outFilePath;
	}
	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public void parse() {
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		  try {
		   cmd = parser.parse(options, args);
		   if (cmd.hasOption("f")){
			   filePath=cmd.getOptionValue("f");
			   mode=1;
		   }else{
			   if (cmd.hasOption("u")) {
				   jiraUrl=cmd.getOptionValue("u");
			   } else {
				   log.log(Level.SEVERE, "Cmd Option 'u' is Missing");
				   help();
			   }
			   if (cmd.hasOption("n")) {
				   jiraUname=cmd.getOptionValue("n");
			   } else {
				   log.log(Level.SEVERE, "Cmd Option 'n' is Missing");
				   help();
			   }
			   if (cmd.hasOption("p")) {
				    jiraPassword=cmd.getOptionValue("p");
			   } else {
				    log.log(Level.SEVERE, "Cmd Option 'p' is Missing");
				    help();
			   }
			   if (cmd.hasOption("q")) {
				    jiraFilter=cmd.getOptionValue("q");
			   } else {
				    log.log(Level.SEVERE, "Cmd Option 'q' is Missing");
				    help();
			   }
			   if (cmd.hasOption("o")) {
				    outFilePath=cmd.getOptionValue("o");
			   } else {
				    log.log(Level.SEVERE, "Cmd Option 'o' is Missing");
				    help();
			   }
		   }
		  } catch (ParseException e) {
			  log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			  help();
		  }
	 }
	 private void help() {
		 // This prints out some help
		 HelpFormatter formater = new HelpFormatter();
		 formater.printHelp("Main", options);
		 System.exit(0);
	 }
}
