package xyz.yudong520.manageadmin.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.yudong520.manageadmin.common.dto.MenuTree;
import xyz.yudong520.manageadmin.core.security.support.SimpleResponse;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    //跳转登陆成功页面
    @GetMapping(value = "/index")
    public String loginSuccessPage(Model model){
        //获取该管理用户的授权信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //在授权信息中获取用户个人基本信息
        User user = (User) authentication.getPrincipal();
        //获取管理员的所有的权限列表
        Set<Permissions> authorities = user.getAuthorities();
        //格式属性菜单树结构
        Set<MenuTree> menuTrees = formatMenuTree(authorities, "#", "1");
        model.addAttribute("menu",menuTrees);
        return  "home.html";
    }

    /**
     * 格式化菜单树形结构
     * @param authorities  所有的菜单权限集合
     * @param pcode  父级编码
     * @param type   类型 1，为菜单，2，为菜单下 的按钮请求权限
     * @return
     */
    private Set<MenuTree> formatMenuTree(Set<Permissions> authorities,String pcode,String type){
        Set<MenuTree> menuTrees=new HashSet<>();
        authorities.forEach((Permissions per)->{
            if(pcode.equals( per.getPcode())){
                if(type!=null && per.getType().equals(type)){
                    menuTrees.add(assemblyDate(per,pcode,type,authorities));
                }else{
                    menuTrees.add(assemblyDate(per,pcode,type,authorities));
                }
            }
        });
        return  menuTrees;
    }

    /**
     * 组装菜单结构
     * @param per
     * @param pcode
     * @param type
     * @param authorities
     * @return
     */
    private MenuTree assemblyDate(Permissions per,String pcode,String type,Set<Permissions> authorities){
        MenuTree menuTree = new MenuTree();
        menuTree.setCode(per.getCode());
        menuTree.setIonc(per.getIonc());
        menuTree.setName(per.getName());
        menuTree.setValue(per.getValue());
        menuTree.setPcode(pcode);
        menuTree.setChildMenu(formatMenuTree(authorities,per.getCode(),type));
        return  menuTree;
    }



    //调整默认加载的首页
    @GetMapping(value = "/index_home")
    public String indexHomePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authentication",authentication);
        return  "index_home.html";
    }

    //失效跳转页面
    @GetMapping(value = "/session/invalid")
    public String sessionInvalid() {
        log.info("session失效，跳转登陆页面");
        return "login.html";
    }

    //失效跳转页面
    @GetMapping(value = "/sessionid")
    @ResponseBody
    public SimpleResponse sessionId(HttpServletRequest request) {
        log.info("SessionId:"+request.getSession().getId());
        return new SimpleResponse(200,"sessionid",request.getSession().getId());
    }

}
