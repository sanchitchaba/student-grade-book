import main.java.com.intuit.gradebook.entity.Assignment;
import main.java.com.intuit.gradebook.enums.AssignmentCategory;
import main.java.com.intuit.gradebook.service.GradeBookService;
import main.java.com.intuit.gradebook.service.impl.GradeBookServiceImpl;

public class Main {
    public static void main(String[] args) {
        GradeBookService gb = new GradeBookServiceImpl();

        gb.addStudent("S1", "Alice");
        gb.addStudent("S2", "Bob");
        gb.addStudent("S3", "Charlie");

        gb.enrollInCourse("S1", "Math", 4);
        gb.enrollInCourse("S1", "Physics", 3);
        gb.enrollInCourse("S2", "Chemistry", 4);
        gb.enrollInCourse("S2", "Biology", 3);
        gb.enrollInCourse("S3", "History", 3);
        gb.enrollInCourse("S3", "Economics", 4);
        gb.enrollInCourse("S3", "CS", 5);

        gb.addAssignment("S1", "Math", new Assignment("HW1", 90, 100, AssignmentCategory.HOMEWORK));
        gb.addAssignment("S1", "Math", new Assignment("Final", 95, 100, AssignmentCategory.FINAL_EXAM));
        gb.addAssignment("S1", "Physics", new Assignment("Midterm", 78, 100, AssignmentCategory.MIDTERM));

        gb.addAssignment("S2", "Chemistry", new Assignment("Quiz1", 65, 100, AssignmentCategory.QUIZZES));
        gb.addAssignment("S2", "Biology", new Assignment("Final", 88, 100, AssignmentCategory.FINAL_EXAM));

        gb.addAssignment("S3", "CS", new Assignment("HW", 100, 100, AssignmentCategory.HOMEWORK));
        gb.addAssignment("S3", "CS", new Assignment("Midterm", 92, 100, AssignmentCategory.MIDTERM));
        gb.addAssignment("S3", "CS", new Assignment("Final", 96, 100, AssignmentCategory.FINAL_EXAM));

        System.out.println(gb.generateTranscript("S1"));
        System.out.println();
        System.out.println(gb.generateTranscript("S2"));
        System.out.println();
        System.out.println(gb.generateTranscript("S3"));
    }
}