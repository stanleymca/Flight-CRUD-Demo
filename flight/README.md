# Springboot DynamoDB Flight Demo Application
Demo Spring Boot application to perform CRUD operations using DynamoDB

### Download and run DynamoDB in local using the below java command
### https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html
```
$ java -jar -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

Initializing DynamoDB Local with the following configuration:
Port:	8000
InMemory:	false
DbPath:	null
SharedDb:	true
shouldDelayTransientStatuses:	false
CorsParams:	*
```
Refer to FlightApplication.java to see how we are creating the Flight data store in DynamoDB. 

### Launch the browser and access the Swagger UI using below URL

http://localhost:8090/swagger-ui.html

Select each operation and try

### Try the below end-point to successfully retrieve all the flights data from DynamoDB

http://localhost:8090/flightService/flights

### Sample POST request with a JSON payload. Make sure that you set 'Content-Type' to 'application/json' in the request headers

http://localhost:8090/flightService/flights/  
```
    {
        "airlineCode": "EK",
        "flightNo": "0049",
        "origin": "DXB",
        "destination": "FRA",
        "ticketPrice": "1900.00",
        "active": "true"
    }
```

### Sample PUT request with a JSON payload. Make sure that you set 'Content-Type' to 'application/json' in the request headers

http://localhost:8090/flightService/flights/365c1112-acdc-43f8-9888-7f8d60746c3a
```
    {
	    "airlineCode": "LH",
	    "flightNo": "0050",
	    "origin": "DXB",
	    "destination": "MUC",
	    "ticketPrice": 1850,
	    "active": true
    }
```

### Sample GET request. 

http://localhost:8090/flightService/flights/365c1112-acdc-43f8-9888-7f8d60746c3a

### Sample DELETE request. 

http://localhost:8090/flightService/flights/365c1112-acdc-43f8-9888-7f8d60746c3a


 