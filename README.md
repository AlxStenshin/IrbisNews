## IrbisNews Web Application

### Used Technologies and Libraries

<li>Spring Boot</li>
<li>Spring Data</li>
<li>Spring Security</li>
<li>PostgreSQL</li>
<li>OpenCSV</li>
<li>H2 Database</li>
<li>Thymeleaf</li>
<li>Swagger</li>

### How To Build:

1. Clone the repo
   ```sh
   git clone https://github.com/AlxStenshin/IrbisNews.git
   ```
2. Navigate to the source dir
   ```sh
   cd IrbisNews
   ```
3. Build and run tests using
   ```sh
   ./gradlew clean build
   ```


### How To Run:
1. Start server using docker-compose:
   ```sh
   docker compose up
   ```
2.   Or run on a local machine with
   ```sh
   ./gradlew bootJar && java -jar build/libs/IrbisNews-0.0.1-SNAPSHOT.jar
   ```
   Please feel free modifying attached configuration *yml files for correct database connectivity. 

### How To Use:
#### 1. Access stored data via REST endpoints:

   http://localhost:8080/api/v1/news

   http://localhost:8080/api/v1/source

   http://localhost:8080/api/v1/topic

   All provided endpoints requires token-based authentication in your request header.
   Required token header field and its value provided in attached secrets.yml.

   Detailed information about provided api available on the swagger page (anonymous access, no token required) at:

   http://localhost:8080/swagger-ui

#### 2. Access data via trivial, but fully functional html forms at:
   http://localhost:8080
   or
   http://localhost:8080/html, there is also no authentication required.


   