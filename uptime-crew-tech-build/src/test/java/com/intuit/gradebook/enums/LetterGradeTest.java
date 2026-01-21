package test.java.com.intuit.gradebook.enums;

import main.java.com.intuit.gradebook.enums.LetterGrade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LetterGradeTest {
    @Test
    void fromScore_shouldReturnA_for90AndAbove() {
        assertEquals(LetterGrade.A, LetterGrade.fromScore(90));
        assertEquals(LetterGrade.A, LetterGrade.fromScore(95));
        assertEquals(LetterGrade.A, LetterGrade.fromScore(100));
        assertEquals(LetterGrade.A, LetterGrade.fromScore(120));
    }

    @Test
    void fromScore_shouldReturnB_for80To89() {
        assertEquals(LetterGrade.B, LetterGrade.fromScore(80));
        assertEquals(LetterGrade.B, LetterGrade.fromScore(85));
        assertEquals(LetterGrade.B, LetterGrade.fromScore(89.99));
    }

    @Test
    void fromScore_shouldReturnC_for70To79() {
        assertEquals(LetterGrade.C, LetterGrade.fromScore(70));
        assertEquals(LetterGrade.C, LetterGrade.fromScore(75));
        assertEquals(LetterGrade.C, LetterGrade.fromScore(79.99));
    }

    @Test
    void fromScore_shouldReturnD_for60To69() {
        assertEquals(LetterGrade.D, LetterGrade.fromScore(60));
        assertEquals(LetterGrade.D, LetterGrade.fromScore(65));
        assertEquals(LetterGrade.D, LetterGrade.fromScore(69.99));
    }

    @Test
    void fromScore_shouldReturnF_forBelow60() {
        assertEquals(LetterGrade.F, LetterGrade.fromScore(59.99));
        assertEquals(LetterGrade.F, LetterGrade.fromScore(0));
        assertEquals(LetterGrade.F, LetterGrade.fromScore(-10));
    }

    @Test
    void gpa_shouldMatchCorrectValues() {
        assertEquals(4.0, LetterGrade.A.getGpa());
        assertEquals(3.0, LetterGrade.B.getGpa());
        assertEquals(2.0, LetterGrade.C.getGpa());
        assertEquals(1.0, LetterGrade.D.getGpa());
        assertEquals(0.0, LetterGrade.F.getGpa());
    }
}
