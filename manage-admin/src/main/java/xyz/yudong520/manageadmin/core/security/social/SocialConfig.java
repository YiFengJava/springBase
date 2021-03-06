package xyz.yudong520.manageadmin.core.security.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import  org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;
import xyz.yudong520.manageadmin.core.security.properties.SecurityCommon;
import xyz.yudong520.manageadmin.core.security.social.qq.MySocialConfig;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig
        extends SocialConfigurerAdapter
//            implements SocialConfigurer
//    SocialConfigurerAdapter
//        implements   SocialConfigurer
{


    @Autowired
    private SecurityCommon securityCommon;

    @Autowired
    private DataSource dataSource;


    @Autowired(required = false)
    private  ConnectionSignUp  connectionSignUp;

    @Override
    public UserIdSource getUserIdSource() {
//        return new UserIdSource() {
            return new AuthenticationNameUserIdSource();
//            @Override
//            public String getUserId() {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if (authentication == null) {
//                    throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
//                } else {
//                    SocialUserDetails socialUserDetails=(SocialUserDetails)authentication;
//                    return socialUserDetails.getUserId();
//                }
//            }
//        };
    }

//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
//        connectionFactoryConfigurer.addConnectionFactory(new QQConnectionFactory(securityProperties.getSocialProperties().getQqProperties().getProviderId(),
//                securityProperties.getSocialProperties().getQqProperties().getAppId(),
//                securityProperties.getSocialProperties().getQqProperties().getAppSecret()));
//    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository =
                new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());

        if(connectionSignUp!=null){
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    /**
     * 处理注册流程的工具类
     * @param
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator) {
        return new ProviderSignInUtils(factoryLocator, getUsersConnectionRepository(factoryLocator));
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
//        SpringSocialConfigurer springSocialConfigurer = new SpringSocialConfigurer();
//        return springSocialConfigurer;
//        获取可配置的url
        String filterProcessesUrl = securityCommon.getSocial().getFilterProcessesUrl();
        String signUpUrl = securityCommon.getSocial().getSignUpUrl();
        MySocialConfig configurer = new MySocialConfig(filterProcessesUrl,signUpUrl);
//        //自动去配置实现url
        return configurer;

    }

}
