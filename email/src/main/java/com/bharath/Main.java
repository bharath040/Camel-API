package com.bharath;

import java.io.FileInputStream;
import java.io.IOException; 
import java.util.Properties;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
public class Main {
    private AWSCredentials createAwsCredentials() throws IOException {
        Properties properties = new Properties();
        try{
        properties.load(new FileInputStream("C:\\Users\\bhara\\workspace\\email\\src\\main\\java\\com\\bharath\\aws.properties"));
        }catch(IOException exception){
        	System.out.println("Problem in loadin the class");
        }
        AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("aws.accessKey"),properties.getProperty("aws.secretKey"));
        return credentials;
    }
    private AmazonSimpleEmailService createSimpleEmailService() throws IOException {
        return new AmazonSimpleEmailServiceClient(createAwsCredentials());
    }
    private void sendTestEmail() throws IOException {
        PostMan postMan = new AwsPostMan(createSimpleEmailService());
        postMan.withFrom("bharathatkuri@gmail.com").withTo("bharathatkuri@gmail.com")
                .withSubject("Email from bharath").withBody(
                    "This is a assignment").send();
        System.out.println("Email has been sent");
    }
    public static void main(String[] args) throws IOException {
        new Main().sendTestEmail();
    }
}
