# Chip Shop API

An Item Shop application

## Project Description

An application that allows users to register, login and view different brands and flavors of chips. Admins can do the same as users but can add, remove and update chips in the shop.

## Technologies Used
 * Java
 * Maven
 * Spring Boot
 * Spring MVC
 * Spring Data JPA
 * JWT
 * PostgreSQL
 * AWS RDS
 * JUnit
 * Mockito
 * Docker
 * Git
 * Log4J
 * Promtail
 * Loki
 * Grafana

## Features

Users can:
 * register as a new user
 * login into their account
 * view the different brands and flavors

Admin users can:
 * remove users
 * update users
 * view all usrs
 * add brands and flavors 
 * update brands and flavors
 * remove brands and flavors

To do list:
 * add cart so users can see what they have purchased

## Getting Started

 * Clone repository

```bash
git clone https://github.com/030722-VA-SRE/Miguel-Garcia/tree/main/project1
```
 * Create enviroment variables for your own database credentials
    * DB_URL
    * DB_USER
    * DB_PASS

 * Navigate to directory with docker-compose.yml file
 * Run docker-compose.yml to spin everything up
 ```bash
 docker-compose up -d
 ```

## Usage
Login
> localhost:8080/auth/login

Register
> localhost:8080/auth/register

Get all brands
> localhost:8080/brands

Get all flavors
> localhost:8080/flavors

Get brand by id
> localhost:8080/brands/{id}

Get flavor by id
> localhost:8080/flavors/{id}

Get all flavors of brand
> localhost:8080/brands/{id}/flavors

Get flavor of brand
> localhost:8080/brands/{id}/flavors/{id}

Admins can create/update and remove brands and flavors
> localhost:8080/brands/{id}
> localhost:8080/flavors/{id}   

After login, copy JWT token from response header into Authorization in request header

Use grafana to view logs and metrics using Loki and Prometheus
> localhost:3000


