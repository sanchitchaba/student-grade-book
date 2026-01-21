package test.java.com.intuit.gradebook.entity;

import main.java.com.intuit.gradebook.entity.Assignment;
import main.java.com.intuit.gradebook.entity.Course;
import main.java.com.intuit.gradebook.enums.AssignmentCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Assignment assignment(String name, double earned, double possible, AssignmentCategory category) {
        return new Assignment(name, earned, possible, category);
    }

    @Test
    void constructor_shouldCreateCourse_whenCreditHoursValid() {
        Course course = new Course("Math", 4);

        assertEquals("Math", course.getCourseName());
        assertEquals(4, course.getCreditHours());
    }

    @Test
    void constructor_shouldThrowException_whenCreditHoursZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Course("Math", 0));

        assertEquals("Invalid credit hours", ex.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenCreditHoursNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Course("Math", -3));

        assertEquals("Invalid credit hours", ex.getMessage());
    }

    @Test
    void getAssignmentCategoryCount_shouldReturnZero_whenNoAssignments() {
        Course course = new Course("Physics", 3);

        assertEquals(0, course.getAssignmentCategoryCount(AssignmentCategory.HOMEWORK));
    }

    @Test
    void getAssignmentCategoryCount_shouldReturnCorrectCount() {
        Course course = new Course("Physics", 3);

        course.addAssignment(assignment("HW1", 80, 100, AssignmentCategory.HOMEWORK));
        course.addAssignment(assignment("HW2", 90, 100, AssignmentCategory.HOMEWORK));
        course.addAssignment(assignment("Quiz", 70, 100, AssignmentCategory.MIDTERM));

        assertEquals(2, course.getAssignmentCategoryCount(AssignmentCategory.HOMEWORK));
        assertEquals(1, course.getAssignmentCategoryCount(AssignmentCategory.MIDTERM));
        assertEquals(0, course.getAssignmentCategoryCount(AssignmentCategory.QUIZZES));
    }

    @Test
    void getCategoryAverage_shouldReturnZero_whenCategoryHasNoAssignments() {
        Course course = new Course("Chemistry", 3);

        assertEquals(0.0, course.getCategoryAverage(AssignmentCategory.MIDTERM), 0.0001);
    }

    @Test
    void getCategoryAverage_shouldCalculateCorrectAverage() {
        Course course = new Course("Chemistry", 3);

        course.addAssignment(assignment("HW1", 80, 100, AssignmentCategory.HOMEWORK));
        course.addAssignment(assignment("HW2", 60, 100, AssignmentCategory.HOMEWORK));

        assertEquals(70.0, course.getCategoryAverage(AssignmentCategory.HOMEWORK), 0.0001);
    }

    @Test
    void getCategoryAverage_shouldIncludeZeroScores() {
        Course course = new Course("Chemistry", 3);

        course.addAssignment(assignment("HW1", 0, 100, AssignmentCategory.HOMEWORK));
        course.addAssignment(assignment("HW2", 100, 100, AssignmentCategory.HOMEWORK));

        assertEquals(50.0, course.getCategoryAverage(AssignmentCategory.HOMEWORK), 0.0001);
    }

    @Test
    void calculateFinalWeightedScore_shouldReturnZero_whenNoAssignments() {
        Course course = new Course("Biology", 4);

        assertEquals(0.0, course.calculateFinalWeightedScore(), 0.0001);
    }

    @Test
    void calculateFinalWeightedScore_shouldExcludeCategoriesWithNoAssignments() {
        Course course = new Course("Biology", 4);
        course.addAssignment(assignment("HW", 100, 100, AssignmentCategory.HOMEWORK));

        assertEquals(100.0, course.calculateFinalWeightedScore(), 0.0001);
    }

    @Test
    void calculateFinalWeightedScore_shouldIncludeZeroScoreCategories() {
        Course course = new Course("Biology", 4);

        course.addAssignment(assignment("HW", 0, 100, AssignmentCategory.HOMEWORK));
        course.addAssignment(assignment("Final", 100, 100, AssignmentCategory.FINAL_EXAM));

        double expected = (0.0 * AssignmentCategory.HOMEWORK.getWeight() + 100 * AssignmentCategory.FINAL_EXAM.getWeight()) / (AssignmentCategory.HOMEWORK.getWeight() + AssignmentCategory.FINAL_EXAM.getWeight());

        assertEquals(expected, course.calculateFinalWeightedScore(), 0.0001);
    }

    @Test
    void calculateFinalWeightedScore_shouldHandleMultipleCategoriesCorrectly() {
        Course course = new Course("Math", 4);

        course.addAssignment(assignment("HW", 80, 100, AssignmentCategory.HOMEWORK));
        course.addAssignment(assignment("Quiz", 90, 100, AssignmentCategory.QUIZZES));
        course.addAssignment(assignment("Mid", 70, 100, AssignmentCategory.MIDTERM));
        course.addAssignment(assignment("Final", 100, 100, AssignmentCategory.FINAL_EXAM));

        double expected = (80.0 * AssignmentCategory.HOMEWORK.getWeight() + 90 * AssignmentCategory.QUIZZES.getWeight() + 70 * AssignmentCategory.MIDTERM.getWeight() + 100 * AssignmentCategory.FINAL_EXAM.getWeight())
                        / (AssignmentCategory.HOMEWORK.getWeight() + AssignmentCategory.QUIZZES.getWeight() + AssignmentCategory.MIDTERM.getWeight() + AssignmentCategory.FINAL_EXAM.getWeight());

        assertEquals(expected, course.calculateFinalWeightedScore(), 0.0001);
    }
}
