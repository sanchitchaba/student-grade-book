package main.java.com.intuit.gradebook.exception;

public class StudentNotFoundException extends RuntimeException {
    private int internalStatusCode;

    public StudentNotFoundException(int internalStatusCode, String message) {
        super(message);
        this.internalStatusCode = internalStatusCode;
    }

    public int getInternalStatusCode () {
        return internalStatusCode;
    }
}
