package main.java.com.intuit.gradebook.enums;

public enum AssignmentCategory {
    HOMEWORK(20),
    QUIZZES(20),
    MIDTERM(25),
    FINAL_EXAM(35);

    private final int weight;

    AssignmentCategory(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
