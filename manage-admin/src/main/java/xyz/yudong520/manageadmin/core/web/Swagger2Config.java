package xyz.yudong520.manageadmin.core.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 在线API文档
 * @author lj
 *
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "ydsecurity" , name = "swagger-open" , havingValue="true")
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api接口文档页面")
				.genericModelSubstitutes(DeferredResult.class)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("xyz.yudong520.manageadmin.system.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("api文档")
				.description("controller的pai文档")
				.termsOfServiceUrl("/")
				.version("1.0")
				.build();
	}

//	@Bean
//	public Docket openRestApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("支付宝接口服务页面")
//				.genericModelSubstitutes(DeferredResult.class)
//				.apiInfo(aliInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.smart.system.controller.ali"))
//				.paths(PathSelectors.any())
//				.build();
//	}

	private ApiInfo aliInfo() {
		return new ApiInfoBuilder()
				.title("智慧社区手机端API文档")
				.description("支付宝Api接口文档")
				.termsOfServiceUrl("")
				.version("1.0")
				.build();
	}
}
