package xyz.yudong520.manageadmin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.service.AuthorityService;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限控制管理控制
 */
@Controller
@RequestMapping(value = "/authority")
public class AuthorityController {

    private final String PAGE_PREFIX_AUTHORITY="system/authority";

    @Autowired
    private AuthorityService authorityService;

    //跳转权限管理页面
    @GetMapping()
    public String  authorityManagerPage(){
        return PAGE_PREFIX_AUTHORITY+"/authority.html";
    }

    //查询该用户的所有的菜单权限和权限的信息列表
    @GetMapping(value = "/queryAll")
    @ResponseBody
    public Object queryAll(){
        //得到用的所有的角色id集合
        //  模拟
        Set<String> rids=new HashSet<>();
        rids.add("1");
        Set<Permissions> permissionsSet = authorityService.getPermisseionsTableByRoles(rids);
        return  permissionsSet;
    }
}
