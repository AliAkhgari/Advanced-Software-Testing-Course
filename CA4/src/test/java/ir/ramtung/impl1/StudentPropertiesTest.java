package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class StudentPropertiesTest {
    @Property
    public void studentIdIsNotEmpty(@When(satisfies = "!#_.isEmpty()") String id, @When(satisfies = "!#_.isEmpty()") String name) {
        try {
            Student student = new Student(id, name);
        } catch (InvalidArgumentEx e) {
            fail("Exception should not be thrown for non-empty name");
        }
    }

    @Property
    public void studentAllowedToBorrowIsTwo() {
        try {
            Student student = new Student("810198341", "Ali Akhgari");
            if (student.allowedToBorrow() != 2) {
                fail("The allowed borrow days should be 2.");
            }
        } catch (InvalidArgumentEx ignored){

        }
    }
}
