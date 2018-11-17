package spring.study.securitycore.properties;

import lombok.Data;

/**
 * 校验图片的配置
 */
@Data
public class ImageCodeProperties extends  SmsCodeProperties{
    //校验图片的宽
    private int width = 80;
    //图片的高度
    private int height = 32;


}
