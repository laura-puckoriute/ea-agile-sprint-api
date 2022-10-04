# API

Set your environment variables
---
1. Click on 'Edit Configurations' under your Run/Debug Configuration
2. In the application run configuration, where it says 'Environment variables' paste in the following:
   USER=NameS;PASSWORD=password;DATABASE=ea_agile_sprint_NameS;HOST=academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com
3. Change the variables to match yours where needed
4. Click Apply and OK

How to start the API application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/ea-agile-sprint-api-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

How to run unit and integration tests
---
1. To run class unit/integration tests simply navigate to the test file you want to run and click on the green arrow beside the class name
2. To run all the tests in the project, right-click on the java directory within the test directory and click 'Run all tests'

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
