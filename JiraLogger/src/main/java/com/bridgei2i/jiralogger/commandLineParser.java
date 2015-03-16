package com.bridgei2i.jiralogger;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class commandLineParser {
 private static final Logger log = Logger.getLogger(commandLineParser.class.getName());
 private String[] args = null;
 private Options options = new Options();
 private int mode;
 private String filePath;
 private String jiraUrl;
 private String jiraUname;
 private String jiraPassword;
 private String jiraFilter;
 private String outFilePath;

 public commandLineParser(String[] arg) {

  this.args = arg;

  options.addOption("F", "file", true, "File Path");
  options.addOption("U", "URL", true, "Jira URL");
  options.addOption("N", "Jira User Name", true, "Jira User Name");
  options.addOption("P", "Jira Password", true, "Jira Password");
  options.addOption("Q", "Jira Query", true, "Jira JQL Query");
  options.addOption("O", "Output File Path", true, "Output File Path");
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

   if (cmd.hasOption("F")){
	   filePath=cmd.getOptionValue("F");
	   mode=1;
   }else{

	   if (cmd.hasOption("U")) {
	    jiraUrl=cmd.getOptionValue("U");
	   } else {
	    log.log(Level.SEVERE, "Cmd Option 'U' is Missing");
	    help();
	   }
	   
	   if (cmd.hasOption("N")) {
		    jiraUname=cmd.getOptionValue("N");
		   } else {
		    log.log(Level.SEVERE, "Cmd Option 'N' is Missing");
		    help();
		   }
	   
	   if (cmd.hasOption("P")) {
		    jiraPassword=cmd.getOptionValue("P");
		   } else {
		    log.log(Level.SEVERE, "Cmd Option 'P' is Missing");
		    help();
		   }
	   
	   if (cmd.hasOption("Q")) {
		    jiraFilter=cmd.getOptionValue("Q");
		   } else {
		    log.log(Level.SEVERE, "Cmd Option 'Q' is Missing");
		    help();
		   }
	   
	   if (cmd.hasOption("O")) {
		    outFilePath=cmd.getOptionValue("O");
		   } else {
		    log.log(Level.SEVERE, "Cmd Option 'O' is Missing");
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
