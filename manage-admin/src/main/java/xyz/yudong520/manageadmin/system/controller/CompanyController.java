package xyz.yudong520.manageadmin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {

    private final String PAGE_PREFIX_COMPANY="system/company";

    @GetMapping()
    public String companyManagerPage(){
        return  PAGE_PREFIX_COMPANY+"/company.html";
    }



}
