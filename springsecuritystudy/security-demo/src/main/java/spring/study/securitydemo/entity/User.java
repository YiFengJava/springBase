package spring.study.securitydemo.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.util.*;

public class User  implements UserDetails ,SocialUserDetails {
    private  String userId ="10";
    private String username="yudong";
    private  String password="123456";
    private Set<GrantedAuthority> authorities=new HashSet<>();


    @Override
    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        this.authorities.add(new GrantedAuthority(){
            @Override
            public String getAuthority() {
                return "admin";
            }
        });
        return this.authorities;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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
