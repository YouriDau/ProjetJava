package exception;

public class AddPromotionException extends Exception{
    @Override
    public String getMessage() {
        return "Error adding promotion";
    }
}
