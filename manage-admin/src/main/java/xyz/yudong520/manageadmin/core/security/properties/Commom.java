package xyz.yudong520.manageadmin.core.security.properties;

public interface Commom {

    /**
     * 手机验证码的手机号的参数名称
     */
    public static final  String LOGIN_MOBILE_PARAMETER_NAME="mobile";

    /**
     * 验证码登陆，短信登陆时短信校验码的参数名称
     */
    public static final String LOGIN_VALIDATE_CODE_SMS="smsCode";

    /**
     * 图片校验码时，登陆时图片校验码的参数名称
     */
    public static final String LOGIN_VALIDATE_CODE_IMAGE="imageCode";

    /**
     * 短信登陆的校验的url
     */
    public static final String SMS_LOGIN_VALIDATE_URL="/login/smsLogin";

    /**
     * 图片校验登陆的校验的url
     */
    public static final  String IMAGE_LOGIN_VALIDATE_URL="/login/auth";
}
