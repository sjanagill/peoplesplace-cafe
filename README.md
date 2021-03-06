# Peoples Place Cafe App

This is the Peoples Place Cafe API. It provides the backend REST APIs for the users to add/remove product types, products, users and update cart. It is a Spring Boot application which using NoSQL MongoDB Atlas instance. 

## Requirements

* [Java](https://www.oracle.com/java/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MongoDB Atlas](https://cloud.mongodb.com/)
* [jUnit](https://junit.org/)
* [Docker](https://www.docker.com/)
* [Amazon EC2](https://aws.amazon.com/ec2/)
* [SonarQube](https://www.sonarqube.org/)

## Open API Specification
http://ec2-18-169-2-189.eu-west-2.compute.amazonaws.com:7575/swagger-ui.html

![image](https://user-images.githubusercontent.com/40201060/136126566-85601325-5b34-4dff-9c43-bf9e870f7b5a.png)

## Postman Collection
https://www.getpostman.com/collections/1c929838c08d21f7c267

## Basic Architecture

![image](https://user-images.githubusercontent.com/40201060/136184929-c0f29fb5-28ad-4584-88f5-b686e7f3d7a8.png)

## EC2 Instance
http://ec2-18-169-2-189.eu-west-2.compute.amazonaws.com:7575/product/

![image](https://user-images.githubusercontent.com/40201060/136126690-5906e7ed-6cb7-4435-b12a-b7b54ed04853.png)


## Analytics using MongoDB Graphs
https://charts.mongodb.com/charts-project-0-jfmyt/public/dashboards/10e78fd9-f8d0-4d8b-a0d5-f7bed339a9d0

![image](https://user-images.githubusercontent.com/40201060/136126854-54226b62-9dad-4804-9e89-0a9e631bfe5d.png)


## SonarQube Instance
http://localhost:9000/projects?sort=name


## GitHub
https://github.com/sjanagill/peoplesplace-cafe

## Installation

Use the maven command line interface [Maven](https://maven.apache.org/) to install the dependencies

```bash
mvn install -DSkipTests
```

## Test
Use the maven command line interface [Maven](https://maven.apache.org/) to perform unit test
```bash
mvn test
```

## Docker build
```bash
docker build --rm -f "Dockerfile" -t pp-cafe:latest "."
```

### Docker run
```bash
docker run -f "Dockerfile" -it -p7575:7575 pp-cafe:latest
```

## AWS deploy (Steps in deploy.sh)
```bash
./deploy.sh
```
