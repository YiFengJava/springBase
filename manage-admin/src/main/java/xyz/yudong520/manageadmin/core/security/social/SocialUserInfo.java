package xyz.yudong520.manageadmin.core.security.social;

import lombok.Data;

@Data
public class SocialUserInfo {
    private String providerId;
    private String providerUserId;
    private String nickname;
    private String haeding;

}
