package ir.ramtung.impl1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitQuickcheck.class)
public class MemberPropertiesTest {
    @Property
    public void memberNameIsNotEmpty(@When(satisfies = "!#_.isEmpty()") String name) {
        try {
            Member member = new Professor(name);
        } catch (InvalidArgumentEx e) {
            fail("Exception should not be thrown for non-empty name");
        }
    }

    @Property
    public void memberIsNamed(@When(satisfies = "!#_.isEmpty()") String name) {
        try {
            Member member = new Professor(name);
            if (!member.isNamed(name)) {
                fail("The name of the member should be equal to what we defined.");
            }
        } catch (InvalidArgumentEx ignored) {
        }
    }

    @Property
    public void newMemberPenaltyIsZero(@When(satisfies = "!#_.isEmpty()") String name) {
        try {
            Member member = new Professor(name);
            if (member.getPrevPenalty() != 0) {
                fail("The prevPenalty for a new member should be 0");
            }
        } catch (InvalidArgumentEx ignored) {
        }
    }
}
