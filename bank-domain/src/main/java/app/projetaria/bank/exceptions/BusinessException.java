package app.projetaria.bank.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
