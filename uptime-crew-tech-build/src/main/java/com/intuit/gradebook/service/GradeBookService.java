package main.java.com.intuit.gradebook.service;

import main.java.com.intuit.gradebook.entity.Assignment;
import main.java.com.intuit.gradebook.enums.AssignmentCategory;

public interface GradeBookService {
    void addStudent(String studentId, String name);
    void enrollInCourse(String studentId, String courseName, int creditHours);
    void addAssignment(String studentId, String courseName, Assignment assignment);

    double getCategoryAverage(String studentId, String courseName, AssignmentCategory category);
    String getCourseGrade(String studentId, String courseName);
    double calculateGPA(String studentId);
    String generateTranscript(String studentId);
}
