
CREATE THE ES CONTAINER
--------------------------------------------------------------------------------------------

#) Open in command line at the root of the project and run :
mvn clean package -DskipTests

#) Then create the containers as follow:
docker-compose up -d
#) run the service from intelliJ

POSTMAN
------------------------------------------------------------------------------------------
#) Please download the postman collection from the resoucres folder


BROWSER
------------------------------------------------------------------------------------------
#) Open the browser on the following url:
http://localhost:9200/users/_search?pretty=true





OPEN UI IN BROWSER
-------------------------------------------------------------------------------------------------------------
#) Open the browser in the following url:
http://localhost:8080/



--------------------------------------------------------------------------------------------------

# HOW TO ADD NEW USER USING CURL

curl --location "http://127.0.0.1:8080/api/users" --header "Content-Type: application/json" --data "{\"firstName\": \"Yossi\", \"lastName\": \"Tal\"}"

curl --location "http://127.0.0.1:8080/api/users" --header "Content-Type: application/json" --data "{\"firstName\": \"Dani\", \"lastName\": \"Cohen\"}"

--------------------------------------------------------------------------------------------------

# HOW TO GET EXIST USER USING CURL

curl --location "http://127.0.0.1:8080/api/users/3"



--------------------------------------------------------------------------------------------------
