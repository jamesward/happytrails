package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Direction extends Model {

    @Id
    public Long id;

    @Column(nullable = false)
    @Constraints.Required
    public Integer stepNumber;

    @Column(length = 1024, nullable = false)
    @Constraints.MaxLength(1024)
    @Constraints.Required
    public String instruction;
    
    @ManyToOne
    public Route route;


    public Direction() {
    }

    public Direction(Integer stepNumber, String instruction) {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }
}
