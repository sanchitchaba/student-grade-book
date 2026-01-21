package test.java.com.intuit.gradebook.service.impl;

import main.java.com.intuit.gradebook.entity.Assignment;
import main.java.com.intuit.gradebook.enums.AssignmentCategory;
import main.java.com.intuit.gradebook.exception.CourseNotFoundException;
import main.java.com.intuit.gradebook.exception.StudentNotFoundException;
import main.java.com.intuit.gradebook.service.impl.GradeBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeBookServiceImplTest {
    private GradeBookServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new GradeBookServiceImpl();
    }

    @Test
    void addStudent_shouldAddStudentSuccessfully() {
        service.addStudent("S1", "Alice");

        assertDoesNotThrow(() -> service.calculateGPA("S1"));
    }

    @Test
    void enrollInCourse_shouldEnrollStudentInCourse() {
        service.addStudent("S1", "Alice");

        service.enrollInCourse("S1", "Math", 3);

        assertDoesNotThrow(() -> service.getCourseGrade("S1", "Math"));
    }

    @Test
    void enrollInCourse_shouldThrowException_ifStudentNotFound() {
        assertThrows(StudentNotFoundException.class, () -> service.enrollInCourse("INVALID", "Math", 3));
    }

    @Test
    void addAssignment_shouldAddAssignmentToCourse() {
        service.addStudent("S1", "Alice");
        service.enrollInCourse("S1", "Math", 3);

        Assignment hw = new Assignment("HW1", 80, 100, AssignmentCategory.HOMEWORK);

        service.addAssignment("S1", "Math", hw);

        double avg = service.getCategoryAverage("S1", "Math", AssignmentCategory.HOMEWORK);

        assertEquals(80.0, avg);
    }

    @Test
    void addAssignment_shouldThrowException_ifCourseNotFound() {
        service.addStudent("S1", "Alice");

        Assignment hw = new Assignment("HW1", 80, 100, AssignmentCategory.HOMEWORK);

        assertThrows(CourseNotFoundException.class, () -> service.addAssignment("S1", "Physics", hw));
    }

    @Test
    void getCategoryAverage_shouldReturnZero_ifNoAssignmentsInCategory() {
        service.addStudent("S1", "Alice");
        service.enrollInCourse("S1", "Math", 3);

        double avg = service.getCategoryAverage("S1", "Math", AssignmentCategory.QUIZZES);

        assertEquals(0.0, avg);
    }

    @Test
    void getCourseGrade_shouldReturnFormattedScoreAndLetterGrade() {
        service.addStudent("S1", "Alice");
        service.enrollInCourse("S1", "Math", 3);

        service.addAssignment("S1", "Math", new Assignment("HW1", 90, 100, AssignmentCategory.HOMEWORK));
        service.addAssignment("S1", "Math", new Assignment("Quiz1", 80, 100, AssignmentCategory.QUIZZES));
        service.addAssignment("S1", "Math", new Assignment("Mid", 70, 100, AssignmentCategory.MIDTERM));
        service.addAssignment("S1", "Math", new Assignment("Final", 100, 100, AssignmentCategory.FINAL_EXAM));

        String grade = service.getCourseGrade("S1", "Math");

        assertTrue(grade.contains("%"));
        assertTrue(grade.contains("("));
        assertTrue(grade.contains(")"));
    }

    @Test
    void calculateGPA_shouldCalculateWeightedGpaAcrossCourses() {
        service.addStudent("S1", "Alice");

        service.enrollInCourse("S1", "Math", 3);
        service.enrollInCourse("S1", "Science", 4);

        service.addAssignment("S1", "Math", new Assignment("HW", 90, 100, AssignmentCategory.HOMEWORK));
        service.addAssignment("S1", "Science", new Assignment("HW", 70, 100, AssignmentCategory.HOMEWORK));

        double gpa = service.calculateGPA("S1");

        assertTrue(gpa > 0);
        assertTrue(gpa <= 4.0);
    }

    @Test
    void calculateGPA_shouldReturnZero_ifNoCourses() {
        service.addStudent("S1", "Alice");

        assertEquals(0.0, service.calculateGPA("S1"));
    }

    @Test
    void generateTranscript_shouldReturnFormattedTranscript() {
        service.addStudent("S1", "Alice");
        service.enrollInCourse("S1", "Math", 3);

        service.addAssignment("S1", "Math", new Assignment("HW1", 85, 100, AssignmentCategory.HOMEWORK));

        String transcript = service.generateTranscript("S1");

        assertTrue(transcript.contains("Transcript for Alice"));
        assertTrue(transcript.contains("Math"));
        assertTrue(transcript.contains("Cumulative GPA"));
    }

    @Test
    void generateTranscript_shouldThrowException_ifStudentNotFound() {
        assertThrows(StudentNotFoundException.class, () -> service.generateTranscript("INVALID"));
    }
}
