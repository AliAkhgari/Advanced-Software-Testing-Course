package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class BookPropertiesTest {
    @Property
    public void penaltyIsNonNegative(int days) {
        try {
            Book book = new Book("A Tale of Two Cities");
            if (book.penaltyFor(days) < 0) {
                fail("The penalty value should be non negative.");
            }
        } catch (ExcessiveLateDaysException | InvalidArgumentEx ignored) {

        }
    }

    @Property
    public void loanDurationIsTen() {
        try {
            Book book = new Book("A Tale of Two Cities");
            if (book.loanDuration() != 10) {
                fail("The loan should be 10.");
            }
        } catch (InvalidArgumentEx ignored) {

        }
    }
}
