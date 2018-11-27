package spring.study.securitycore.properties;

import lombok.Data;

@Data
public class WeiXinProperties {
    private String providerId = "weixin";

    private String appId;

    private String appSecret;
}
