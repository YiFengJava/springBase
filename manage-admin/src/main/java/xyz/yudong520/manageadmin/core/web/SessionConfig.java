package xyz.yudong520.manageadmin.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;


@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=180)
public class SessionConfig {
    /**
     * `@EnableRedisHttpSession` 这个注解创建了一个名为 springSessionRepositoryFilter
     * 的 bean，负责替换 httpSession,同由 redis 提供缓存支持。
     * 为了做到全部替换，我们要确保Servlet容器(Tomcat)对于某个请求都使用这个Filter,这个由SpringBoot负责。
     *
     * `maxInactiveIntervalInSeconds`:设置Session失效时间
     * 使用Redis Session之后，原Boot的server.session.timeout属性不再生效
     */

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }

//    @Bean
//    public CookieHttpSessionStrategy  cookieHttpSessionStrategy(){
//        CookieHttpSessionStrategy strategy=new CookieHttpSessionStrategy();
//        DefaultCookieSerializer cookieSerializer=new DefaultCookieSerializer();
//        cookieSerializer.setCookieName("MYSESSIONID");//cookies名称
//        cookieSerializer.setCookieMaxAge(1800);//过期时间(秒)
//        strategy.setCookieSerializer(cookieSerializer);
//        return strategy;
//    }

}
