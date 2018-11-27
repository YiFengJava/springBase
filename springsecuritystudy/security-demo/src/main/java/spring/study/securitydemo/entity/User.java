package spring.study.securitydemo.entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="tb_user")
public class User  implements UserDetails ,SocialUserDetails {
    @Id
    private  String userId ="10";

    @Column
    private String username="yudong";

    @Column
    private  String password="123456";

    @Transient
    private Set<Permissions> authorities=new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "tb_role_user",joinColumns = { @JoinColumn(referencedColumnName = "userId",name="uid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet=new HashSet<>();

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


    public void setAuthorities(Set<Permissions> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
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
