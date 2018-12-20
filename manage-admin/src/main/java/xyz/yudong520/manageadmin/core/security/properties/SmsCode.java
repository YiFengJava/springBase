package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsCode implements Serializable {

    private int length=6;

    private int expireIn=180;

    private String url;

    public SmsCode() {
    }

    public SmsCode(int length, int expireIn) {
        this.length = length;
        this.expireIn = expireIn;
    }

    public SmsCode(int length, int expireIn, String url) {
        this.length = length;
        this.expireIn = expireIn;
        this.url = url;
    }
}
