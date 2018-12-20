package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageCode implements Serializable {
//    默认的宽度
    private int width =100;
//    默认的高度
    private int height =32;
//    默认的长度
    private int length =6;
//    默认的过期时长 秒
    private int expireIn=180;

    private String url;

    public ImageCode(int width, int height, int length, int expireIn, String url) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.expireIn = expireIn;
        this.url = url;
    }

    public ImageCode() {
    }

    public ImageCode(int width, int height, int length, int expireIn) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.expireIn = expireIn;
    }
}
