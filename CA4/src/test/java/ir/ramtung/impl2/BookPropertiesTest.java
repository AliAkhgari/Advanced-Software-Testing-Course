package ir.ramtung.impl2;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class BookPropertiesTest {
    @Property
    public void penaltyIsNonNegative(String name, int copies) {
        Document doc = new Book(name, copies);
    }

    @Property
    public void penaltyIsNonNegative(int days) {
        Document doc = new Book("A Tale of Two Cities", 10);
        if (doc.calculatePenalty(days) < 0) {
            fail("The penalty value should be non negative.");
        }
    }
}
