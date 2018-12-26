package xyz.yudong520.manageadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManageAdminApplication {



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



	public static void main(String[] args) {
		SpringApplication.run(ManageAdminApplication.class, args);
	}
}
