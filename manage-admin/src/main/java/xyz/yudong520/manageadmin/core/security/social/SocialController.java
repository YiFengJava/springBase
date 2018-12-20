package xyz.yudong520.manageadmin.core.security.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
@RestController
public class SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;


    @GetMapping(value = "/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo info = new SocialUserInfo();
        info.setProviderId(connection.getKey().getProviderId());
        info.setProviderUserId(connection.getKey().getProviderUserId());
        info.setNickname(connection.getDisplayName());
        info.setHaeding(connection.getImageUrl());
        return info;
    }

}

