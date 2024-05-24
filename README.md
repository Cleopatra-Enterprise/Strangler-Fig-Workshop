# Strangler Fig workshop
This is a workshop to learn how to use the Strangler Fig pattern to migrate a monolithic application to a service-based architecture. During the workshop, your goal will be to migrate the application in this repository to a service-based architecture using the Strangler Fig pattern.

## The application
Pending description, but it's a simple monolithic application that uses a database to store data.

## Postman collection
To help you test the application, we've created a Postman collection that contains all the requests you need to test the application. You can find the collection in the [strangler-fig-workshop.postman_collection.json](strangler-fig-workshop.postman_collection.json) file. To import the collection into Postman, click on the hamburger menu in the top left corner, select `File > Import` and select the file.

The collection contains a configuration with component IDs which are commonly re-used in the requests. You can find the configuration in the `Variables` tab in the collection. Authorization is also configured in the collection, so you don't need to worry about setting up the authorization headers.

## Useful resources
Pending description, but it will include links to resources that will help you during the workshop.
- Resources about Spring Boot (to be added)
- Resources about Spring Cloud (gateway, to be added)

## Prerequisites
### Option A: Docker
To participate in this workshop using Docker, you only need to have Docker installed on your computer. You can download Docker from [here](https://www.docker.com/products/docker-desktop). Once you've installed Docker, the application will automatically set up the necessary infrastructure for you.

### Option B: Local development environment
To participate in this workshop using a local environment, you only need to have a computer with SQL Server installed. You can use the free developer version of SQL Server, which is SQL Server Developer. You can download it from [here](https://www.microsoft.com/en-us/sql-server/sql-server-downloads).

Once you've installed SQL Server, open SQL Server Management Studio (SSMS) and create a new database called `stranglerfig`. You can do this by right-clicking on the `Databases` node in the Object Explorer, selecting `New Database`, and entering `stranglerfig` as the database name. You can leave the rest of the settings as default.

After setting up the databse, you need to make two changes to the application:
1. If you're not using the default Service Account (`sa`) to connect to the database, you need to update the `application.properties` file in the `src/main/resources` folder to use your credentials. You can find the file [here](src/main/resources/application.properties).
2. You need to disable Docker Compose integration in the `pom.xml` file. To do so, comment out the `spring-boot-docker-compose` dependency. You can find the file [here](pom.xml).
