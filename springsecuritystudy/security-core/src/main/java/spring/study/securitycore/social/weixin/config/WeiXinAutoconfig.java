package spring.study.securitycore.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import spring.study.securitycore.properties.QQProperties;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.properties.WeiXinProperties;
import spring.study.securitycore.social.qq.connection.QQConnectionFactory;
import spring.study.securitycore.social.weixin.connection.WeiXinConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "mysecurity.socialProperties.weiXinProperties",name = "app-id")
public class WeiXinAutoconfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    public ConnectionFactory<?> createConnectionFactory() {
        WeiXinProperties weiXinProperties = securityProperties.getSocialProperties().getWeiXinProperties();
        return new WeiXinConnectionFactory(weiXinProperties.getProviderId(), weiXinProperties.getAppId(), weiXinProperties.getAppSecret());
    }

    // 后补：做到处理注册逻辑的时候发现的一个bug：登录完成后，数据库没有数据，但是再次登录却不用注册了
    // 就怀疑是否是在内存中存储了。结果果然发现这里父类的内存ConnectionRepository覆盖了SocialConfig中配置的jdbcConnectionRepository
    // 这里需要返回null，否则会返回内存的 ConnectionRepository
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }

//    @Autowired
//    private SecurityProperties securityProperties;
//
//    public ConnectionFactory<?> createConnectionFactory() {
//        return new QQConnectionFactory(securityProperties.getSocialProperties().getQqProperties().getProviderId(),
//                securityProperties.getSocialProperties().getQqProperties().getAppId(),
//                securityProperties.getSocialProperties().getQqProperties().getAppSecret());
//    }
}
