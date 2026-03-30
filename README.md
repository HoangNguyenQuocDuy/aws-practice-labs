# Certified AWS Developer - Associate Notes

### 2025 AWS Developer Associate Exam (DVA-C02)

## Study Files

| File | Description |
|------|-------------|
| [`dva-c02-study-guide.html`](dva-c02-study-guide.html) | Core concepts reference — all exam topics with key facts, tables, and gotchas |
| [`dva-c02-java-labs.html`](dva-c02-java-labs.html) | 18 Java & Spring Boot labs — AWS SDK v2, all major services in Java |
| [`dva-c02-supplemental-labs.html`](dva-c02-supplemental-labs.html) | 12 gap-coverage labs with step-by-step AWS Console UI walkthroughs |

> Open any `.html` file directly in your browser — no server needed, works fully offline.

---

## Table of contents

- AWS Fundamentals
    - [IAM: Identity Access & Management](1-aws-fundamentals/iam.md)
    - [EC2: Virtual Machines](1-aws-fundamentals/ec2.md)
    - [Security Groups](1-aws-fundamentals/security-groups.md)
    - [ELB: Elastic Load Balancers](1-aws-fundamentals/elb.md)
    - [ASG: Auto Scaling Group](1-aws-fundamentals/asg.md)
    - [EBS: Elastic Block Store](1-aws-fundamentals/ebs.md)
    - [RDS: Relational Database Service](1-aws-fundamentals/rds.md)
    - [Route 53](1-aws-fundamentals/route53.md)
    - [ElastiCache](1-aws-fundamentals/elasticache.md)
    - [VPC: Virtual Private Cloud](1-aws-fundamentals/vpc.md)
    - [S3 Buckets](1-aws-fundamentals/s3.md)

- AWS Deep Dive
    - [CLI: Command Line Interface](2-aws-deep-dive/cli.md)
    - [SDK: Software Development Kit](2-aws-deep-dive/sdk.md)
    - [Elastic Beanstalk](2-aws-deep-dive/elastic-beanstalk.md)
    - [CICD: Continuous Integration and Deployment](2-aws-deep-dive/cicd/cicd.md)
        - [CodeCommit](2-aws-deep-dive/cicd/codecommit.md)
        - [CodePipeline](2-aws-deep-dive/cicd/codepipeline.md)
        - [CodeBuild](2-aws-deep-dive/cicd/codebuild.md)
        - [CodeDeploy](2-aws-deep-dive/cicd/codedeploy.md)
    - [CloudFormation](2-aws-deep-dive/cloudformation/cloudformation.md)
    - [CloudWatch](2-aws-deep-dive/monitoring-and-audit/cloudwatch.md)
    - [Integration and Messaging](2-aws-deep-dive/integration-and-messaging/0-intro.md)
        - [SQS](2-aws-deep-dive/integration-and-messaging/1-sqs.md)
        - [SNS](2-aws-deep-dive/integration-and-messaging/2-sns.md)
        - [Kinesis](2-aws-deep-dive/integration-and-messaging/3-kinesis.md)

- [YAML](2-aws-deep-dive/yaml.md)

- [AWS Serverless](3-aws-serverless/serverless.md)
    - [Lambda](3-aws-serverless/lambda.md)
    - [DynamoDB](3-aws-serverless/dynamodb.md)
    - [API Gateway](3-aws-serverless/apigateway.md)
    - [SAM](3-aws-serverless/sam.md)
    - [Cognito](3-aws-serverless/cognito.md)
    - [Step Functions](3-aws-serverless/stepfunctions.md)
    - [AppSync](3-aws-serverless/appsync.md)

- Docker based AWS services
    - [ECS: Elastic Container Service](4-aws-containers/ecs.md)
    - [Elastic Container Registry](4-aws-containers/ecr.md)
    - [Fargate](4-aws-containers/fargate.md)

- Other
    - [KMS](5-others/kms.md)
    - [Secret Manager](5-others/secret-manager.md)

## Exam Preparation

- Exam details
    - Two question types:
        - Multiple Choice
        - Multiple response
    - Minimum passing score: 720/1000
    - Domains:
        - Deployment: CICD, Beanstalk, Serverless
        - Security: each service deep-dive + dedicated section
        - Development with AWS Services: Serverless, API, SDK, & CLI
        - Refactoring: Understand all the AWS services for the best migration
        - Monitoring and Troubleshooting: CloudWatch, CloudTrail, X-Ray

    - Exam Guide:
        - [Certified Developer - Associate Exam PDF](https://d1.awsstatic.com/training-and-certification/docs-dev-associate/AWS-Certified-Developer-Associate_Exam-Guide.pdf)

- EC2 + IAM Exam Checklist
    * Know how to SSH into EC2 (and change .pem file permissions)
    * Know how to properly use security groups
    * Know the fundamental differences between private vs public vs elastic IP
    * Know how to use User Data to customize your instance at boot time
    * Know that you can build custom AMI to enhance your OS
    * EC2 instances are billed by the second and can be easily created and thrown away, welcome to the cloud!

  Maybe on Exam:
    * Availability zones are in geographically isolated data centers
    * IAM users are NOT defined on a per-region basis
    * If you are getting a permission error exception when trying to SSH into your linux instance, then the key is missing chmod 400 permissions
    * If you are getting a network timeout when trying to SSH into your EC2 instance, then your security groups are misconfigured
    * Security groups reference IP address, CIDR block, Security group, but NOT DNS name

---

## ☕ Java & Spring Boot Labs — `dva-c02-java-labs.html`

18 labs covering every DVA-C02 domain using **Java 17+**, **AWS SDK v2**, **Spring Boot 3.x**, and **Spring Cloud AWS**. Each lab contains runnable code, Maven dependencies, and exam insights.

### Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 17+ | Runtime |
| Spring Boot | 3.x | Application framework |
| AWS SDK for Java | v2 (2.25+) | AWS service clients |
| Spring Cloud AWS | 3.x | `@SqsListener`, `@Cacheable` with Redis |
| Maven Shade Plugin | 3.5+ | Fat JAR for Lambda deployment |
| Lettuce | (via Spring Data Redis) | Redis client |
| HikariCP | (via Spring Boot) | JDBC connection pooling |

### Lab Index

| # | Lab | AWS Services | Key Concepts |
|---|-----|-------------|--------------|
| 1 | AWS SDK v2 Setup & Credential Chain | IAM, SDK | `DefaultCredentialsProvider`, BOM, exponential backoff |
| 2 | IAM Roles & STS AssumeRole | IAM, STS | Cross-account access, `AssumeRole`, decode auth errors |
| 3 | Lambda Handler & Cold Start Optimization | Lambda | Static initializer, fat JAR, `RequestHandler`, context |
| 4 | Spring Boot inside Lambda | Lambda, API Gateway | `aws-serverless-java-container`, versions, aliases, canary |
| 5 | Lambda + SQS DLQ & Idempotency | Lambda, SQS | `SQSBatchResponse`, partial failures, visibility timeout |
| 6 | DynamoDB Enhanced Client CRUD | DynamoDB | `@DynamoDbBean`, conditional writes, pagination, TTL |
| 7 | DynamoDB Streams Consumer | DynamoDB, Lambda | INSERT/MODIFY/REMOVE events, SNS fan-out |
| 8 | RDS + Spring Data JPA | RDS, Secrets Manager | HikariCP, read replica routing, `@Transactional(readOnly)` |
| 9 | ElastiCache Redis — Spring Cache | ElastiCache | `@Cacheable`, `@CachePut`, `@CacheEvict`, Spring Session |
| 10 | SQS Producer & Consumer | SQS, Spring Cloud AWS | `@SqsListener`, FIFO queues, batch processing |
| 11 | SNS Fan-Out Publisher | SNS | Message attributes, filter policies, mobile push |
| 12 | Kinesis Producer | Kinesis | Partition keys, batch put, hot shard avoidance |
| 13 | S3 Operations | S3 | Multipart upload, SSE-KMS, pre-signed URLs, Object Lambda |
| 14 | X-Ray Distributed Tracing | X-Ray | Annotations vs metadata, `patch_all()`, subsegments |
| 15 | CloudWatch Custom Metrics | CloudWatch | `PutMetricData`, Spring AOP auto-recording |
| 16 | KMS Envelope Encryption | KMS, Secrets Manager | `GenerateDataKey`, envelope pattern, secret caching |
| 17 | Spring Boot on ECS Fargate | ECS, ECR, Fargate | Multi-stage Dockerfile, health checks, JVM container flags |
| 18 | CodeBuild for Java/Maven | CodeBuild | `buildspec.yml`, Maven cache, Docker build, SSM secrets |

### Key Patterns Covered

**Cold Start Optimization**
```java
// ✅ Static block = runs once on cold start, reused on warm invocations
private static final DynamoDbClient dynamoDb;
static {
    dynamoDb = DynamoDbClient.builder().region(Region.of(System.getenv("AWS_REGION"))).build();
}
// ❌ Never create clients inside handleRequest() — new client every invocation
```

**Credential Chain (never hardcode)**
```java
// Works automatically for local dev (~/.aws/credentials) AND Lambda/EC2/ECS (IAM Role)
.credentialsProvider(DefaultCredentialsProvider.create())
```

**SQS DLQ Placement Rule**
- S3/SNS → Lambda (async): DLQ on the **Lambda function**
- SQS → Lambda (event source mapping): DLQ on the **SQS queue**

**DynamoDB WCU/RCU Quick Reference**
```
WCU = writes/s × CEILING(item_KB / 1KB)
Strong RCU = reads/s × CEILING(item_KB / 4KB)
Eventual RCU = Strong RCU / 2
Transactions = 2× WCU or RCU
```

### Prerequisites

```bash
# Java 17+
java -version

# Maven 3.8+
mvn -version

# AWS CLI configured
aws configure

# AWS SAM CLI (for local Lambda testing)
sam --version
```

### Running Labs Locally

```bash
# Build fat JAR
mvn clean package -DskipTests

# Deploy Lambda
aws lambda create-function \
  --function-name my-handler \
  --runtime java17 \
  --handler com.myapp.MyHandler::handleRequest \
  --role arn:aws:iam::ACCOUNT:role/LambdaRole \
  --zip-file fileb://target/my-app.jar \
  --timeout 30 \
  --memory-size 512

# Local test with SAM
sam local invoke MyFunction --event events/test.json
```

---

## Exam Preparation

- Exam details
    - Two question types:
        - Multiple Choice
        - Multiple response
    - Minimum passing score: 720/1000
    - Domains:
        - Deployment: CICD, Beanstalk, Serverless
        - Security: each service deep-dive + dedicated section
        - Development with AWS Services: Serverless, API, SDK, & CLI
        - Refactoring: Understand all the AWS services for the best migration
        - Monitoring and Troubleshooting: CloudWatch, CloudTrail, X-Ray

    - Exam Guide:
        - [Certified Developer - Associate Exam PDF](https://d1.awsstatic.com/training-and-certification/docs-dev-associate/AWS-Certified-Developer-Associate_Exam-Guide.pdf)

- EC2 + IAM Exam Checklist
    * Know how to SSH into EC2 (and change .pem file permissions)
    * Know how to properly use security groups
    * Know the fundamental differences between private vs public vs elastic IP
    * Know how to use User Data to customize your instance at boot time
    * Know that you can build custom AMI to enhance your OS
    * EC2 instances are billed by the second and can be easily created and thrown away, welcome to the cloud!

  Maybe on Exam:
    * Availability zones are in geographically isolated data centers
    * IAM users are NOT defined on a per-region basis
    * If you are getting a permission error exception when trying to SSH into your linux instance, then the key is missing chmod 400 permissions
    * If you are getting a network timeout when trying to SSH into your EC2 instance, then your security groups are misconfigured
    * Security groups reference IP address, CIDR block, Security group, but NOT DNS name

# Contributors

Please feel free to contribute by making a Pull Request!
