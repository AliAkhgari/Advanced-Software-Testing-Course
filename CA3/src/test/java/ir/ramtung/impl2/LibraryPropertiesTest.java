package ir.ramtung.impl2;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import ir.ramtung.sts01.LibraryException;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class LibraryPropertiesTest {

    @Property
    public void canAddStudentMember(String studentId, String studentName) {
        assumeTrue(studentId != null && !studentId.trim().isEmpty());
        assumeTrue(studentName != null && !studentName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember(studentId, studentName);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid student member.");
        }
    }

    @Property
    public void cannotAddStudentMemberWithEmptyId(String studentName) {
        assumeTrue(studentName != null && !studentName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("", studentName);
            fail("Should throw exception for empty student ID.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddStudentMemberWithEmptyName(String studentId) {
        assumeTrue(studentId != null && !studentId.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember(studentId, "");
            fail("Should throw exception for empty student name.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddDuplicateStudentMember(String studentId, String studentName) {
        assumeTrue(studentId != null && !studentId.trim().isEmpty());
        assumeTrue(studentName != null && !studentName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember(studentId, studentName);
            library.addStudentMember(studentId + "2", studentName);
            fail("Should throw exception for duplicate student member.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void canAddProfMember(String profName) {
        assumeTrue(profName != null && !profName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addProfMember(profName);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid professor member.");
        }
    }

    @Property
    public void cannotAddProfMemberWithEmptyName() {
        Library library = new Library();
        try {
            library.addProfMember(" ");
            fail("Should throw exception for empty professor name.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddDuplicateProfMember(String profName) {
        assumeTrue(profName != null && !profName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addProfMember(profName);
            library.addProfMember(profName);
            fail("Should throw exception for duplicate professor member.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void canAddBook(String bookTitle, int copies) {
        assumeTrue(bookTitle != null && !bookTitle.trim().isEmpty());
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addBook(bookTitle, copies);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid book.");
        }
    }

    @Property
    public void cannotAddBookWithEmptyTitle(int copies) {
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addBook(" ", copies);
            fail("Should throw exception for empty book title.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddDuplicateBook(String bookTitle, int copies) {
        assumeTrue(bookTitle != null && !bookTitle.trim().isEmpty());
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addBook(bookTitle, copies);
            library.addBook(bookTitle, copies);
            fail("Should throw exception for duplicate book.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void canAddMagazine(String magazineTitle, int year, int number, int copies) {
        assumeTrue(magazineTitle != null && !magazineTitle.trim().isEmpty());
        assumeTrue(year > 0);
        assumeTrue(number > 0);
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addMagazine(magazineTitle, year, number, copies);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid magazine.");
        }
    }

    @Property
    public void cannotAddMagazineWithEmptyTitle(int year, int number, int copies) {
        assumeTrue(year > 0);
        assumeTrue(number > 0);
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addMagazine(" ", year, number, copies);
            fail("Should throw exception for empty magazine title.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddMagazineWithInvalidYear(String magazineTitle, int number, int copies) {
        assumeTrue(magazineTitle != null && !magazineTitle.trim().isEmpty());
        assumeTrue(number > 0);
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addMagazine(magazineTitle, -1, number, copies);
            fail("Should throw exception for invalid magazine year.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddMagazineWithInvalidNumber(String magazineTitle, int year, int copies) {
        assumeTrue(magazineTitle != null && !magazineTitle.trim().isEmpty());
        assumeTrue(year > 0);
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addMagazine(magazineTitle, year, -1, copies);
            fail("Should throw exception for invalid magazine number.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddDuplicateMagazine(String magazineTitle, int year, int number, int copies) {
        assumeTrue(magazineTitle != null && !magazineTitle.trim().isEmpty());
        assumeTrue(year > 0);
        assumeTrue(number > 0);
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addMagazine(magazineTitle, year, number, copies);
            library.addMagazine(magazineTitle, year, number, copies);
            fail("Should throw exception for duplicate magazine.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void canAddReference(String referenceTitle, int copies) {
        assumeTrue(referenceTitle != null && !referenceTitle.trim().isEmpty());
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addReference(referenceTitle, copies);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid reference.");
        }
    }

    @Property
    public void cannotAddReferenceWithEmptyTitle(int copies) {
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addReference(" ", copies);
            fail("Should throw exception for empty reference title.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotAddDuplicateReference(String referenceTitle, int copies) {
        assumeTrue(referenceTitle != null && !referenceTitle.trim().isEmpty());
        assumeTrue(copies > 0);

        Library library = new Library();
        try {
            library.addReference(referenceTitle, copies);
            library.addReference(referenceTitle, copies);
            fail("Should throw exception for duplicate reference.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void canBorrowDocument(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.trim().isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.borrow(memberName, documentTitle);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid borrow.");
        }
    }

    @Property
    public void cannotBorrowNonExistentDocument(String memberName) {
        assumeTrue(memberName != null && !memberName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.borrow(memberName, "Nonexistent Book");
            fail("Should throw exception for non-existent document.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotBorrowDocumentWithNoCopiesAvailable(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.trim().isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.borrow(memberName, documentTitle);
            library.borrow(memberName, documentTitle);
            fail("Should throw exception when no copies are available.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void canReturnDocument(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.trim().isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.borrow(memberName, documentTitle);
            library.returnDocument(memberName, documentTitle);
        } catch (LibraryException e) {
            fail("Should not throw exception for valid document return.");
        }
    }

    @Property
    public void cannotReturnDocumentNotBorrowed(String memberName, String documentTitle) {
        assumeTrue(memberName != null && !memberName.trim().isEmpty());
        assumeTrue(documentTitle != null && !documentTitle.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.addBook(documentTitle, 1);
            library.returnDocument(memberName, documentTitle);
            fail("Should throw exception when returning a document not borrowed.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }

    @Property
    public void cannotReturnNonExistentDocument(String memberName) {
        assumeTrue(memberName != null && !memberName.trim().isEmpty());

        Library library = new Library();
        try {
            library.addStudentMember("studentId", memberName);
            library.returnDocument(memberName, "Nonexistent Book");
            fail("Should throw exception when returning a non-existent document.");
        } catch (LibraryException e) {
            // Expected exception
        }
    }
}