package xyz.yudong520.manageadmin.system.controller.exception;

import lombok.Data;

@Data
public class NotAuthorityJsonException extends Exception {
    private  String message;

    public NotAuthorityJsonException(String message) {
        super(message);
        this.message = message;
    }

    public NotAuthorityJsonException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public NotAuthorityJsonException(Throwable cause) {
        super(cause);
    }

    public NotAuthorityJsonException(String message, Throwable cause, boolean enableSuppression
            , boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }
}
