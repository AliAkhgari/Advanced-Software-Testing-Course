package ir.ramtung.impl2;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class MagazinePropertiesTest {
    @Property
    public void penaltyIsNonNegative(int days, int year) {
        Magazine magazine = new Magazine("The Believer", year, 10, 10);
        if (magazine.calculatePenalty(days) < 0) {
            fail("The penalty value should be non negative.");
        }
    }
}
