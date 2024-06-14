package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class DocumentPropertiesTest {
    @Property
    public void bookTitleIsNotEmpty(@When(satisfies = "#_ != null && !#_.isEmpty()") String title) {
        try {
            Document doc = new Book(title);
            assertNotNull(doc.getTitle());
            assertEquals(title, doc.getTitle());
        } catch (InvalidArgumentEx e) {
            fail("Exception should not be thrown for non-empty title");
        }
    }

    @Property
    public void bookIsTitled(@When(satisfies = "#_ != null && !#_.isEmpty()") String title) {
        try {
            Document doc = new Book(title);
            assertTrue(doc.isTitled(title));
        } catch (InvalidArgumentEx e) {
            fail("Exception should not be thrown for non-empty title");
        }
    }
}
