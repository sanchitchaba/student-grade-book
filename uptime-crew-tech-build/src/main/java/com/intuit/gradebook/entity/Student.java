package main.java.com.intuit.gradebook.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private final String studentId;
    private final String name;
    private Map<String, Course> courses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new HashMap<>();
    }

    public void enroll(Course course) {
        courses.put(course.getCourseName(), course);
    }

    public Course getCourse(String courseName) {
        return courses.get(courseName);
    }

    public Collection<Course> getCourses() {
        return courses.values();
    }

    public String getName() {
        return name;
    }

    public String getStudentId () {
        return studentId;
    }
}
