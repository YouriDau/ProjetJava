package exception;

public class AddDocumentException extends Exception {
    @Override
    public String getMessage() {
        return "Error adding document";
    }
}
