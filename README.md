# EdTech.org - an online learning platform

# About
>EdTech.org is an online learning platform. It is a free, open source project.<br>
This project utilizes two services:
>- Consumer : NajaFiApp
>- Provider : TeluskoApp``

# NajaFi App : (Consumer)
It is a consumer app of Telusko API for managing courses

**Usage**
- NajaFi uses Telusko's Api to perform and manage CRUD operations on courses
integrating it into their application for course management.
- With the help of RestTemplate consumer can access Provider API endpoints.

---

## Telusko App : (Provider)
It is provider app which provides courses and any consumer like NajaFi can access them.

- It provides CRUD operations endpoints with the REST API to manage courses and provides 
endpoints.
- All the course information store in database.

## Technologies 

* Spring MVC
* Spring Rest
* MySql
* Spring Data Jpa
* Junit

Actuators for production management and Api Testing is done by Swagger.

