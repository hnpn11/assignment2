How to run

1. Setup environmet variable
- Need to setup 3 variables are:
  - database: Name of database
  - database_username: Username to login database
  - database_password: Password to login database

2. Run command to compile and package jar file
- mvn clean install

3. RUn command to run application
- java -jar ./target/namhnp-assignment1-0.0.1-SNAPSHOT.jar

4. List API
4.1. API to register user
- path: /api/v1/user/register
- body: 
  - type: json
  	{
	  "username"  : "<username>",
	  "userrpass" : "<password>"
	}
- reponse: 
  - type: json
	   {
	     "username" : "<username>",
	     "userID"	: "<userID>"
	   }

4.2. API to login
- path: /api/v1/user/login
- body: 
  - type: json	
	{
          "username"  : "<username>",
          "userrpass" : "<password>"
        }
- response: {
	      "username" : "<username>"
	      "token" 	 : "<token>"	
	    }

4.3. API to upload file
- path: /api/v1/file/upload
- body:
  - type: form-data
  - data: 
  	- key: file
	- value: <path to file>
- response: {
	      "fileName" : "<fileName>",
	      "userName" : "<username>",
	      "fileSize" : "<fileSize>"
	    }

4.4. API to download file
- path: /api/v1/file/download
- body:
  - type: json
  - data: {
  	    "fileName":"<fileName>"
  	  }
- reponse: COntent of file
