package spring.study.securitycore.social.weixin.connection;

import lombok.Data;
import org.springframework.social.oauth2.AccessGrant;
@Data
public class WeiXinAccessGrant extends AccessGrant {

    private String openid;

    public WeiXinAccessGrant(String accessToken) {
        super(accessToken);
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }
}
