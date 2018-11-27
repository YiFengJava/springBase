package spring.study.securitycore.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import spring.study.securitycore.social.qq.api.QQUserInfo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    static  final  String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";

    static  final  String URL_GET_USERINFO="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    ObjectMapper objectMapper=new ObjectMapper();


    public Logger logger= LoggerFactory.getLogger(WeiXinImpl.class);


    public WeiXinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }


    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return  messageConverters;
    }

    @Override
    public WeiXinUserInfo getWeiXinUserInfo(String openid) {
        String url = String.format(URL_GET_USERINFO,openid);
        String result=getRestTemplate().getForObject(url,String.class);
        System.out.println("请求api得到用户信息的借口返回的字符串为："+result);
        logger.info("==========userInfo 为："+result);
        WeiXinUserInfo user=null;
        try {
            user =objectMapper.readValue(result, WeiXinUserInfo.class);
            user.setOpenid(openid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
