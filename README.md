Trade Store

Introduction

Trade Store provides REST services for create, search for trades allowing implement through an API 

Technologies

Built on maven, java 8, spring-boot 2.5.1, lombok.

Maven and Java 8 are required to run the project, be sure you have already installed.

How start the Trade Store

Run Test Cases:

For maven:

$ mvn clean test

Build:

For maven:

$ mvn clean install

Start:

For maven:
$ java -jar target/tradestore-1.0.jarmvn

How access the Trade Store API (verbs and status)

POST http://localhost:8080/v1/trades/save
Create a new trade after valid checks of maturity date and version number (Http Status 200).

GET http://localhost:8080/v1/trades/all
Return the json of trade saved (Http Status 200). If the there are no trades the status code returned is 404.

Scheduler to update Expiry trades , runs every day . Logs looks as below

[
2021-06-26 00:00:00.024  INFO 17020 --- [   scheduling-1] c.d.t.scheduler.TradesExpiryScheduler    : In updateTradeExpiry method TradesExpiryScheduler

2021-06-26 00:00:00.026  INFO 17020 --- [   scheduling-1] c.d.t.scheduler.TradesExpiryScheduler    : Scheduler start time : 00:00:00.026

2021-06-26 00:00:00.027  INFO 17020 --- [   scheduling-1] c.d.t.service.TradesServiceImpl          : In updateTradeExpiry of TradesServiceImpl

2021-06-26 00:00:00.027  INFO 17020 --- [   scheduling-1] com.db.tradestore.dao.TradesDAOImpl      : Inside findAll method TradesDAOImpl

2021-06-26 00:00:00.041  INFO 17020 --- [   scheduling-1] c.d.t.scheduler.TradesExpiryScheduler    : Scheduler end time : 00:00:00.041
]
 
Sample JSON:

	[
        {
            "id": 1,
            "tradeId": "T1",
            "version": 1,
            "counterPartyId": "B1",
            "bookId": "CP-1",
            "maturityDate": "2021-07-28",
            "createdDate": "2021-06-26",
            "expired": "N"
        },
        {
            "id": 2,
            "tradeId": "T1",
            "version": 2,
            "counterPartyId": "B1",
            "bookId": "CP-1",
            "maturityDate": "2021-07-29",
            "createdDate": "2021-06-26",
            "expired": "N"
        },
        {
            "id": 3,
            "tradeId": "T2",
            "version": 1,
            "counterPartyId": "B1",
            "bookId": "CP-1",
            "maturityDate": "2021-07-29",
            "createdDate": "2021-06-26",
            "expired": "N"
        },
        {
            "id": 4,
            "tradeId": "T1",
            "version": 3,
            "counterPartyId": "B1",
            "bookId": "CP-1",
            "maturityDate": "2021-07-29",
            "createdDate": "2021-06-26",
            "expired": "N"
        }
    ]
	
For Swagger :

http://localhost:8080/swagger-ui.html#/
