package xyz.yudong520.manageadmin.core.security.validate.code;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码的实体类
 */

public class ValidateCode implements Serializable {
    //校验码的值
    private  String code;

    //校验码的过期时间
    private LocalDateTime expireTime=LocalDateTime.now().plusSeconds(180);


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public void setExpireTime(long expire) {
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }

    public ValidateCode() {
    }

    public ValidateCode(String code) {
        this.code = code;
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code,long expire) {
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expire);
    }

    public boolean isExpireTime(){
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(this.expireTime)){
            return false;
        }
        return  true;
    }


}
