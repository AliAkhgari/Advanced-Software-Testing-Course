package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class ProfessorPropertiesTest {
    @Property
    public void profAllowedToBorrowIsFive() {
        try {
            Professor professor = new Professor("Khamespanah");
            if (professor.allowedToBorrow() != 5) {
                fail("The allowed borrow days should be 5.");
            }
        } catch (InvalidArgumentEx ignored) {

        }
    }
}
