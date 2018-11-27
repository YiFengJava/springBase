package spring.study.securitydemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import spring.study.securitydemo.dto.VUser;
import spring.study.securitydemo.dto.UserQueryCondition;
import spring.study.securitydemo.entity.TUser;
import spring.study.securitydemo.entity.User;
import spring.study.securitydemo.exception.UserNotExistException;
import spring.study.securitydemo.service.TUserService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController  //表明提共restFulapi
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;


//    @RequestMapping(value = "/user",method = RequestMethod.GET)
//    public List<VUser> query(@RequestParam(value = "name",required = true) String name){ //请求中请求参数name 别名，是否必填
//        List<VUser> list=new ArrayList<>();
//        list.add(new VUser());
//        list.add(new VUser());
//        list.add(new VUser());
//        return  list;
//    }



//    @ApiOperation("注册，绑定")
//    @PostMapping(value = "/regists")
//    public void userRegists(User user, HttpServletRequest request){
//        //不管是注册还是绑定都会有一个用户唯一标识
//        String userId = user.getUserId();
//        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
//    }

    @GetMapping(value = "/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @DeleteMapping(value = "/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }

    @PostMapping()
    public VUser create(@Valid @RequestBody VUser VUser){
        System.out.println(VUser.getPassword());
        System.out.println(VUser.getUsername());
        System.out.println(VUser.getBrithday());
        VUser.setId("1");
        return VUser;
    }


    @GetMapping(value = "/toquery/{id}")
    @ResponseBody
//    @Transactional(rollbackFor = Exception.class,propagation= Propagation.SUPPORTS)
    public TUser getuser(@PathVariable Long id) throws Exception {
        TUser tUser = tUserService.insertTUser(id);
        return  tUser;
    }


//    @PostMapping()
//    public VUser create(@Valid @RequestBody  VUser user, BindingResult erros){
//        if(erros.hasErrors()){
//            erros.getAllErrors().stream().forEach(erro->
//                    System.out.println(erro.getDefaultMessage()));
//        }
//        System.out.println(user.getPassword());
//        System.out.println(user.getUsername());
//        System.out.println(user.getBrithday());
//        user.setId("1");
//        return  user;
//    }

    @PutMapping(value = "/{id:\\d+}")
    public VUser update(@Valid @RequestBody VUser VUser, BindingResult erros){
        if(erros.hasErrors()){
            erros.getAllErrors().stream().forEach(erro->{
                        FieldError erro1 = (FieldError) erro;
                        String s = erro1.getField() + ":" + erro1.getDefaultMessage();
                        System.out.println(s);
                        System.out.println(erro.getDefaultMessage());
                    }
                   );
        }
        System.out.println(VUser.getPassword());
        System.out.println(VUser.getUsername());
        System.out.println(VUser.getBrithday());
        VUser.setId("1");
        return VUser;
    }

    @JsonView({VUser.UserSimpleView.class})
    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping()  //(value = "/user")
    @ApiOperation(value = "用户查询服务")
    public List<VUser> query(UserQueryCondition  condition, @PageableDefault(page = 2,size = 17,sort = "username,asc") Pageable pageable){
        //反射工具
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<VUser> list=new ArrayList<>();
        list.add(new VUser());
        list.add(new VUser());
        list.add(new VUser());
        return  list;
    }

    @JsonView(VUser.UserDetaiView.class)
//    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    @GetMapping("/{id:\\d+}")
    public VUser getInfo(@ApiParam(value = "用户主键") @PathVariable String id){
        VUser VUser = new VUser();
        VUser.setUsername("tom");
        return VUser;
    }

    @GetMapping(value = "/test/{id}")
    public void testException(@PathVariable String id){
        throw new UserNotExistException(id);
    }

    @GetMapping(value = "/tests/{id}")
    public VUser testException1(@PathVariable String id){
      System.out.println("進入测试");
      VUser VUser =  new VUser();
      VUser.setId("1");
      return VUser;
    }

//    使用@JsonView使用步骤
//        使用接口来申明多个视图
//        在值的对象的对象方法上指定视图
//        在Controller方法上指定视图


}
