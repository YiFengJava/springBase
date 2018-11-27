package spring.study.securitycore.social.qq.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import java.io.IOException;


/**
 * QQ 用户信息的实现
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    public Logger logger= LoggerFactory.getLogger(QQImpl.class);


    static  final  String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";

    static  final  String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    ObjectMapper objectMapper=new ObjectMapper();

    private String appid;

    private String openid;

    public QQImpl(String accessToken,String appid){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appid=appid;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result=getRestTemplate().getForObject(url,String.class);
        System.out.println("请求api得到openid的借口字符串为："+result);
        String between = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
//        callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        logger.info("==========openid 为："+between);
        this.openid=between;
    }

    @Override
    public QQUserInfo getQQUserInfo(){
        String url = String.format(URL_GET_USERINFO, appid, openid);
        String result=getRestTemplate().getForObject(url,String.class);
        System.out.println("请求api得到用户信息的借口返回的字符串为："+result);
        logger.info("==========userInfo 为："+result);
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qqUserInfo;
    }
}
