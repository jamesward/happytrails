package models;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.imgscalr.Scalr;
import play.Logger;
import play.db.ebean.Model;
import utils.S3Blob;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Entity
public class S3Photo extends Model {

    @Id
    public Long id;
    
    public String bucket;
    
    public String key;
    
    public URL getUrl() throws MalformedURLException {
        return new URL("https://s3.amazonaws.com/" + bucket + "/" + key);
    }
    
    public S3Photo() {
        super();
    }

    public S3Photo(File file, int maxWidthOrHeight) throws IOException {
        super();
        
        this.bucket = S3Blob.s3Bucket; // store this here so the bucket can change without breaking stuff
        this.key = UUID.randomUUID().toString() + ".jpg";
        
        // resize the image
        BufferedImage bufferedImage = ImageIO.read(file);

        BufferedImage thumbnail = Scalr.resize(bufferedImage, maxWidthOrHeight);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "jpg", byteArrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");

        // save the image in S3
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, inputStream, objectMetadata);
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        
        if (S3Blob.amazonS3 == null) {
            Logger.error("Cloud not save Photo because amazonS3 was null");
        }
        else {
            S3Blob.amazonS3.putObject(putObjectRequest);
        }
    }

}
