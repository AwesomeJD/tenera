
# Assignment problem statement Link:
https://www.notion.so/Tenera-Backend-Coding-Challenge-f5a4c891e1094717b224d9d1ad268a88

# How to run the application:
This is a simple spring boot application. 
1. Check out from the gitrepo. 
2. Have lombok plugin in the IDE.
3. Do a ==> maven clean install
4. Run the main class `WeatherAppApplication`

## End points:
###1. For current weather:
   
      
    http://localhost:8080/current?location=Berlin

Response:

    {
      "temp": 4.37,
      "pressure": 1035,
      "umbrella": false
    }
###2. For History

    http://localhost:8080/history?location=Berlin

Response:

    {
      "avgTemp": 4.37,
      "avgPressure": 1035,
      "history": [
        {
          "temp": 4.37,
          "pressure": 1035,
          "umbrella": false
        },
        {
          "temp": 4.37,
          "pressure": 1035,
          "umbrella": false
        },
        {
          "temp": 4.37,
          "pressure": 1035,
          "umbrella": false
        },
        {
          "temp": 4.37,
          "pressure": 1035,
          "umbrella": false
        },
        {
          "temp": 4.37,
          "pressure": 1035,
          "umbrella": false
        }
    ]
    }

##Exceptions thrown
* Exception handling is done, using a framework. `ApplicationExceptionHandler` deals with
  all the exceptions thrown from application.

* Separate error codes and messages are defined for different scenarios.
* If the cityName is blank, the service throws the below exception.

        {
            "errors": [
                {
                    "code": "1002",
                    "message": "[Location cannot be blank.]",
                    "type": "BAD USER INPUT"
                }
            ]
        }
* If the history is not present the below exception is thrown.

        {
            "errors": [
                {
                    "code": "1004",
                    "message": "No weather history for the location provided",
                    "type": "Business Error"
                }
            ]
        }

# Approach for the solution:
##Solution approach
* Fire the Open weather API
  
        https://api.openweathermap.org/data/2.5/weather?q=Berlin&units=metric&appid=04671daf183697e1c2507572e488df70

    From this response, the required fields are used and the rest are ignored.

        {
          "weather":[
            {
              "id":803,
              "main":"Clouds",
              "description":"broken clouds"
            }
          ],
          "main":{
            "temp":3.58,
            "pressure":1035
          },
        "dt":1614568546,
          "sys":{
            "country":"DE"
          },
        "name":"Berlin"
        }

* The response before sending back to the client, is persisted in a H2 Database. 
* The H2 database is used to store the history of the API calls. 


                    ID  	CITY  	COUNTRY_CODE  	PRESSURE  	TEMP  	UMBRELLA  	UNIX_TIME_DATE  
                    1	Delhi	IN	1013	19.0	FALSE	1614542588
                    2	Berlin	DE	1035	4.42	FALSE	1614543587
                    3	Berlin	DE	1035	4.42	FALSE	1614543589
                    4	Berlin	DE	1035	4.42	FALSE	1614543590
                    5	Berlin	DE	1035	4.42	FALSE	1614543592
                    6	Berlin	DE	1035	4.42	FALSE	1614543593
                    7	Berlin	DE	1035	4.42	FALSE	1614543594
                    8	Berlin	DE	1035	4.42	FALSE	1614543595
                    9	Delhi	IN	1013	19.0	FALSE	1614543633
                    10	Delhi	IN	1013	19.0	FALSE	1614543635
                    11	Delhi	IN	1013	19.0	FALSE	1614543637
                    12	Delhi	IN	1013	19.0	FALSE	1614543639
                    13	Delhi	IN	1013	19.0	FALSE	1614543641

* The top 5 records for a city are returned to the client with the avg of temp and pressure.
* The various responsibilities are separated into layers. 
  
      Validation --> Controller --> Service layer --> DB Repository layer --> Rest Template Layer ...etc
  
* The properties can be used to customise a great of information without changing the code. 
  Ex: the weathers can be added for the rain check. The units of the measurement can be changed.
  
      service:
        open-weather:
          api-data:
            endpoint: https://api.openweathermap.org/data/2.5/weather
        appId: MDQ2NzFkYWYxODM2OTdlMWMyNTA3NTcyZTQ4OGRmNzA=
        units: metric
        umbrella:
          weathers:
            - Thunderstorm
            - Drizzle
            - Rain

* Unit tests have been used for all the layers except the controller layer.
* The controller layer is tested using the Integration tests(**TestRestTemplate**).

# Things that can be improved:
* The logging can be improved
* The Exception handling can be added more, by more testing the edge cases. 
* The **Clean-up** of the Database can be done using a **Scheduler**. This Scheduler can run 
in periodically and delete the records for each city from the table, that are older than the top 5 records.
  
* A Distributed cache could be used instead of a Database. That will add to the performance for sure.
* The API key for the open API, is a Base64 encoded string right now in the yaml file. This could be put into a vault, Ex Hashi vault.
* The places in the test cases where the json as string is used, can be put in files and read for input. The Json strings looks ugly and inconvenient.

  
# Changes to make this application a production ready code:
* A **persistent DB** like postgress or mysql or even MongoDB should be 
  used, instead of an in memory DB.
  
* The in-memory DB can be used for local testing and for some low level environments.
  
* Vault should be configured for storing the Open weather API key.
  
* Sonar configuration has to be added for the quality gate. In this the JUnits coverage, Integration tests coverage, the style checks can be checked as a hard stop.
 
* The **Security scan** step like the veracode  has to be added.

* A step for **Pushing** the image or the jar to the nexus has to be added. 
* SSL can be added to make it a secure application.
* Integration tests using **Apache Karate** should be written.
* Swagger can be used for the API documentation and upload the swagger file in a nexus. 
* the logging level for the hibernate needs to be disabled for the production level code. 
This will act as both performance enhancement and unnecessary use of resources. 

# High level view of deploying this application in AWS:

The deployment in AWS can be one in various ways, but the containerised one is what I feel is the best.
* CircleCI can be used for the CI/CD. The yaml config files can of the circleCI will be added in the code. 
The CircleCI can be configured to listen to the gitrepo. The build will be triggered for each push.
  
      References:
      https://www.youtube.com/watch?v=J1l-icYGyd0&ab_channel=CircleCI
      https://circleci.com/blog/learn-iac-part02/

* The image can be push to a private docker repository. 
* The Infrastructure can be provisioned in the AWS environment using Terraform.
The AWS env will need **VPC**, **security groups** and an **EKS cluster** . All these can be configured via terraform configurations.
  
      References:
      https://learn.hashicorp.com/tutorials/terraform/aws-build?in=terraform/aws-get-started
      https://learn.hashicorp.com/tutorials/terraform/eks

* Once the resources are ready, the deployment in the EKS cluster can be done. 

      References:
      https://learn.hashicorp.com/tutorials/terraform/kubernetes-provider?in=terraform/kubernetes&utm_source=WEBSITE&utm_medium=WEB_IO&utm_offer=ARTICLE_PAGE&utm_content=DOCS&_ga=2.212280660.1786749240.1614570182-640429117.1614570182
        
* The whole process can be fully automated by the CircleCI. 


#Please note:
I would have loved to do the deployment and try out hands on the tech stack of AWS and related services and Terraform.
Unfortunately, the time constraint have not allowed me to dig deep into these. 

Given a couple of days more (Provided I can spare 3 hours for 3 days.), **I can deploy this service in the AWS** using the tools and services mentioned. 

Finally, I cannot **thank** enough for this opportunity. :)