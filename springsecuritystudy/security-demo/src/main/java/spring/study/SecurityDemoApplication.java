package spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//        (exclude={DataSourceAutoConfiguration.class }) //关闭数据库源
//        ,SecurityAutoConfiguration.class
//}) //关闭security
@RestController
@EnableSwagger2
public class SecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }



//    restful api
//
//    查询   /user/query？name=avb            /user?name=tom          get
//    详情   /user/getInfo?id=1               /user/1                 get
//    创建   /user/create?name=tom            /user                   post
//    修改   /user/update?id=1&name=payu      /user/1                 put
//    删除   /user/delete?id=1                /user/1                 delete
//            1，用url描述资源
//            2，使用http方法描述行为，使用http状态码来表示不同的结果
//            3，使用json交互数据
//            4，restful只是一个风格，不是强制的标准


    @GetMapping("/helle")
    public String hello() {
        return "hello";
    }
}
