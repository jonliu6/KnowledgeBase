# KnowledgeBase is a simple example using SpringBoot and PostgreSQL to implement a data service.
Required software and libraries:
  - JDK (Open JDK 11)
  - PostgreSQL 12
  - Apache Maven 3
  - SpringBoot 2
  - Hibernate 4
  
To build and run the application:
  mvn spring-boot:run

To test the application (e.g. using curl.exe):
  - search all articles: curl -X GET http://localhost:8080/articles/
  - search articles by category (e.g. Test): curl -X GET http://localhost:8080/articles/Test
  - add an article: curl -X POST -H "Content-Type: application/json" -d "{"""title""":"""2nd Article for testing purpose""","""category""":"""Test""","""description""":"""This is a test record added from SpringBoot RestController again."""}" http://localhost:8080/articles/add
  - delete an article (e.g. by id 10008): curl -X DELETE http://localhost:8080/articles/10008