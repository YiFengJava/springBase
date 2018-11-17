package spring.study.securitycore.validate.sms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;


    public ValidateCode( String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean isExpried() {
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(this.expireTime)){
            return  false;
        }
        return true;
    }
}
