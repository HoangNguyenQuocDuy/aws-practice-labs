package aws.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.core.retry.backoff.BackoffStrategy;
import software.amazon.awssdk.core.retry.conditions.RetryCondition;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

/**
 * Credential Provider Chain (in order):
 * 1. Environment variables: AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY
 * 2. Java system properties: aws.accessKeyId, aws.secretAccessKey
 * 3. ~/.aws/credentials file (local dev)
 * 4. EC2 Instance Profile / ECS Task Role / Lambda execution role
 *
 * NEVER hardcode credentials — the chain handles everything automatically.
 */

@Configuration
public class AwsConfig {

    @Value("${aws.region}")
    private String region;

    @Value("{$aws.retry.maxAttempt}")
    private int maxRetryAttempt;

    @Bean
    public RetryPolicy retryPolicy() {
        return RetryPolicy.builder()
                .numRetries(maxRetryAttempt)
                .retryCondition(RetryCondition.defaultRetryCondition())
                .backoffStrategy(BackoffStrategy.defaultStrategy())
                .throttlingBackoffStrategy(BackoffStrategy.defaultThrottlingStrategy())
                .build();
    }

    @Bean
    public S3Client s3Client(RetryPolicy retryPolicy) {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .overrideConfiguration(ClientOverrideConfiguration.builder()
                        .retryPolicy(retryPolicy)
                        .build())
                .build();
    }

    @Bean
    public DynamoDbClient dynamoDbClient(RetryPolicy retryPolicy) {
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .overrideConfiguration(ClientOverrideConfiguration.builder()
                        .retryPolicy(retryPolicy)
                        .build())
                .build();
    }

    @Bean
    public SqsClient sqsClient(RetryPolicy retryPolicy) {
        return SqsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .overrideConfiguration(ClientOverrideConfiguration.builder()
                        .retryPolicy(retryPolicy)
                        .build())
                .build();
    }

}

