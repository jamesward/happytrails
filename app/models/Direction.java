package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Direction extends Model {

  @Id
  public Long id;

  public Integer stepNumber;

  public String instruction;


  public Direction() {
  }

  public Direction(Integer stepNumber, String instruction) {
    this.stepNumber = stepNumber;
    this.instruction = instruction;
  }
}
