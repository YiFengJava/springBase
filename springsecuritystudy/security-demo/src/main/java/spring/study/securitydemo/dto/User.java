package spring.study.securitydemo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import spring.study.securitydemo.validator.MyConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    public interface UserSimpleView{}; //简单视图

    public interface  UserDetaiView extends  UserSimpleView{}; //申明一个详细视图，继承简单视图

    private String id;
    @MyConstraint(message = "这是一个测试")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去时间")
    private Date   brithday;

    @JsonView(UserSimpleView.class)
    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetaiView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
