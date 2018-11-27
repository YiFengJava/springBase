package spring.study.securitycore.social.weixin.connection;
import lombok.Data;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import spring.study.securitycore.social.qq.api.QQImpl;
import spring.study.securitycore.social.qq.connection.QQOAuth2Template;
import spring.study.securitycore.social.weixin.api.WeiXin;
import spring.study.securitycore.social.weixin.api.WeiXinImpl;

@Data
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin>{

    private String appId;
    private String appSecret;
    static  final  String URL_AUTHORIZE="https://open.weixin.qq.com/connect/qrconnect";

    static  final  String URL_ACCESS_TOKEN="https://api.weixin.qq.com/sns/oauth2/access_token";
    public WeiXinServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template( appId,  appSecret,  URL_AUTHORIZE,  URL_ACCESS_TOKEN));
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}
