package models

import java.util.Date

import play.api.Play.current
import reflect.BeanProperty
import org.codehaus.jackson.annotate.JsonProperty
import net.vz.mongodb.jackson.ObjectId
import play.modules.mongodb.jackson.MongoDB
import javax.persistence.Id
import java.security.MessageDigest


class User(@ObjectId @Id val id: String,
           @BeanProperty @JsonProperty("token") val token: String,
           @BeanProperty @JsonProperty("emailAddress") val emailAddress: String,
           @BeanProperty @JsonProperty("shaPassword") val shaPassword: Array[Byte],
           @BeanProperty @JsonProperty("fullName") val fullName: String,
           @BeanProperty @JsonProperty("creationDate") val creationDate: Date,
           @BeanProperty @JsonProperty("admin") val admin: Boolean,
           @BeanProperty @JsonProperty("regionSubscriptions") val regionSubscriptions: List[RegionSubscription],
           @BeanProperty @JsonProperty("comments") val comments: List[Comment]) {
  @ObjectId @Id def getId = id;
}

object User {
  
  def apply(fullName: String, emailAddress: String, password: String): User = {
    new User(null, null, emailAddress, getSha512(password), fullName, new Date(), false, List.empty[RegionSubscription], List.empty[Comment])
  }
  
  private lazy val db = MongoDB.collection("users", classOf[User], classOf[String])

  def create(user: User) { db.save(user) }
  def findAll() = { db.find().toArray }

  def getSha512(value: String): Array[Byte] = {
      MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"))
  }
  
}

/*
@Entity
@Table(name="account")  // because "user" is an invalid table name in some databases
public class User extends Model {

    @Id
    public Long id;
    
    private String token;

    @Column(length = 256, unique = true, nullable = false)
    @Constraints.MaxLength(256)
    @Constraints.Required
    @Constraints.Email
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress.toLowerCase();
    }

    @Column(length = 64, nullable = false)
    private byte[] shaPassword;

    @Transient
    @Constraints.Required
    @Constraints.MinLength(6)
    @Constraints.MaxLength(256)
    private String password;
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
        shaPassword = getSha512(password);
    }

    @Column(length = 256, nullable = false)
    @Constraints.Required
    @Constraints.MinLength(2)
    @Constraints.MaxLength(256)
    public String fullName;

    @Column(nullable = false)
    public Date creationDate;

    @Column(nullable = false)
    public boolean isAdmin;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<RegionSubscription> regionSubscriptions = new ArrayList<RegionSubscription>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Comment> comments = new ArrayList<Comment>();

    public User() {
        this.isAdmin = false;
        this.creationDate = new Date();
    }

    public User(String emailAddress, String password, String fullName) {
        this.isAdmin = false;
        setEmailAddress(emailAddress);
        setPassword(password);
        this.fullName = fullName;
        this.creationDate = new Date();
    }
    
    public String createToken() {
        token = UUID.randomUUID().toString();
        save();
        return token;
    }


    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    
    
    public static User findByEmailAddressAndPassword(String emailAddress, String password) {
        // todo: verify this query is correct.  Does it need an "and" statement?
        return find.where().eq("emailAddress", emailAddress.toLowerCase()).eq("shaPassword", getSha512(password)).findUnique();
    }

    public static User findByToken(String token) {
        if (token == null) {
            return null;
        }
        
        try  {
            return find.where().eq("token", token).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static User findByEmailAddress(String emailAddress) {
        return find.where().eq("emailAddress", emailAddress.toLowerCase()).findUnique();
    }
}
*/