package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class LibraryPropertiesTest {
    @Property
    public void canAddStudentMember(String studentId, String studentName) {
        assumeTrue(studentId != null && !studentId.isEmpty());
        assumeTrue(studentName != null && !studentName.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember(studentId, studentName);
        } catch (InvalidArgumentEx | DuplicateMemberEx e) {
            fail("Should not throw exception for valid student member.");
        }
    }

    @Property
    public void canAddProfMember(String profName) {
        assumeTrue(profName != null && !profName.isEmpty());

        Library library = new Library();
        try {
            library.addProfMember(profName);
        } catch (InvalidArgumentEx | DuplicateMemberEx e) {
            fail("Should not throw exception for valid professor member.");
        }
    }

    @Property
    public void canAddBook(String bookTitle, int copies) {
        assumeTrue(bookTitle != null && !bookTitle.isEmpty());
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addBook(bookTitle, copies);
        } catch (InvalidArgumentEx | DuplicateDocumentEx e) {
            fail("Should not throw exception for valid book.");
        }
    }

    @Property
    public void canAddMagazine(String magazineTitle, int year, int number, int copies) {
        assumeTrue(magazineTitle != null && !magazineTitle.isEmpty());
        assumeTrue(year > 0);
        assumeTrue(number > 0);
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addMagazine(magazineTitle, year, number, copies);
        } catch (InvalidArgumentEx | DuplicateDocumentEx e) {
            fail("Should not throw exception for valid magazine.");
        }
    }

    @Property
    public void canAddReference(String referenceTitle, int copies) {
        assumeTrue(referenceTitle != null && !referenceTitle.isEmpty());
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addReference(referenceTitle, copies);
        } catch (InvalidArgumentEx | DuplicateDocumentEx e) {
            fail("Should not throw exception for valid reference.");
        }
    }

    @Property
    public void cannotBorrowNonExistentDocument(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.borrow(memberName, documentTitle);
            fail("Should throw exception for non-existent document.");
        } catch (InvalidArgumentEx | CannotBorrowEx | DuplicateMemberEx e) {
            // Expected exception
        }
    }

    @Property
    public void canExtendLoan(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.borrow(memberName, documentTitle);
            library.timePass(1);
            library.extend(memberName, documentTitle);
        } catch (InvalidArgumentEx | CannotBorrowEx | CannotExtendEx | DuplicateMemberEx | DuplicateDocumentEx e) {
            fail("Should not throw exception for valid extension.");
        }
    }

    @Property
    public void cannotExtendNonExistentLoan(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.extend(memberName, documentTitle);
            fail("Should throw exception for non-existent loan.");
        } catch (InvalidArgumentEx | CannotExtendEx | DuplicateMemberEx | DuplicateDocumentEx e) {
            // Expected exception
        }
    }

    @Property
    public void canReturnDocument(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.borrow(memberName, documentTitle);
            library.returnDocument(memberName, documentTitle);
        } catch (InvalidArgumentEx | CannotBorrowEx | ExcessiveLateDaysException | DuplicateMemberEx |
                 DuplicateDocumentEx e) {
            fail("Should not throw exception for valid return.");
        }
    }

    @Property
    public void cannotReturnNonExistentLoan(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.returnDocument(memberName, documentTitle);
            fail("Should throw exception for non-existent loan.");
        } catch (InvalidArgumentEx | ExcessiveLateDaysException | DuplicateMemberEx | DuplicateDocumentEx e) {
            // Expected exception
        }
    }

    @Property
    public void totalPenaltyShouldBeNonNegative(String memberName) {
        assumeTrue(memberName != null && !memberName.isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            int penalty = library.getTotalPenalty(memberName);
            assertTrue("Total penalty should be non-negative.", penalty >= 0);
        } catch (InvalidArgumentEx | ExcessiveLateDaysException | DuplicateMemberEx e) {
            fail("Should not throw exception for valid member.");
        }
    }

    @Property
    public void timePassShouldAdvanceTime(int days) {
        assumeTrue(days > 0);

        Library library = new Library();
        try {
            int initialTime = library.now;
            library.timePass(days);
            int currentTime = library.now;
            assertEquals("Time should advance correctly.", initialTime + days, currentTime);
        } catch (InvalidArgumentEx e) {
            fail("Should not throw exception for valid time advancement.");
        }
    }

    @Property
    public void timePassShouldNotGoBackwards(int days) {
        assumeTrue(days < 0);

        Library library = new Library();
        try {
            library.timePass(days);
            fail("Should throw exception for negative days.");
        } catch (InvalidArgumentEx e) {
            // Expected exception
        }
    }
}