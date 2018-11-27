package spring.study.securitycore.properties;

import lombok.Data;

@Data
public class SocialProperties {
    private QQProperties qqProperties=new QQProperties();

    private WeiXinProperties weiXinProperties=new WeiXinProperties();
    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessesUrl = "/auth";
}
