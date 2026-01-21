package main.java.com.intuit.gradebook.enums;

public enum LetterGrade {
    A(4.0),
    B(3.0),
    C(2.0),
    D(1.0),
    F(0.0);

    private final double gpa;

    LetterGrade(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public static LetterGrade fromScore(double score) {
        if (score >= 90) return A;
        if (score >= 80) return B;
        if (score >= 70) return C;
        if (score >= 60) return D;
        return F;
    }
}
