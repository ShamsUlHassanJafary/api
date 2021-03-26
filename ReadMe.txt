												
						The decisions i made along the way:
												
	according to your requirment and time constraint, i develope an api. it perform 5 functions
	
1. Create Customer
2. Create Sim
3. Link sim to Customer
4. Retrieve Customer Sims
5. Retrieve all Sims
 
 first of all i would like to tell you Structure of api.
 I create 2 POJO classes along with some properties and theit getters/setters.
 Classes name are:
 1) Customer
 2) Sim
 
I write one Controller class named "Welcome". There are 5 methods in it named 
createCustomer()           : for creation of Customer
createSim()                : for creation of Cell Numbers
linkcustomer()             : for Link sim(cell Numbers) to Customer
RetrieveCustomerSims()     : for  Retrieve Customer Sims
listSim()                  : for geting list of all sims.



														
							what I focused on?
														
i focus on Basic medules of api, like creation of cell numbers, creation of customers , link cell numbers to customers, retrive customers cellnumbers
and retrives all sims. I created two tables in MySql, one for Customer named "dbtcustomer"  and other for cell Numbers named "dbtsim". i joined them with 
adding primary key of Customer's table  "ct_Id" to table of dbtsim as foreign_key.

							 what I didn't focus on and why 
													 
I didn't focused on additional functionality which operator requires:

1. Email notification to be sent 7 days before the customer`s birthday. 
2. Daily export having the list of customers having their birthday on the day. 
		
		there are two reasons why i didn't focus on these functionalities, 
		first is time constraint. 
		second is, I did not practice these types of functionalites before attempting of your api test. it would requers more time for Research and Development.
		
		
							   How to run and use the program
											
this is an api and you known that you could use it by any client like Postman. there are five mappings, 
/createCustomer            
/createSim                
/listSim                  
/linkcustomer              
/RetrieveCustomerSims      

I would like to explains each controller one by one, their expected input and outputs. 

1: for creation of Customer

Url:      http://localhost:8080/Telecommunication/createCustomer
method:   Post 
Parameters: ct_fName, ct_lName, ct_gender, ct_address

above url reposible of creating Customers, its read the name  from client like postman and use
the SqlUtils Class methods for inserting Customer in database.

2 : for creation of Cell Numbers

Url:     http://localhost:8080/Telecommunication/createSim               
method:   Post 
Parameters : cellNo

it expects cellNo as parameter , that should be unique. it is responsible for creating cell Numbers and save into database table name dbtsim



3:      for Link sim(cell Numbers) to Customer

Url:        http://localhost:8080/Telecommunication/linkcustomer           
method:     Post
parameters: customerName , cellNo 
	this controller resposible of Linking sim(cell Numbers) to Customer. its expects two parameters 1)customerName and 2) cellNo 
	this controller assigns cell numbers to Customers. according to requersment one Customer have 0 or more cell numbers.
	
4: for  Retrieve Customer Sims

url:        http://localhost:8080/Telecommunication/RetrieveCustomerSims
method:     Post
parameters: customerName

	this controller responsible of retriving Cell Numbers of Customer against his/her name. this handler expects Customer name as  input parameter 
	and returens cell numbers of Customer if any.

5:    for geting list of all sims

Url:         http://localhost:8080/Telecommunication/listSim                  
methos:      Get
panameters: 
it is resposible of showing all Cell Numbers. this controller expect nothing as input parameters. it is uses Get method.
	
	Note: these functionalities are basics we can improve it further. 
