package xyz.yudong520.manageadmin.core.security.validate.code.image;

import xyz.yudong520.manageadmin.core.security.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片校验码的实体类
 */
public class ImageValidateCode extends ValidateCode {
    //图片校验码的图片
    private BufferedImage image;

    public ImageValidateCode(BufferedImage image) {
        this.image = image;
    }

    public ImageValidateCode(String code, BufferedImage image) {
        super(code);
        this.image = image;
    }

    public ImageValidateCode(String code, LocalDateTime expireTime, BufferedImage image) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageValidateCode(String code, long expire, BufferedImage image) {
        super(code, expire);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
