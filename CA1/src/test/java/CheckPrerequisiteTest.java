import CourseSelection.CheckPrerequisite;
import CourseSelection.model.Course;
import CourseSelection.model.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckPrerequisiteTest {
    @Test
    public void testEmptyRecordsAndPrerequisites() {
        List<Record> rec = new ArrayList<>();
        Course course = new Course();
        course.pre = new ArrayList<>();
        Assertions.assertTrue(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testNonEmptyRecordsAndEmptyPrerequisites() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 101, 18, false));
        Course course = new Course();
        course.pre = new ArrayList<>();
        assertTrue(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testEmptyRecordsAndNonEmptyPrerequisites() {
        List<Record> rec = new ArrayList<>();
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        assertFalse(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testRecordsNotMeetingPrerequisites() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 101, 8, false));
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        assertFalse(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testRecordsMeetingPrerequisites() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 101, 10, false));
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        assertTrue(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testMehmanRecordsNotMeetingPrerequisites() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 101, 10, true));
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        assertFalse(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testMehmanRecordsMeetingPrerequisites() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 101, 12, true));
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        assertTrue(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testMultiplePrerequisites() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 101, 12, false));
        rec.add(new Record(1403, 102, 11, false));
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        course.pre.add(102);
        assertTrue(CheckPrerequisite.hasPassedPre(rec, course));
    }

    @Test
    public void testRecordsForDifferentCourses() {
        List<Record> rec = new ArrayList<>();
        rec.add(new Record(1403, 103, 12, false));
        Course course = new Course();
        course.pre = new ArrayList<>();
        course.pre.add(101);
        assertFalse(CheckPrerequisite.hasPassedPre(rec, course));
    }
}
