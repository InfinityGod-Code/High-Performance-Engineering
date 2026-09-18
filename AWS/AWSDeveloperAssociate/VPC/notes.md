# VPC(Virtual Private Cloud)
AWS provides a Virtual Private Cloud (VPC) to create an isolated and logically separated network for our AWS resources.

A VPC allows us to control how resources communicate with each other, with the internet, and with external networks.

By default, resources inside a private subnet are not directly reachable from the public internet. We control inbound and outbound traffic using different networking components such as Internet Gateways, NAT Gateways, route tables, Security Groups, and Network ACLs.

A VPC provides several important networking and security capabilities:

- IP address management: We define a CIDR range for the VPC and assign private IP addresses to resources.
- Subnets: We divide the VPC into public and private subnets, typically across multiple Availability Zones for high availability.
- Internet Gateway: Provides a path for resources in public subnets to communicate with the internet.
- NAT Gateway: Allows resources in private subnets to initiate outbound connections to the internet without allowing unsolicited inbound connections from the internet.
- Route Tables: Control where network traffic is routed, such as to an Internet Gateway, NAT Gateway, or other networks.
- Security Groups: Act as stateful virtual firewalls at the resource level, controlling inbound and outbound traffic.
- Network ACLs: Act as stateless firewalls at the subnet level and can allow or deny traffic based on rules.
- VPC Peering / Transit Gateway / VPN: Allow communication between VPCs or with on-premises networks when required.


### Points to Ponder 
- VPC: A logically isolated network that spans an entire AWS Region.
- Subnets: Divide a VPC into smaller network segments, such as public, private, and database subnets. A subnet belongs to one Availability Zone.
- Region: A separate geographical area where AWS operates multiple Availability Zones.
- Availability Zone (AZ): One or more discrete data centers within a Region, designed with independent power, cooling, and networking to provide fault isolation.
- Public Subnet: A subnet with a route to an Internet Gateway, typically used for resources such as public ALBs.
- Private Subnet: Has no direct route from the internet; commonly used for application servers, ECS tasks, and internal services.
- Database Subnet: Usually private and isolated from direct internet access; commonly used for RDS databases.
- High Availability: Deploying resources across multiple AZs protects against failure of a single AZ.
- Every AWS region have atleast three availability zones.


