package main.java.com.intuit.gradebook.entity;

import main.java.com.intuit.gradebook.enums.AssignmentCategory;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String courseName;
    private final int creditHours;
    private List<Assignment> assignments;

    public Course(String courseName, int creditHours) {
        if (creditHours <= 0) {
            throw new IllegalArgumentException("Invalid credit hours");
        }
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.assignments = new ArrayList<>();
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public int getAssignmentCategoryCount (AssignmentCategory category) {
        return assignments.stream()
                .filter(a -> a.getCategory() == category)
                .toList()
                .size();
    }

    public double getCategoryAverage(AssignmentCategory category) {
        return assignments.stream()
                .filter(a -> a.getCategory() == category)
                .mapToDouble(Assignment::getPercentage)
                .average()
                .orElse(0.0);
    }

    public double calculateFinalWeightedScore() {
        double weightedSum = 0;
        int appliedWeight = 0;

        for (AssignmentCategory category : AssignmentCategory.values()) {
            if (getAssignmentCategoryCount(category) == 0) continue;

            double avg = getCategoryAverage(category);
            weightedSum += avg * category.getWeight();
            appliedWeight += category.getWeight();
        }

        return appliedWeight == 0 ? 0 : weightedSum / appliedWeight;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getCourseName() {
        return courseName;
    }
}
