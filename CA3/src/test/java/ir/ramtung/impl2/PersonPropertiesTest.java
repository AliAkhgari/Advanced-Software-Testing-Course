package ir.ramtung.impl2;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import ir.ramtung.sts01.LibraryException;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class PersonPropertiesTest {

    static class TestPerson extends Person {
        public TestPerson(String personName) {
            super(personName);
        }

        @Override
        public boolean canBorrow() {
            return true;
        }
    }

    @Property
    public void personInitialization(String name) {
        Person person = new TestPerson(name);
        assertEquals(name, person.getName());
        assertEquals(0, person.getPenalty());
    }

    @Property
    public void borrowedBookShouldBeInDocsBorrowAndDayBorrowLists() {
        Person person = new TestPerson("Ali Akhgari");
        Document doc = new Book("A Tale of Two Cities", 10);
        person.borrow(doc);
        assertTrue(person.docsBorrow.contains(doc));
        assertTrue(person.dayBorrow.contains(doc.getDay()));
        assertTrue(person.canExtend.contains(true));
    }

    @Property
    public void borrowedDocCanBeReturned() {
        Person person = new TestPerson("Ali Akhgari");
        Document doc = new Book("A Tale of Two Cities", 10);
        person.borrow(doc);
        person.returnDoc("A Tale of Two Cities");
        assertFalse(person.docsBorrow.contains(doc));
    }

    @Property
    public void borrowedBookCanBeExtendedOnce() throws LibraryException {
        Person person = new TestPerson("Ali Akhgari");
        Document doc = new Book("A Tale of Two Cities", 10);
        person.borrow(doc);
        person.extend("A Tale of Two Cities");
        try {
            person.extend("A Tale of Two Cities");
            fail("Expected LibraryException to be thrown");
        } catch (LibraryException e) {
            // Expected behavior
        }
    }

    @Property
    public void borrowedRefCanBeExtendedOnce() throws LibraryException {
        Person person = new TestPerson("Ali Akhgari");
        Document doc = new Reference("A Tale of Two Cities", 10);
        person.borrow(doc);
        person.extend("A Tale of Two Cities");
        try {
            person.extend("A Tale of Two Cities");
            fail("Expected LibraryException to be thrown");
        } catch (LibraryException e) {
            // Expected behavior
        }
    }

    @Property
    public void borrowedMagCannotBeExtended() {
        Person person = new TestPerson("Ali Akhgari");
        Document doc = new Magazine("A Tale of Two Cities", 1990, 10, 10);
        person.borrow(doc);
        try {
            person.extend("A Tale of Two Cities");
            fail("Expected LibraryException to be thrown");
        } catch (LibraryException e) {
            // Expected behavior
        }
    }

    @Property
    public void penaltyCalculation() {
        Person person = new TestPerson("Ali Akhgari");
        Document doc = new Book("A Tale of Two Cities", 10);
        person.borrow(doc);
        person.timePass();

        int penalty = person.getPenalty();
        assertTrue("Penalty should be non-negative", penalty >= 0);
    }
}
