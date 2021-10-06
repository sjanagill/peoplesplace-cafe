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

## GitHub


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

## SonarQube config


## Helpful Links
* [API Documentation](https://portal.tools.stg.b2cecom.eu.pvh.cloud/B2CEU/documentation/ecom-openapi-spec)
* [Kong Enterprise Documentation](https://docs.konghq.com/enterprise/)
* [Transformers Confluence Page](https://confluence-eu.pvh.com/display/PEP/PVH+Ecom+API)
* [AWS SSO Login](https://pvh-europe.awsapps.com/start)
* [PVH ECOMM API Spec](https://confluence-eu.pvh.com/display/PEP/PVH+Ecom+API%3ASPEC)
* [Dyntrace](https://mjx66619.live.dynatrace.com/)
