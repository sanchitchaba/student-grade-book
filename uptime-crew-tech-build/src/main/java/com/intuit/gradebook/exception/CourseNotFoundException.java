package main.java.com.intuit.gradebook.exception;

public class CourseNotFoundException extends RuntimeException {
    private int internalStatusCode;

    public CourseNotFoundException(int internalStatusCode, String message) {
        super(message);
        this.internalStatusCode = internalStatusCode;
    }

    public int getInternalStatusCode () {
        return internalStatusCode;
    }
}
