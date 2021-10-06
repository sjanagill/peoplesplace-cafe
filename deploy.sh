#!/usr/bin/env bash

rm -rf target/
echo "Deleted target/ folder"

"C:\PVH\apache-maven-3.8.2\bin\mvn" package
echo "Generating jar file"

#Copy execute_commands_on_ec2.sh file which has commands to be executed on server... Here we are copying this file
# every time to automate this process through 'deploy.sh' so that whenever that file changes, it's taken care of
scp -i "C:/Users/sjanagill/Documents/PP_Demo.pem" execute_commands_on_ec2.sh ec2-user@ec2-35-177-63-156.eu-west-2.compute.amazonaws.com:/home/ec2-user
echo "Copied latest 'execute_commands_on_ec2.sh' file from local machine to ec2 instance"

scp -i "C:/Users/sjanagill/Documents/PP_Demo.pem" target/peoplesplace-cafe-0.0.1-SNAPSHOT.jar ec2-user@ec2-35-177-63-156.eu-west-2.compute.amazonaws.com:/home/ec2-user
echo "Copied jar file from local machine to ec2 instance"

echo "Connecting to ec2 instance and starting server using java -jar command"
ssh -i "C:/Users/sjanagill/Documents/PP_Demo.pem" ec2-user@ec2-35-177-63-156.eu-west-2.compute.amazonaws.com ./execute_commands_on_ec2.sh