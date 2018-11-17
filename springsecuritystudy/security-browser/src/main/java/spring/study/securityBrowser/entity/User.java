package spring.study.securityBrowser.entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class User {
    private  String id;
    private String username;
    private  String password;

    // 封装了权限信息
//    Collection<? extends GrantedAuthority> getAuthorities(){
//        return  null ;
//    };
//    // 密码信息
//    String getPassword(){
//        return  null;
//    };
//    // 登录用户名
//    String getUsername(){
//        return  null;
//    };
//    // 帐户是否过期
//    boolean isAccountNonExpired(){
//        return  true;
//    };
//    // 帐户是否被冻结
//    boolean isAccountNonLocked(){
//        return  true;
//    };
//    // 帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
//    boolean isCredentialsNonExpired(){
//        return  true;
//    };
//    // 帐号是否可用
//    boolean isEnabled(){
//        return  true;
//    };//////////////

}
