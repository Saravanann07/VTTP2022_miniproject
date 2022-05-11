package vttp2022.project.Stock.exceptions;

public class UserException extends Exception {
    private String reason;

    public UserException(String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
