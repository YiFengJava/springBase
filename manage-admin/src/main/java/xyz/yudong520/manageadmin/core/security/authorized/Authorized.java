package xyz.yudong520.manageadmin.core.security.authorized;

import org.springframework.http.HttpMethod;
import java.lang.annotation.*;
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorized {

    String value() default "";

    ReturnType type() default  ReturnType.JSON;

    HttpMethod method() default HttpMethod.GET;
//




}
