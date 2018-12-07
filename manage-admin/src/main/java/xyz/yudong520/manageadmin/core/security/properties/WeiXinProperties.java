package xyz.yudong520.manageadmin.core.security.properties;

import lombok.Data;

@Data
public class WeiXinProperties {
    private String providerId = "weixin";

    private String appId;

    private String appSecret;
}
