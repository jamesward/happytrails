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

  @Column(unique = true, nullable = false)
  public String emailAddress;

  @Column(length = 64, nullable = false)
  private byte[] shaPassword;

  @Column(nullable = false)
  public String fullName;

  @Column(nullable = false)
  public Date creationDate;


  public User() {
    this.creationDate = new Date();
  }

  public User(String emailAddress, String password, String fullName) {
    this.emailAddress = emailAddress.toLowerCase();
    this.shaPassword = getSha512(password);
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
