package xyz.yudong520.manageadmin.system.controller.exception;

public class NotAuthorityHtmlException extends Exception{
    private  String message;

    public NotAuthorityHtmlException(String message) {
        super(message);
        this.message = message;
    }

    public NotAuthorityHtmlException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public NotAuthorityHtmlException(Throwable cause) {
        super(cause);
    }

    public NotAuthorityHtmlException(String message, Throwable cause, boolean enableSuppression
            , boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }
}
