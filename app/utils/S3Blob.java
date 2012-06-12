package utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import play.Application;
import play.Logger;

public class S3Blob {
    
    public static AmazonS3 amazonS3;
    
    public static String s3Bucket;
    
    public static void initialize(Application application) {
        
        if (!application.configuration().keys().contains("aws.access.key")) {
            throw new RuntimeException("You must set the aws.access.key configuration parameter");
        }

        if (!application.configuration().keys().contains("aws.secret.key")) {
            throw new RuntimeException("You must set the aws.secret.key configuration parameter");
        }

        if (!application.configuration().keys().contains("aws.s3.bucket")) {
            throw new RuntimeException("You must set the aws.s3.bucket configuration parameter");
        }

        s3Bucket = application.configuration().getString("aws.s3.bucket");
        String accessKey = application.configuration().getString("aws.access.key");
        String secretKey = application.configuration().getString("aws.secret.key");
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3 = new AmazonS3Client(awsCredentials);

        if (!amazonS3.doesBucketExist(s3Bucket)) {
            Logger.info("Creating S3 Bucket: " + s3Bucket);
            amazonS3.createBucket(s3Bucket);
        }

        Logger.info("Using S3 Bucket: " + s3Bucket);
        
    }
    
}
