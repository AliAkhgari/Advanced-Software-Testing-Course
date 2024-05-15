package CourseSelection.model;

public class Record {
    int termId;
    public int courseId;
    public double grade;
    public boolean isMehman;

    public Record(int termId, int courseId, double grade, boolean isMehman) {
        this.termId = termId;
        this.courseId = courseId;
        this.grade = grade;
        this.isMehman = isMehman;
    }
}
