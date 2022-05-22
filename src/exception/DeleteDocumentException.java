package exception;

public class DeleteDocumentException extends Exception{
    @Override
    public String getMessage() {
        return "Error deleting document";
    }
}
