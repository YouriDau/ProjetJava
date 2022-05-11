package exception;

public class SingletonConnectionException extends Exception {
    private String errorMessage;

    public SingletonConnectionException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorTitle() {
        return "Erreur connection à la base de donnée";
    }
}
