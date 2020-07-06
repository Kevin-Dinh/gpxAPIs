Steps to start and use the project:
1. System requirements:
    - Maven
    - Postgrsql
    - Springtboot 2.1.7
2. Run mvn clean compile
3. Make application.properties file from the template and add database connection into it. 
3. This project has 3 APIs:
    - An endpoint that allow users to upload "gpx" file and store mandatory information such as "metadata, waypoint, track" 
    - An endpoint to return a list of "Latest track" from our users (This is incomplete)
    - An endpoint to allow users to view details of their gpx file
    
Notes:
    - Unit test is under development