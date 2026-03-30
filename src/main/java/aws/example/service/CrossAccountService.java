package aws.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrossAccountService {

    private final StsClient stsClient;

    @Value("${aws.region}")
    private String region;

    public CrossAccountService(StsClient stsClient) {
        this.stsClient = stsClient;
    }

    public AwsCredentialsProvider assumeRole(String roleArn, String roleSessionName) {
        AssumeRoleRequest request = AssumeRoleRequest.builder()
                .roleArn(roleArn)                    // "arn:aws:iam::OTHER_ACCOUNT:role/MyRole"
                .roleSessionName(roleSessionName)         // "my-app-session"
                .durationSeconds(3600)              // 1 hour max by default
                .build();

        AssumeRoleResponse response = stsClient.assumeRole(request);
        Credentials creds = response.credentials();

        return StaticCredentialsProvider.create(
                AwsSessionCredentials.create(
                        creds.accessKeyId(),
                        creds.secretAccessKey(),
                        creds.sessionToken()             // session token required for temp creds
                )
        );
    }

    // Use the assumed role credentials to access another account's S3
    public List<String> listBucketsInOtherAccounts(String roleArn) {
        AwsCredentialsProvider credentialsProvider = assumeRole(roleArn, "list-buckets");

        S3Client crossAccountS3 = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();

        return crossAccountS3.listBuckets().buckets()
                .stream()
                .map(Bucket::name)
                .collect(Collectors.toList());
    }

    public String decodeAuthorizationError(String encodedMessage) {
        DecodeAuthorizationMessageRequest request = DecodeAuthorizationMessageRequest.builder()
                .encodedMessage(encodedMessage)
                .build();

        DecodeAuthorizationMessageResponse response = stsClient.decodeAuthorizationMessage(request);

        return response.decodedMessage();
    }

}
