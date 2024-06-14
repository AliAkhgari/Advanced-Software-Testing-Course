package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class LoanPropertiesTest {
    @Property
    public void loanConstructorShouldBeValidWithValidFields(int date) {
        try {
            assumeTrue(date >= 0);

            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);
        } catch (InvalidArgumentEx e) {
            fail("Exception should not be thrown for non-empty member and doc.");
        }
    }

    @Property
    public void penaltyIsNonNegative(int date, int now) {
        try {
            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);
            if (loan.getPenalty(now) < 0) {
                fail("Penalty should be non-negative.");
            }

            Member member1 = new Professor("Khamespanah");
            Document document1 = new Reference("Encyclopedias");
            Loan loan1 = new Loan(member1, document1, date);
            if (loan1.getPenalty(now) < 0) {
                fail("Penalty should be non-negative.");
            }

            Member member2 = new Professor("Khamespanah");
            Document document2 = new Magazine("The Believer", 1990, 10);
            Loan loan2 = new Loan(member2, document2, date);
            if (loan2.getPenalty(now) < 0) {
                fail("Penalty should be non-negative.");
            }

        } catch (InvalidArgumentEx | ExcessiveLateDaysException ignored) {
        }
    }

    @Property
    public void loanExtensionShouldWorkCorrectly(int date, int now) {
        try {
            assumeTrue(now > date);

            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);

            int initialDueDate = date + document.loanDuration();
            loan.extend(now);
            int extendedDueDate = initialDueDate + document.loanDuration();

            assertEquals("Due date should be extended correctly.", extendedDueDate, loan.dueDate);

        } catch (InvalidArgumentEx | CannotExtendEx ignored) {
        }
    }

    @Property
    public void cannotExtendLoanBeyondLimit(int date, int now) {
        try {
            assumeTrue(now > date);

            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);

            loan.extend(now);
            loan.extend(now + 1);
            try {
                loan.extend(now + 2);
                fail("Loan should not be extendable more than twice.");
            } catch (CannotExtendEx e) {
                // Expected exception
            }

        } catch (InvalidArgumentEx | CannotExtendEx ignored) {
        }
    }

    @Property
    public void cannotExtendMagazines(int date) {
        try {
            Member member = new Professor("Khamespanah");
            Document document = new Magazine("A Tale of Two Cities", 1990, 10);
            Loan loan = new Loan(member, document, date);

            try {
                loan.extend(date + 1);
                fail("Loan should not be extendable for Magazines.");
            } catch (CannotExtendEx e) {
                // Expected exception
                System.out.println(e);
            }

        } catch (InvalidArgumentEx ignored) {
        }
    }

    @Property
    public void prevLoanShouldBeBiggerThanPrevAfterPenalize(int date, int now) {
        try {
            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);
            int prevPenalty = member.getPrevPenalty();

            assumeTrue(now > loan.dueDate);
            loan.return_(now);
            int currPenalty = member.getPrevPenalty();

            if (currPenalty < prevPenalty) {
                fail("Current penalty after penalize should not be lower than prev penalty.");
            }
        } catch (InvalidArgumentEx | ExcessiveLateDaysException ignored) {
        }
    }

    @Property
    public void isForShouldReturnTrueForSameDocument(int date) {
        try {
            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);
            assertTrue("isFor should return true for the same document.", loan.isFor(document));
        } catch (InvalidArgumentEx ignored) {
        }
    }

    @Property
    public void isByShouldReturnTrueForSameMember(int date) {
        try {
            Member member = new Professor("Khamespanah");
            Document document = new Book("A Tale of Two Cities");
            Loan loan = new Loan(member, document, date);
            assertTrue("isBy should return true for the same member.", loan.isBy(member));
        } catch (InvalidArgumentEx ignored) {
        }
    }
}
