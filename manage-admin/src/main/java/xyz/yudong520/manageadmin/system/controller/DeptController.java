package xyz.yudong520.manageadmin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    private final String PAGE_PREFIX_DEPT="system/dept";

    @GetMapping()
    public String deptManagerPage(){
        return  PAGE_PREFIX_DEPT+"/dept.html";
    }
}
