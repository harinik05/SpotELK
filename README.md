# 🍀 Clove App


https://github.com/user-attachments/assets/31cec458-612c-4c3c-9531-720ab7a16b83

Clove is your in-house CLI command tool that employs the power of AI to 
extract Groq pattern insights from natural-language prompts and logs. It
consists of Springboot application deployed on Amazon EKS, seamlessly 
integrated with ELK stack to deliver AI-driven log querying. Initially, the logs
from Clove are collected via FluentBit DaemonSets, processed and indexed in 
an ElasticSearch stateful set, and visualized through Kibana's frontend. 

## 🩵 Functionalities and Features
1. **AI-driven Log Querying**:
Clove enables AI-powered CLI tool that allows user to craft natural language
prompts for generating Grok patterns, enabling faster analysis without 
requiring complex querying knowledge. 

2. **Centralized Application-level logs**:
Logs from Clove application are collected via FluentBit, and essentially taken 
from a custom Logger framework from Log4J class. 

3. **Health Checks and Integration with EKS**:
Readiness, Liveness, and Startup probes were used in the application to monitor the its state, validate service availability, detect unresponsive components, and manage pod restarts to maintain reliability and performance

## 🎯 Deployment Procedure
![diagram](https://github.com/user-attachments/assets/622d1f93-3230-4164-aced-38f3bd00b119)

1. Build and Push Docker Image
```
docker build --platform linux/amd64 -t <image> .
docker inspect --format '{{.Architecture}}' <image>
docker push <image>
```

2. Create an Amazon EKS Cluster
```
eksctl create cluster \
    --name <cluster_name> \
    --node-type t2.large \
    --nodes 3 \
    --nodes-min 3 \
    --nodes-max 5 \
    --region us-east-1
```

3. Deploy the ELK Stack

4. Deploy Clove Application
Create and apply the clove-deployment.yaml file to deploy the app and expose it via a LoadBalancer service.

5. Using AI CLI - Uses OpenAI API to retrieve responses based on generic KQL
```
python QueryTool.py kibanaQL.json "Generate a KQL query to find logs where the kubernetes namespace is 'default' and the log level is 'info'"
```
Prompts like this should return appropriate Grok patterns for completing these queries

## 🔮 Future Steps 
Looking to implement this approach for Go applications, use RBAC for Kibana access, and let me know once you try out the POC :)



