# Company API

A number of basic RESTful APIs for performing simple CRUD operations on companies.

## DB setup

Run the scripts from *db/script.sql* into postgres DB.

## Build & Run 
.gradlew build
.gradlew run
	
## Deployed on Heroku
https://young-ridge-70702.herokuapp.com

## Using cURL

### 1. Create new company

	curl -H "Content-Type: application/json" -X POST -d '{"name": "comp1", "address": "addr1", "city": "city1", "country": "country1", "phone": "phone1", "email": "email1", "beneficiaryOwners": [{"name": "name5"}, {"name": "name6"}]}' https://young-ridge-70702.herokuapp.com/companies
	
### 2. Get a list of all companies

	curl -i -H "Accept: application/json" https://young-ridge-70702.herokuapp.com/companies
	
### 3. Get details about a company

	curl -i -H "Accept: application/json" https://young-ridge-70702.herokuapp.com/companies/:id
	
### 4. Update a company

	curl -H "Content-Type: application/json" -X PUT -d '{"name": "comp1", "address": "addr1", "city": "city1", "country": "country1", "phone": "phone1", "email": "email1"}' https://young-ridge-70702.herokuapp.com/companies/:id
	
	
### 5. Add beneficial owner(s) of the company

	curl -H "Content-Type: application/json" -X POST -d '{"beneficiaryOwners": [{"name": "name5"}, {"name": "name6"}]}' https://young-ridge-70702.herokuapp.com/companies/:id/owners