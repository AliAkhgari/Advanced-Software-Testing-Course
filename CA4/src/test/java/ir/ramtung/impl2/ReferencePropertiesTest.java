package ir.ramtung.impl2;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class ReferencePropertiesTest {
    @Property
    public void penaltyIsNonNegative(int days, int year) {
        Reference reference = new Reference("Encyclopedias", 10);
        if (reference.calculatePenalty(days) < 0) {
            fail("The penalty value should be non negative.");
        }
    }
}
