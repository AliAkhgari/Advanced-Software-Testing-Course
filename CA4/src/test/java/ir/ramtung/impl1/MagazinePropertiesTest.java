package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class MagazinePropertiesTest {
    @Property
    public void yearAndNumIsPositive(@When(satisfies = "#_ > 0") int year, @When(satisfies = "#_ > 0") int num) {
        try {
            Document doc = new Magazine("The Believer", year, num);
            assertEquals("The Believer", doc.getTitle());
        } catch (InvalidArgumentEx ignored) {
            fail("Exception should not be thrown for positive year and num");
        }
    }

    @Property
    public void penaltyIsNonNegative(int days, int year) {
        try {
            Document doc = new Magazine("The Believer", year, 10);
            if (doc.penaltyFor(days) < 0) {
                fail("The penalty value should be non negative.");
            }
        } catch (ExcessiveLateDaysException | InvalidArgumentEx ignored) {

        }
    }

    @Property
    public void loanDurationIsTen() {
        try {
            Document doc = new Magazine("The Believer", 1990, 10);
            if (doc.loanDuration() != 2) {
                fail("The loan should be 2.");
            }
        } catch (InvalidArgumentEx ignored) {

        }
    }
}
