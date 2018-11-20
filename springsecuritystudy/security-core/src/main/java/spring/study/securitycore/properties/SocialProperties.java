package spring.study.securitycore.properties;

import lombok.Data;

@Data
public class SocialProperties {
    private QQProperties qqProperties=new QQProperties();
    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessesUrl = "/auth";
}
