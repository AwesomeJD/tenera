
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



# Things that can be improved:
* The logging can be improved
* The Exception handling can be added more, by more testing the edge cases. 
* The **Clean-up** of the Database can be done using a **Scheduler**. This Scheduler can run 
in periodically and delete the records for each city from the table, that are older than the top 5 records.
  
* A Distributed cache could be used instead of a Database. That will add to the performance for sure.
* The API key for the open API, is a Base64 encoded string right now in the yaml file. This could be put into a vault, Ex Hashi vault.


  
# Changes to make this application a production ready code:
* A persistent DB like postgress or mysql or even MongoDB should be 
  used, instead of an in memory DB.
  
* The in-memory DB can be used for local testing and for some low level environments.
  
* Vault should be configured for storing the Open weather API key.
  
* Sonar configuration has to be added for the quality gate. In this the JUnits coverage, Integration tests coverage, the style checks can be checked as a hard stop.
 
* The **Security scan** step like the veracode  has to be added.

* A step for **Pushing** the image or the jar to the nexus has to be added. 
* SSL can be added to make it a secure application.


# High level view of deploying this application in AWS:

