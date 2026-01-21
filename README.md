# Student Gradebook System

A Java-based student gradebook system that manages courses, assignments, weighted grading categories, letter grades, and GPA calculation. Designed with clean OOP principles, exception handling, and comprehensive unit testing.

## Features
- Manage students and course enrollments
- Support weighted grading categories:
    - Homework (20%)
    - Quizzes (20%)
    - Midterm (25%)
    - Final Exam (35%)
- Calculate:
    - Category averages
    - Final weighted course grades
    - Letter grades (A–F)
    - Cumulative GPA (credit-hour weighted)
- Generate formatted student transcripts
- Robust validation and custom exception handling
- Full JUnit test coverage for core logic

## Tech Stack
- Java 17
- JUnit 5
- Maven
- Object-Oriented Design (SOLID principles)

## Project Structure
gradebook/
├── src/
│   ├── main/java/com/intuit/gradebook/
│   │   ├── entity/        # Student, Course, Assignment
│   │   ├── enums/         # AssignmentCategory, LetterGrade
│   │   ├── exception/     # Custom runtime exceptions
│   │   ├── service/       # GradeBookService interface
│   │   └── service/impl/  # GradeBookServiceImpl
│   └── test/java/com/intuit/gradebook/
│       ├── entity/        # Entity unit tests
│       ├── enums/         # Enum tests
│       └── service/impl/  # Service tests
└── pom.xml

## Setup Instructions
- Prerequisites
    - Java 17+
    - Maven 3.8+

- Build & Run Tests
    '''mvn clean test'''

- Run Application (Main class)
      '''mvn exec:java'''

## Sample Usage
- Input (Programmatic)
```java
service.addStudent("S1", "Alice");
service.enrollInCourse("S1", "Math", 3);

service.addAssignment("S1", "Math",
    new Assignment("HW1", 85, 100, AssignmentCategory.HOMEWORK));

service.addAssignment("S1", "Math",
    new Assignment("Final", 90, 100, AssignmentCategory.FINAL_EXAM));'''

- Output
'''Transcript for Alice
Math: 88.75% (B)
Cumulative GPA: 3.00'''

## Key Assumptions
- All scores are percentages, derived from pointsEarned / pointsPossible.
- Missing assignments in a category result in a category average of 0.
- Categories with no assignments are still considered in grading logic.
- GPA is calculated using credit-hour weighted average.
- Derived values (final score, GPA) are not stored, only computed.
- All custom exceptions extend RuntimeException and include internal status codes.
- Thread safety is not required (single-user academic system).

## Build & Test
mvn clean test
