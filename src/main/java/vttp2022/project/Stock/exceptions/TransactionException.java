package vttp2022.project.Stock.exceptions;

public class TransactionException extends Exception {
    private String reason;

    public TransactionException(String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
    
}
