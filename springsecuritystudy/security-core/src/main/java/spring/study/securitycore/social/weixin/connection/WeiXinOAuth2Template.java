package spring.study.securitycore.social.weixin.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class WeiXinOAuth2Template extends OAuth2Template {

    private  String clientId;

    private  String clientSecret;

    private  String accessTokenUrl;

    private static  final String REFRESH_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public Logger logger= LoggerFactory.getLogger(WeiXinOAuth2Template.class);

    public WeiXinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId=clientId;
        this.clientSecret=clientSecret;
        this.accessTokenUrl=accessTokenUrl;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        //        AccessGrant accessGrant=new AccessGrant();
        //        getAccessToken
        StringBuilder stringBuilder = new StringBuilder(accessTokenUrl);
        stringBuilder.append("?appid="+clientId);
        stringBuilder.append("&sectret="+clientSecret);
        stringBuilder.append("&code="+authorizationCode);
        stringBuilder.append("&grant_type=authorization_code");
        stringBuilder.append("&redirect_url="+redirectUri);
        return getAccessToken(stringBuilder);
    }


    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuilder stringBuilder = new StringBuilder(REFRESH_TOKEN_URL);
        stringBuilder.append("?appid="+clientId);
        stringBuilder.append("&grant_type=refresh_token");
        stringBuilder.append("&refreshToken="+refreshToken);
        return getAccessToken(stringBuilder);
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url=url+"&appid="+clientId+"&scope=snsapi_login";
        return  url;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }

    /**
     * 获取访问令牌,返回访问权限,微信定义的正确的返回
     * @param accessTokenRequestUrl
     * @return
     */
    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {
        logger.info("获取access_token, 请求URL: "+accessTokenRequestUrl.toString());

        String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);

        logger.info("获取access_token, 响应内容: "+response);

        Map<String, Object> result = null;
        try {
            //将返回的json串转换成map
            result = new ObjectMapper().readValue(response, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //返回错误码时直接返回空
        if(StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))){
            String errcode = MapUtils.getString(result, "errcode");
            String errmsg = MapUtils.getString(result, "errmsg");
            throw new RuntimeException("获取access token失败, errcode:"+errcode+", errmsg:"+errmsg);
        }
        WeiXinAccessGrant accessToken = new WeiXinAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in"));

        accessToken.setOpenid(MapUtils.getString(result, "openid"));
        return accessToken;
    }

}
