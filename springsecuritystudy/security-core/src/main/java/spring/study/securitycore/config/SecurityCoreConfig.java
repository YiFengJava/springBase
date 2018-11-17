package spring.study.securitycore.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import spring.study.securitycore.properties.SecurityProperties;

@Configuration  //申明配置类
@EnableConfigurationProperties(SecurityProperties.class) //使自定义的配置类生效
public class SecurityCoreConfig {
}
