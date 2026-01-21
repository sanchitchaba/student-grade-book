package main.java.com.intuit.gradebook.entity;

import main.java.com.intuit.gradebook.enums.AssignmentCategory;

public class Assignment {
    private final String name;
    private double pointsEarned;
    private final double pointsPossible;
    private final AssignmentCategory category;

    public Assignment(String name, double earned, double possible, AssignmentCategory category) {
        if (possible <= 0 || earned < 0 || earned > possible) {
            throw new IllegalArgumentException("Invalid assignment score");
        }
        this.name = name;
        this.pointsEarned = earned;
        this.pointsPossible = possible;
        this.category = category;
    }

    public AssignmentCategory getCategory() {
        return category;
    }

    public double getPercentage() {
        return (pointsEarned / pointsPossible) * 100.0;
    }

    public String getName () {
        return name;
    }
}
