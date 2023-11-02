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
src/main/resources/application.properties dosyasında aşağıdaki ayarlamaları kendi veritabanınıza göre yapılandırın.
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
After starting the project, go to http://localhost:8080/swagger-ui/index.html

To register a user, send a request to the user/createUser endpoint.<br><br>
![image](https://github.com/hasancolk/vehicle-yedek/assets/70701060/bd44eba4-73f4-476a-890e-a3a9a4a26596)

Log in by sending a request to the auth/login endpoint. <br><br>
![image](https://github.com/hasancolk/vehicle-yedek/assets/70701060/789cd07d-3639-4a1f-a7bf-1766b223fc57)

Copy the JWT generated as a result of the request. <br><br>
![image](https://github.com/hasancolk/vehicle-yedek/assets/70701060/16de40cd-7a70-4b66-9a6c-f7e18fbc9b1d)

After clicking 'Authorize,' paste the JWT and click the authorize button. You have now enabled the use of this token for requests to different endpoints. <br><br>
![image](https://github.com/hasancolk/vehicle-yedek/assets/70701060/cdff478b-860c-4e5e-9ea4-fb64e8d2cd78)

You can start making requests to the endpoints after activating the token. <br><br>
![image](https://github.com/hasancolk/vehicle-yedek/assets/70701060/51743c1c-c0d8-44b5-8b3b-a32f731d16c4)
