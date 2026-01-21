package main.java.com.intuit.gradebook.service.impl;

import main.java.com.intuit.gradebook.entity.Assignment;
import main.java.com.intuit.gradebook.entity.Course;
import main.java.com.intuit.gradebook.entity.Student;
import main.java.com.intuit.gradebook.enums.AssignmentCategory;
import main.java.com.intuit.gradebook.enums.LetterGrade;
import main.java.com.intuit.gradebook.exception.CourseNotFoundException;
import main.java.com.intuit.gradebook.exception.StudentNotFoundException;
import main.java.com.intuit.gradebook.service.GradeBookService;
import java.util.HashMap;
import java.util.Map;

public class GradeBookServiceImpl implements GradeBookService {
    private Map<String, Student> students = new HashMap<>();

    @Override
    public void addStudent(String studentId, String name) {
        students.put(studentId, new Student(studentId, name));
    }

    @Override
    public void enrollInCourse(String studentId, String courseName, int creditHours) {
        Student student = getStudent(studentId);
        student.enroll(new Course(courseName, creditHours));
    }

    @Override
    public void addAssignment(String studentId, String courseName, Assignment assignment) {
        getCourse(studentId, courseName).addAssignment(assignment);
    }

    @Override
    public double getCategoryAverage(String studentId, String courseName, AssignmentCategory category) {
        return getCourse(studentId, courseName).getCategoryAverage(category);
    }

    @Override
    public String getCourseGrade(String studentId, String courseName) {
        Course course = getCourse(studentId, courseName);
        double score = course.calculateFinalWeightedScore();
        return String.format("%.2f%% (%s)", score, LetterGrade.fromScore(score));
    }

    @Override
    public double calculateGPA(String studentId) {
        Student student = getStudent(studentId);

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course course : student.getCourses()) {
            double score = course.calculateFinalWeightedScore();
            LetterGrade grade = LetterGrade.fromScore(score);

            totalPoints += grade.getGpa() * course.getCreditHours();
            totalCredits += course.getCreditHours();
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }

    @Override
    public String generateTranscript(String studentId) {
        Student student = getStudent(studentId);
        StringBuilder sb = new StringBuilder();

        sb.append("Transcript for ").append(student.getName()).append("\n");

        for (Course course : student.getCourses()) {
            sb.append(course.getCourseName())
                    .append(": ")
                    .append(getCourseGrade(studentId, course.getCourseName()))
                    .append("\n");
        }

        sb.append("Cumulative GPA: ")
                .append(String.format("%.2f", calculateGPA(studentId)));

        return sb.toString();
    }

    private Student getStudent(String id) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(1001, "Student not found");
        }
        return students.get(id);
    }

    private Course getCourse(String studentId, String courseName) {
        Course course = getStudent(studentId).getCourse(courseName);
        if (course == null) {
            throw new CourseNotFoundException(1002, "Course not found");
        }
        return course;
    }
}
