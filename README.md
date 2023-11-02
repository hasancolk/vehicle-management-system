# Vehicle Management System

This API allows for management of vehicle records and user vehicle authorizations.

In this project, users hold either admin or standard permissions within the company. Users with admin permissions can create vehicles and groups.
Those with standard permissions are able to be assigned as admin or standard within groups and vehicles.
A user granted admin permission for a group obtains the same level of permission across all of its subgroups.
Standard permission holders within a group are limited to viewing the group, whereas admins have the capability to perform all actions related to the group.
Furthermore, an admin within a group has the ability to assign standard users as either admin or standard.
Similarly, an admin associated with a vehicle can grant standard users either admin or standard permissions for that vehicle.


## Technologies

- Java 17
- Spring Boot 3.1.2
- PostgreSQL 14
- Maven 3.8.5
- Spring Data JPA
- Spring Security
- Json Web Token (JWT)
- Lombok
- Validation
- Liquibase
- OpenAPI (Swagger)


## Getting Started

#### 1. Clone the project
```shell
git clone https://github.com/hasancolk/vehicle-management-system.git
```
#### 2. Configure the database settings
Configure the following settings in the src/main/resources/application.properties file according to your own database.
```shell
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```
#### 3. Go to the project directory
```shell
cd vehicle-management-system
```
#### 4. Start the server
```shell
mvn spring-boot:run
```

## How to Use
After starting the project, go to http://localhost:8080/swagger-ui/index.html <br><br>

To register a user, send a request to the user/createUser endpoint.<br><br>
![image](https://github.com/hasancolk/vehicle-management-system/assets/70701060/ae33e2a0-f1ca-438c-8548-cd3614e21455) <br><br>

Log in by sending a request to the auth/login endpoint. <br><br>
![image](https://github.com/hasancolk/vehicle-management-system/assets/70701060/18078b10-0bfd-4ff8-930b-de73430817ad) <br><br>

Copy the JWT generated as a result of the request. <br><br>
![image](https://github.com/hasancolk/vehicle-management-system/assets/70701060/f72b883e-dce2-418d-a7e1-447fc3f2cefc) <br><br>

After clicking 'Authorize,' paste the JWT and click the authorize button. You have now enabled the use of this token for requests to different endpoints.
Remember that the token is valid for 1 hour. You must obtain a new token after it expires. <br><br>
![image](https://github.com/hasancolk/vehicle-management-system/assets/70701060/69903eb3-f4be-4f3c-9fad-53ff6a5d5fe4) <br><br>

You can start making requests to the endpoints after activating the token. <br><br>
![image](https://github.com/hasancolk/vehicle-management-system/assets/70701060/58e4df23-8f16-4f6b-9a87-0ef4645f55ca) <br><br>
