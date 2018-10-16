# DemoProject

# Overview     
MyRetail is a rest service for myRetail company . The service provides the product detail to the external/internal services. GET method is used for the retreiving the Product Details and PUT method is used to update the Product Details.

## Project key dependencies (at the time of this writing):
Java 1.8

Apache Maven 3.5.4

Cassandra 3.11.3

Datastax DevCenter 1.6 (optional)(We can use cqlsh of the cassandra open source)

STS or any IDE

## Setup
Repository location is: https://github.com/trishalalatthe/DemoProject

## Cassandra Set up
Downlaod the Cassandra from (http://cassandra.apache.org/) 

Set up the Cassandra home and Path in the environment variables(windows), for Mac (bashprofile).

Start the cassandra.bat from the cassandra bin folder.

open the cqlsh or datastax devcenter and execute the below queries.


## DB table Creation scripts
CREATE KEYSPACE IF NOT EXISTS myretail WITH
 REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 };
 
CREATE TABLE myretail.product_detail(product_id varchar,product_value decimal, 
	currency_code varchar, PRIMARY KEY (product_id));
  
INSERT INTO  myretail.product_detail (product_id ,product_value , 
	currency_code) VALUES('13860428',13.49, 'USD');
  
INSERT INTO  myretail.product_detail (product_id ,product_value , 
	currency_code) VALUES('15117729',15.29, 'USD');
  
INSERT INTO  myretail.product_detail (product_id ,product_value , 
	currency_code) VALUES('16483589',23.29, 'USD');
  
INSERT INTO  myretail.product_detail (product_id ,product_value , 
	currency_code) VALUES('16696652',63.29, 'USD');
  
INSERT INTO  myretail.product_detail (product_id ,product_value , 
	currency_code) VALUES('16752456',3.29, 'USD');
  
INSERT INTO  myretail.product_detail (product_id ,product_value , 
	currency_code) VALUES('15643793',16.15, 'USD');

## Swagger details :
http://localhost:8080/swagger-ui.html#/

## Steps to run the project
Import the project in IDE from github(https://github.com/trishalalatthe/DemoProject)

Start the Cassandra DB

Run the Spring boot project.

Hit the URL in postman to validate the request and repsonse :http://localhost:8080/products/{productId}

 




