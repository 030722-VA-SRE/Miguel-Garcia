# Practical

Given the following table 'employees'...

| id | firstName | lastName | salary | dept |
| --- | -------- | -------- | ------ | ---- |
| 1  | Michael   | Scott    | 65     | Sales|
| 2  | Dwight    | Schrute  | 75     | Sales|
| 3  | Toby      | Flenderson| 80    | HR  |
| 4  | Jim       | Halpert  | 90     | Sales|
| 5  | Oscar     | Martinez | 90     | Accounting |
| 6  | Angela    | Martin   | 75     | Accounting |
| 7  | Kevin     | Malone   | 70     | Accounting |
| 8  | Holly     | Flax     | 60     | HR |
| 9  | Creed     | Branton  | 70     | Quality Assurance |

* Write a query to find all data in the table

	```sql
	select * 
	from employees;
	```

* Write a query to find employees with a salary over 75
	```sql
	select * 
	from employees 
	where salary > 75;
	```
* Write a query to find employees whose first name contains an 'e' or whose last name begins with 'S'\
	```sql
	select * 
	from employees 
	where firstName like '%e%' or lastName like 'S%';
	```
* Write a query to find the first name of all employees who do not work in accounting
	```sql 
	select firstName 
	from employees 
	where not dept = 'Accounting';
	```
* Write a query to find the average salary of all employees whose last names begin with 'M'
	```sql
	select AVG(salary) as averageSalary 
	from employees 
	where lastName like 'M%';
	```
* Write a query to find the highest paid salesperson
	```sql
	select *
	from employees
	where dept = 'Sales'
	order by salary desc
	limit 1;
	```
* Write a query to combine the resultsets of any two previous queries
	```sql
	select * 
	from employees 
	where salary > 75
	union
	select firstName 
	from employees 
	where not dept = 'Accounting';
	```
* Remove all members of accounting from the database
	```sql
	delete from employees where dept = 'Accounting';
	```

* Given removing the dept column from the employees table and creating a table 'department' and linking the two via a foreign key relationship

| dept_id | name |
| ------- | ---  |
| 1       | Sales |
| 2       | HR   |
| 3       | Accounting |
| 4       | Customer Service |

* Write a query to find the salary of the lowest paid salesperson (HINT: use a join)
	```sql
	select min(salary)
	from employees e
	join department d on e.dept = d.dept_id
	where d.name = 'Sales'
	```

* Write a query to find the average salary of each department
	```sql
	select d.name, avg(salary) as averageSalary
	from employees e
	join department d on e.dept = d.dept_id
	group by d.name;
	```
* Write a query to find all possible combinations of employees and departments. How many records do you expect?

	```sql
	select *
	from employees
	cross join department;
	```

* Change the name of department 4 to 'Quality Assurance'
	```sql
	update department
	set name = 'Quality Assurance'
	where dept_id = 4; 
	```
* Remove both tables
	```sql
	delete table employees,
	delete table department;
	```


# Cloud/AWS Overview

How would you describe AWS? What is "the cloud" or "cloud computing" and why is it so popular now?
- AWS (Amazon Web Services) is a comprehensive, evloving cloud computing platform provided by Amazon.
- Cloud computing is the on-demand delivery of compute power, database storage, applications and other IT resources through a cloud services platform via the Internet with pay-as-you-go pricing.
- Advantages of Cloud Computing
	- Instead of investing heavily in data centers and servers, you can pay only when you consume computing resources
	- By using cloud computing, you can achieve a lower variable cost than you can get on your own
	- Eliminate guessing on your infrastructure capacity needs
	- Increase speed and agility because in a cloud computing environment, new IT resources are only a click away.
	- Lets you focus on your own customers, rather than on the infrastructure
	- Easily deploy your application in multiple regions around the world

Define Infrastructure, Platform, and Software as a Service
- Infrastructure as a Service (IaaS) is a self-service model for managing remote data center infrastructures. AWS offers IaaS in the form of data centers
- Platform as a Service (PaaS) allows organizations to build, run and manage applications without the IT infrastructure. This makes it easier and faster to develop, test and deploy applications
- Software as a service (SaaS) replaces the traditional on-device software with software that is licensed on a subscription basis. It is centrally hosted in the cloud. A good example is Salesforce, Slack, and Dropbox

What's the difference between a Region and an Availability Zone (AZ)?
- An AWS Region is a geographical location with a collection of availability zones mapped to physical data centers in that region
- An availability zone is a logical data center in a region available for use by any AWS customer.
- Inside each region, you will find two or more availability zones with each zone hosted in separate data centers from another zone to reduce the likelihood of two zones failing.

How are you charged for using AWS services? Does it vary by service?
 - AWS pricing is based on your usage of each individual service
 - Yes, it does vary by service

Different ways to interact with AWS services?
 - AWS provides command line tools and APis

# EC2

What are the configuration options for EC2?
- You can specify the Amazon Machine Image (AMI), instances type, key pair, and security groups etc..

What are the different EC2 instance sizes/types?
- To view more information about EC2 instance types click [here](https://aws.amazon.com/rds/instance-types/)

Once you create an EC2, how to connect to it?
- Open an SSH client
- Locate your private key file. Example: mynewkeypair.pem
- Connect to your instance using its Public DNS. Example: ec2-18-188-136-133.us-east-2.compute.amazonaws.com
- ssh -i [key file] [Public DNS]

What are Security Groups? When defining a rule for a security group, what 3 things do you need to specify?
- A security group acts as a virtual firewall for your EC2 instances to control incoming and outgoing traffic based on their IP address
- Source/Destination, Protocol, and Port range?

What's the difference between scalability, elasticity, and resiliency?
- Scalability: Handles the changing needs of an application within the confines of the infrastructure via statically adding or removing resources to meet applications demands if needed
- Elasticity: The “Elastic” nature of the service allows developers to instantly scale to meet spikes in traffic or demand.
- Resilience: Having the capability to recover when stressed by load (more requests for service), attacks (either accidental through a bug, or deliberate through intention), and failure of any component in the workload's components

Ways of paying for EC2?
 - On-demand, pay by the second

# RDS

What's an RDS?
- RDS is a relational database service, therefore it organizes data within tables in rows and columns

Which vendors are supported?
- Amazon Aurora, PostgreSQL, MySQL, MariaDB, Oracle Database, and SQL Server

# UNIX/LINUX

Write a basic bash script

	#! /bin/bash
	#Adding two values
	A=5
	B=6

	total=$((A+B))

	echo $total

