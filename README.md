# Company API

A number of basic RESTful APIs for performing simple CRUD operations on companies.
	
## Using cURL

### 1. Create new company

	curl -H "Content-Type: application/json" -X POST -d '{"name": "comp1", "address": "addr1", "city": "city1", "country": "country1", "phone": "phone1", "email": "email1", "beneficiaryOwners": [{"name": "name5"}, {"name": "name6"}]}' http://<hostname>:<port>/companies
	
### 2. Get a list of all companies

	curl -i -H "Accept: application/json" http://<hostname>:<port>/companies
	
### 3. Get details about a company

	curl -i -H "Accept: application/json" http://<hostname>:<port>/companies/:id
	
### 4. Update a company

	curl -H "Content-Type: application/json" -X PUT -d '{"name": "comp1", "address": "addr1", "city": "city1", "country": "country1", "phone": "phone1", "email": "email1"}' http://<hostname>:<port>/companies/:id
	
	
### 5. Add beneficial owner(s) of the company

	curl -H "Content-Type: application/json" -X PUT -d '{"beneficiaryOwners": [{"name": "name5"}, {"name": "name6"}]}' http://<hostname>:<port>/companies/:id/owners

	curl -i -H "Accept: application/json" http://<hostname>:<port>/companies
