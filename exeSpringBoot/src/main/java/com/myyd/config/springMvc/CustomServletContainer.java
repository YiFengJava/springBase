package com.myyd.config.springMvc;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 在 Spring Boot 1.x 中 ，我们通过 EmbeddedServletContainerCustomizer 接口调优 Tomcat 自定义配置。
 * 在Spring Boot 2.0 中，通过 WebServerFactoryCustomizer 接口定制。
 */

@Component
public class CustomServletContainer  implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8082);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));

    }
}
