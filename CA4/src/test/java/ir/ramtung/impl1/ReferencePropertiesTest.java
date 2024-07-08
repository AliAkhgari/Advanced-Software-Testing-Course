package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class ReferencePropertiesTest {
    @Property
    public void penaltyIsNonNegative(int days) {
        try {
            Document doc = new Reference("Encyclopedias");
            if (doc.penaltyFor(days) < 0) {
                fail("The penalty value should be non negative.");
            }
        } catch (ExcessiveLateDaysException | InvalidArgumentEx ignored) {

        }
    }

    @Property
    public void loanDurationIsTen() {
        try {
            Document doc = new Reference("Encyclopedias");
            if (doc.loanDuration() != 5) {
                fail("The loan should be 5.");
            }
        } catch (InvalidArgumentEx ignored) {

        }
    }
}
