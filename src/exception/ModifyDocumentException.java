package exception;

public class ModifyDocumentException extends Exception{
    @Override
    public String getMessage() {
        return "Error editing document";
    }
}
