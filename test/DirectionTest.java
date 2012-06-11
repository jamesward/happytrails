import models.Direction;

import models.Route;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class DirectionTest {

    @Test
    public void testCreate() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Direction direction = new Direction(new Route(), 1, "instruction");
                direction.save();
                assertThat(direction.id).isNotNull();
                assertThat(direction.stepNumber).isEqualTo(1);
                assertThat(direction.instruction).isEqualTo("instruction");
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyInstruction() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Direction direction = new Direction(new Route(), 1, null);
                direction.save();
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyStepNumber() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Direction direction = new Direction();
                direction.instruction = "instruction";
                direction.save();
            }
        });
    }

}
