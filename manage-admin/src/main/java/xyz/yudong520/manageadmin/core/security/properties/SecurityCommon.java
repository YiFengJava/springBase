package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.core.security.authorized.ReturnType;

@Data
@Component
@ConfigurationProperties(prefix = SecurityCommon.PREFIX)
public class SecurityCommon {
    public static final String PREFIX="ydsecurity";

    private ValidateCode validateCode;

    private ReturnType  returnType = ReturnType.JSON;

    private Social social;

}
