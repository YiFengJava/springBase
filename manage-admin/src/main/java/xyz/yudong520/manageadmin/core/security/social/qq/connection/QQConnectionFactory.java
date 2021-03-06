package xyz.yudong520.manageadmin.core.security.social.qq.connection;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import xyz.yudong520.manageadmin.core.security.social.qq.api.QQ;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId, String appid,String appSecret) {
        super(providerId, new QQServiceProvider(appid,appSecret), new QQAdapter());
    }
}
