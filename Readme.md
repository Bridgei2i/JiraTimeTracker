Jira Time Tracker
===================

	A Jira Rest Client to fetch log deatils from Jira and create a CSV file.
	
	
Description
------------
    A Jira rest client implemented in java to fetch work log details from Jira based on the JQL Query passed as a parameter which will create a CSV file with log details.

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