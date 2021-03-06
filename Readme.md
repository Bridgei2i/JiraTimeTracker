Jira Time Tracker
===================

	A command line tool that fetches time logged against all JIRA tickets and dumps the 
	output into a CSV file.
	
	
Description
------------
	
	Let's say one of your teams is logging time against JIRA tasks, and other teams are logging 
	time in other tools, and you wish to display the time details from all teams. 
	This tool will extract the time logged against all JIRA tasks from a given set of projects 
	and dump the output as a CSV. The data is in the form of a 4-tuple, {username, date, project, hours}
    

Inputs
------------
    This application need 5 inputs from the user to extract Jira logs and create CSV file. which are:
         UserName 	- Jira UserName
		 Password	- Jira Password
		 URL		- Jira Host URL
		 Query 		- Jira JQL Query
		 Filepath	- Path where you want to store the output file(File name and extension has to be explicitly mentioned)
		 
Output
----------
    This application will create a file in <Filepath> location.

     Sample Output:
     ---------------------	
                   The output file will be exported in the following structure.

                  User                       Date                            Project                     Hours Spent
               -----------                 -------------                  ----------------               ----------------------
             Sample User1                   2015-01-01                     Project 1                         8
             Sample User1                   2015-01-02                     Project 1                         2
             Sample User1                   2015-01-02                     Project 2                         3
             Sample User1                   2015-01-03                     Project 1                         7
             Sample User2                   2015-01-01                     Project 1                         4
                      ...                        ...                             ...                         ...
Usage
----------
    Inputs has to be send as a command line arguments. which you can send in 2 methods :
	Method 1: (Sending all inputs in commands)
	------
		 Using Command line options you can send the inputs to the application.
		 
		-n,		= 	Jira User Name
		-p,		=	Jira Password
		-q,		=	Jira JQL Query
		-u,		=	Jira Host URL
		-o,		=	Output File Path

		 Example: java -jar myJARFile.jar -u "http://sample.atlassian.net" -n "user@google.com" -p "abcdefghij"
						-q "project in ('project1','project2') AND created >= -30d  ORDER BY created DESC" -o "E:\\name.csv"
						
	Method 2: (Sending inputs from property file)
	-------------
		In this method you can send the inputs from a flat file to the application.
		The file should contain values for the 5 properties. Following is the Sample.

			USERNAME = sampleuser@google.com                    										//Jira User Name
			PASSWORD = abcdefghij																		//Jira Password
			URL = http://sample.atlassian.net															//Jira Host URL
			FILEPATH=E:\\JiraLogs.csv																	//Output File Path
			QUERY = project in ('project1','project2') AND created >= -30d  ORDER BY created DESC      //Jira JQL Query

		Now you have to send the flat file to the application by using -f option.
		
		 Example: java -jar myJARFile.jar -f "E:\\properties.txt"
		 

License
---------------------

Copyright © 2015 Bridgei2i Analytics Pvt Ltd.

Released under the Eclipse license.
