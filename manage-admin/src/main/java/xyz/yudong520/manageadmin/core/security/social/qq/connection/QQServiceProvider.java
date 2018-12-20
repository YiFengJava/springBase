package xyz.yudong520.manageadmin.core.security.social.qq.connection;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import xyz.yudong520.manageadmin.core.security.social.qq.api.QQ;
import xyz.yudong520.manageadmin.core.security.social.qq.api.impl.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    private String appSecret;
    static  final  String URL_AUTHORIZE="https://graph.qq.com/oauth2.0/authorize";

    static  final  String URL_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new QQOAuth2Template( appId,  appSecret,  URL_AUTHORIZE,  URL_ACCESS_TOKEN));
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
