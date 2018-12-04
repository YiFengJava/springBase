package xyz.yudong520.manageadmin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/community")
public class CommunityController {
    private final String PAGE_PREFIX_COMMUNITY="system/community";
    @GetMapping
    public String communityManagerPage(){
       return PAGE_PREFIX_COMMUNITY+"community.html";
    }
}
