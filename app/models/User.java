package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Entity
@Table(name="account")  // because "user" is an invalid table name in some databases
public class User extends Model {

    @Id
    public Long id;
    
    public String token;

    @Column(length = 256, unique = true, nullable = false)
    @Constraints.MaxLength(256)
    @Constraints.Required
    @Constraints.Email
    public String emailAddress;

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


    public User() {
        this.creationDate = new Date();
    }

    public User(String emailAddress, String password, String fullName) {
        this.emailAddress = emailAddress.toLowerCase();
        setPassword(password);
        this.fullName = fullName;
        this.creationDate = new Date();
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
        try  {
            return find.where().eq("emailAddress", emailAddress.toLowerCase()).eq("shaPassword", getSha512(password)).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

}
