package xyz.yudong520.manageadmin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private final String PAGE_PREFIX_ROLE="system/role";

    @GetMapping()
    public String roleManagerPage(){
        return  PAGE_PREFIX_ROLE+"/role.html";
    }
}
