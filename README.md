# KnowledgeBase is a simple example using SpringBoot and PostgreSQL to implement a data service.
Required software and libraries:
  - JDK (Open JDK 11)
  - PostgreSQL 12
  - Apache Maven 3
  - SpringBoot 2
  - Hibernate 4

To set up PostgreSQL database:
  to initial setup and configuration, refer to http://jldevtest.blogspot.com/2018/10/set-up-postgressql-database.html for the following:
  - createdb <database name>
  - createuser -s <user name>
  - psql -h <server name> -d <database name>
  to create database objects, run src/main/resources/dbscripts.sql
  
To build and run the application:
  mvn spring-boot:run

To test the standalone application (e.g. using curl.exe):
  - search all articles: curl -X GET http://localhost:8080/articles/
  - search articles by category (e.g. Test): curl -X GET http://localhost:8080/articles/Test
  - add an article: curl -X POST -H "Content-Type: application/json" -d "{"""title""":"""2nd Article for testing purpose""","""category""":"""Test""","""description""":"""This is a test record added from SpringBoot RestController again."""}" http://localhost:8080/articles/add
    or curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"2nd Article for testing purpose\",\"category\":\"Test\",\"description\":\"This is a test record added from SpringBoot RestController again.\"}" http://localhost:8080/articles/add
  - delete an article (e.g. by id 10008): curl -X DELETE http://localhost:8080/articles/10000

To test the application WAR deployed to other containers (eg Tomcat Wildfly):
  - search all articles: curl -X GET http://localhost:8080/KnowledgeBase/articles/
  - add an article: curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"3rd Article for testing purpose\",\"category\":\"Test\",\"description\":\"This is a test record added from SpringBoot WAR file deployment.\"}" http://localhost:8080/KnowledgeBase/articles/add
  - delete an article (e.g. by id 10001): curl -X DELETE http://localhost:8080/KnowledgeBase/articles/10001
    
