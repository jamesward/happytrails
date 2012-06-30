package models


import org.beaucatcher.bson.ObjectId
import org.beaucatcher.bobject.{BObject, JsonMethods, CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId}
import org.beaucatcher.mongo.{BoundSyncCollection, Context}
import org.beaucatcher.caseclass.ClassAnalysis


case class S3Photo(_id: ObjectId, name: String, bucket: String, key: String)

object S3Photo extends CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId[S3Photo] with JsonMethods[S3Photo] {

  override def jsonSync(implicit context: Context): BoundSyncCollection[BObject, BObject, BObject, _, _] = sync[BObject]

  override val jsonAnalysis = new ClassAnalysis(classOf[S3Photo])

  override def createQueryForAllObjects() = BObject()

}

/*
import play.api.Play.current
import reflect.BeanProperty
import org.codehaus.jackson.annotate.JsonProperty
import net.vz.mongodb.jackson.{Id, ObjectId}
import play.modules.mongodb.jackson.MongoDB
;

class S3Photo(@ObjectId @Id val id: String,
            @BeanProperty @JsonProperty("name") val name: String,
            @BeanProperty @JsonProperty("bucket") val bucket: String,
            @BeanProperty @JsonProperty("key") val key: String) {
  @ObjectId @Id def getId = id;
}

object S3Photo {
  private lazy val db = MongoDB.collection("s3photos", classOf[S3Photo], classOf[String])

  def create(s3photos: S3Photo) { db.save(s3photos) }
  def findAll() = { db.find().toArray }
}


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
*/