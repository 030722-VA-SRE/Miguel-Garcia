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

# RDS

What's an RDS?
- RDS is a relational database service, therefore it organizes data within tables in rows and columns

Which vendors are supported?
- Amazon Aurora, PostgreSQL, MySQL, MariaDB, Oracle Database, and SQL Server