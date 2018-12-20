package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

@Data
public class Social {

    private QQ qq;

    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessesUrl = "/auth";

    private String signUpUrl="/login/regist";

}
