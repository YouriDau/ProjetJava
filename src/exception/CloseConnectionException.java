package exception;

public class CloseConnectionException extends Exception{
    @Override
    public String getMessage() {
        return  "Error closing connection";
    }
}
