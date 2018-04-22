package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadToS3 {

	@Value("#{environment.AWS_ACCESS_KEY_ID}")
	String accessKey;

	@Value("#{environment.AWS_SECRET_KEY}")
	String secretKey;

	public String UploadToS3(String fileName, InputStream inputStream) throws IOException{
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();

		PutObjectRequest request = new PutObjectRequest("khushboo1234", fileName, inputStream,
				new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);

		amazonS3.putObject(request);

		String url = "http://" + "khushboo1234" + ".s3.amazonaws.com/" + fileName;

		return url;

	}
}
