package xyz.yudong520.manageadmin.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_user")
public class User  implements UserDetails ,SocialUserDetails,Serializable {
    @Id
    private  String userId;    //主键

    @Column(name="username",unique=true,nullable=false)
    private String username;  //用户名

    @Column(name="nick_name",nullable=false)
    private String nickName; //昵称

    @Column(name="password",unique=false,nullable=false)
    private  String password;  //密码

    @Column(name = "mobile",length = 20)
    private String mobile;  //电话

    @Column(name = "handImage")
    private String handImge;  //图像

    @Column(name = "age",length = 3)
    private Integer age;  //年龄

    @Column(name = "sex",length = 1)
    private Integer sex; //性别

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday",nullable=false)
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;    //出生日期

    @Column(name = "dateStatus",length = 1)
    private Integer dateStatus=1;   //数据状态

    @Temporal(TemporalType.DATE)
    @Column(name ="created_time" )
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;   //创建时间

    @Column(name = "created_emp")
    private String createdEmp;   //创建人

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_time")
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;  //更新时间

    @Column(name = "updated_emp")
    private String updatedEmp; //更新人

    @Column(name = "version")
    private String version;  //乐观锁字段

    @Column(name = "reserve1")
    private String reserve1;  //备用字段

    @Column(name = "reserve2")
    private String reserve2;  //备用字段

    @Column(name = "reserve3")
    private String reserve3;  //备用字段

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "tb_role_user",joinColumns = { @JoinColumn(referencedColumnName = "userId",name="uid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet=new HashSet<>();

    @Transient
    private Set<Permissions> authorities=new HashSet<>();

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHandImge() {
        return handImge;
    }

    public void setHandImge(String handImge) {
        this.handImge = handImge;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(Integer dateStatus) {
        this.dateStatus = dateStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(String createdEmp) {
        this.createdEmp = createdEmp;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedEmp() {
        return updatedEmp;
    }

    public void setUpdatedEmp(String updatedEmp) {
        this.updatedEmp = updatedEmp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public Set<Permissions> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Permissions> authorities) {
        this.authorities = authorities;
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
}
