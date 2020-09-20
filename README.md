
The project is a Spring Boot application which retrieves data from mongodb database and also updates the database.

To download and install mongodb on the system refer the below documentation:
https://docs.mongodb.com/manual/installation/

After installing mongodb execute the mongo.exe application. Follow the below steps to create database (name : te), collection (name : templates) and insert documents in the collection
1. to create database - use te
2. to create collection - db.createCollection(templates)
2. insert documents in collection - db.templates.insert(
{
	"type": "system",
	"entity": "entity",
	"customerId": "system",
	"law" : "CCPA",
	"fields" : [ 
		{ "field" : "representative_contact_details", "label" : "Representative Contact Details", "data_type" :"long-text", "default" : "Type contact details here..", "field_type":	"contact_details", "field_type_label":	"Contact Details", "is_removable" : false, "mandatory": false},
		{ "field" : "data_protection_officer", "label" : "Data Protection Officer", "data_type" :"options", "default" : "Type the data protection officer name here..", "field_type":	"contact_details", "field_type_label":	"Contact Details", "is_removable" : false, "mandatory": false,
			"options_url": {
				"url" : "dm/customer/<customer_id>/users",
				"request_type" : "GET"
			}
		}, 
		{ "field" : "dpo_contact_details", "label" : "Data Protection Officer Contact Details", "data_type" :"long-text", "default" : "Type contact details here..", "field_type":	"contact_details", "field_type_label":	"Contact Details", "is_removable" : false, "mandatory": false}
	]
}
)

To run the Application follow the below steps:
mvn clean install
java -jar mongobackend-0.0.1-SNAPSHOT.jar

Below is the explanation of the code according to the requirement:
1. To develop a POST api call and process: it fetches all the system templates from the database and combines the fields to generate the final list of fields. It takes union of all the system templates. It is stored with the customer-id in the “templates” collection 
- For this developed a method postTemplateById() in controller to fetch all "system" typed templates and creating a single array of "fields" which consist of all the "fields" of "system" template and stores the generated entity in the database with type "customer" and customerId as the "customer_id" from the RequestParams. Validations considered: the input "customer_id" should be numeric and if the customer_id already exists return HttpStatusCode 400 and do not save the entity in the db.

2.To develop a GET api call and fetch the particular customer_id from the database
- For this developed a getTemplateById() in controller to fetch the particular "customer_id" entity from the db.Validations considered: The customer_id should be numeric and developed a simple Apache Velocity Template Engine to check that the customer_id template stored in the db matched the template which is global and if the customer_id already exists return HttpStatusCode 400

Unit Test Cases:
1. Service Layer - Used junit and mockito for testing the service class and mocking the dao layer.
2. Controller layer - Used junit and mockito for testing the conttoller class and mocking the service layer.

To refer to Apache Velocity providing the below link:
https://velocity.apache.org/engine/2.0/user-guide.html