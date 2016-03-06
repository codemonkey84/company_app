# Company API

A number of basic RESTful APIs for performing simple CRUD operations on companies.
	
## Using cURL

### Create new company

curl -H "Content-Type: application/json" -X POST -d '{"name": "comp1", "address": "addr1", "city": "city1", "country": "country1", "phone": "phone1", "email": "email1", "beneficiaryOwners": [{"name": "name5"}, {"name": "name6"}]}' http://_<hostname>_**:_<port>_**/companies

