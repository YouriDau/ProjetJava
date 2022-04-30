package exception;

public class DBException extends Exception {
    private String errorMessage;

    public DBException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
