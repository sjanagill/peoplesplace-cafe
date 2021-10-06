# Peoples Place Cafe App

This is the Peoples Place Cafe API

## Requirements

* [Java](https://www.oracle.com/java/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MongoDB Atlas](https://cloud.mongodb.com/)
* [jUnit](https://junit.org/)
* [Docker](https://www.docker.com/)
* [Amamzon EC2](https://aws.amazon.com/ec2/)
* [SonarQube](https://www.sonarqube.org/)

## Swagger-UI
http://ec2-35-177-63-156.eu-west-2.compute.amazonaws.com:7575/swagger-ui.html

## EC2 Instance
http://ec2-35-177-63-156.eu-west-2.compute.amazonaws.com:7575/product/

## MongoDB Graphs
https://charts.mongodb.com/charts-project-0-jfmyt/public/dashboards/10e78fd9-f8d0-4d8b-a0d5-f7bed339a9d0

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

## Helpful Links
* [API Documentation](https://portal.tools.stg.b2cecom.eu.pvh.cloud/B2CEU/documentation/ecom-openapi-spec)
* [Kong Enterprise Documentation](https://docs.konghq.com/enterprise/)
* [Transformers Confluence Page](https://confluence-eu.pvh.com/display/PEP/PVH+Ecom+API)
* [AWS SSO Login](https://pvh-europe.awsapps.com/start)
* [PVH ECOMM API Spec](https://confluence-eu.pvh.com/display/PEP/PVH+Ecom+API%3ASPEC)
* [Dyntrace](https://mjx66619.live.dynatrace.com/)
