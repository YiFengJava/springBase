package xyz.yudong520.manageadmin.core.security.propertiesbak;

import lombok.Data;

@Data
public class WeiXinProperties {
    private String providerId = "weixin";

    private String appId;

    private String appSecret;
}
