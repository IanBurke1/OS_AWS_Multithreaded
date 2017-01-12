# Operating Systems Project
For this module, I am required to	write	a	Multi-threaded TCP Server	Application	which	allows multiple	customers to	
update their bank	accounts and send	payments to	other	bank accounts. The project must be written in java programming language.

1. Register with the	system.
  * Name
  * Address
  * Bank	A/C	Number
  * Username	
  * Password
2. Log-in	to	the	banking system	from the	client	application	to the	server	application.
3. Change	customer	details.
4. Make	Lodgements	to	their	Bank	Account.
5. Make	Withdrawal	from	their	Bank	Account	(Note:	Each	User	has	a	credit	limit	of	€1000).
6. View	the	last	ten	transactions	on	their	bank	account.

### Project Overview
As stated above, I had to write a Multi-threaded TCP (Transmission Control Protocol) Server Application for multiple customers. I started of working with an Echo Server and Requester examples given to us by our lecturer. These two examples were able to connect and communicate to each other by running the two classes in two seperate [Eclipse](https://eclipse.org/) programmes. This was a basic foundation to start from. As part of the project, we had to use an [Amazon Web Service](https://aws.amazon.com/) to run the server on. 

### Problems Encountered
I hit a wall when it came to the user creating a new account. I got a little user menu up and running and ask the user for their details but then it got messy and confusing. The sending messages between client and server got complicated for me. 

### How to run
* Download [Eclipse](https://eclipse.org/). 
* Create two seperate projects for the Server and Client.
* Run Echo server first and then run the client(Requester).
