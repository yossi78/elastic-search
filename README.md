
CREATE THE ES CONTAINER
--------------------------------------------------------------------------------------------

#) Open in command line at the root of the project and run :
mvn clean package -DskipTests

#) Then create the containers as follow:
docker-compose up -d


POSTMAN
------------------------------------------------------------------------------------------
#) Please download the postman collection from the resoucres folder


BROWSER
------------------------------------------------------------------------------------------
#) Open the browser on the following url:
http://localhost:9200/users/_search?pretty=true

