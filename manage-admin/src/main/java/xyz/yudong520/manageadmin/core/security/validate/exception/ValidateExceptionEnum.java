package xyz.yudong520.manageadmin.core.security.validate.exception;

public enum ValidateExceptionEnum {

    VALIDATE_NOT_EXIST(700,"校验码不存在")
    ,GET_VALIDATE_CODE_ERR(701,"获取校验码失败")
    ,VALIDATE_NOT_EMPT(702,"校验码不能空")
    ,VALIDATE_IS_EXPIRE_EMPT(703,"校验码已经过期")
    ,VALIDATE_CODE_NOT_MATCH(704,"校验码不匹配");

    private Integer code;
    private String message;


    ValidateExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
