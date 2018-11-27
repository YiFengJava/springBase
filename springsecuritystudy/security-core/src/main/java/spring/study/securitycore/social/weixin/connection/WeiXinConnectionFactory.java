package spring.study.securitycore.social.weixin.connection;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import spring.study.securitycore.social.qq.api.QQ;
import spring.study.securitycore.social.qq.connection.QQAdapter;
import spring.study.securitycore.social.qq.connection.QQServiceProvider;
import spring.study.securitycore.social.weixin.api.WeiXin;

public class WeiXinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {

    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
//     * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
//     * @param apiAdapter      the ApiAdapter for mapping the provider-specific service API model to the uniform {@link Connection} interface.
     */
    public WeiXinConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new WeiXinServiceProvider(appId,appSecret), new WeiXinAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的,所以在这里直接根据accessToken设置providerUserId即可,不用像QQ那样通过QQAdapter获取
     * @param accessGrant
     * @return
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeiXinAccessGrant){
            return ((WeiXinAccessGrant) accessGrant).getOpenid();
        }
        return null;
    }

    /**
     * 通过访问权限获取微信连接
     * @param accessGrant
     * @return
     */
    @Override
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeiXin>(getProviderId(),extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(),accessGrant.getRefreshToken(),accessGrant.getExpireTime(),
                getOAuth2ServiceProvider(),getApiAdapter(extractProviderUserId(accessGrant)));
    }


    @Override
    public Connection<WeiXin> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeiXin>(data,getOAuth2ServiceProvider(),getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        //多实例对象,不同的人openId不一样,每一个openId对应一个对象,qq这里每一次传回去都是同一个对象
        return new WeiXinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider(){
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }

}
