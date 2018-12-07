package xyz.yudong520.manageadmin.core.security.properties;


import lombok.Data;

@Data
public class QQProperties{

    private String providerId = "qq";

    private String appId;

    private String appSecret;
}
