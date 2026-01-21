package test.java.com.intuit.gradebook.entity;

import main.java.com.intuit.gradebook.entity.Assignment;
import main.java.com.intuit.gradebook.enums.AssignmentCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {
    @Test
    void constructor_shouldCreateAssignment_whenInputsAreValid() {
        Assignment assignment = new Assignment(
                "HW1",
                80,
                100,
                AssignmentCategory.HOMEWORK
        );

        assertEquals("HW1", assignment.getName());
        assertEquals(AssignmentCategory.HOMEWORK, assignment.getCategory());
        assertEquals(80.0, assignment.getPercentage(), 0.0001);
    }

    @Test
    void getPercentage_shouldReturnCorrectPercentage() {
        Assignment assignment = new Assignment(
                "Quiz",
                45,
                50,
                AssignmentCategory.FINAL_EXAM
        );

        assertEquals(90.0, assignment.getPercentage(), 0.0001);
    }

    @Test
    void constructor_shouldThrowException_whenPointsPossibleIsZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Assignment("HW", 10, 0, AssignmentCategory.HOMEWORK)
        );

        assertEquals("Invalid assignment score", ex.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenPointsPossibleIsNegative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Assignment("HW", 10, -100, AssignmentCategory.HOMEWORK)
        );

        assertEquals("Invalid assignment score", ex.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenPointsEarnedIsNegative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Assignment("HW", -1, 100, AssignmentCategory.HOMEWORK)
        );

        assertEquals("Invalid assignment score", ex.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenPointsEarnedExceedsPointsPossible() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Assignment("HW", 110, 100, AssignmentCategory.HOMEWORK)
        );

        assertEquals("Invalid assignment score", ex.getMessage());
    }

    @Test
    void getPercentage_shouldHandleZeroEarnedCorrectly() {
        Assignment assignment = new Assignment(
                "Final",
                0,
                100,
                AssignmentCategory.FINAL_EXAM
        );

        assertEquals(0.0, assignment.getPercentage(), 0.0001);
    }

    @Test
    void multipleCallsToGetPercentage_shouldReturnSameValue() {
        Assignment assignment = new Assignment(
                "Midterm",
                75,
                100,
                AssignmentCategory.MIDTERM
        );

        double first = assignment.getPercentage();
        double second = assignment.getPercentage();

        assertEquals(first, second, 0.0001);
    }
}
