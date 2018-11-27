package spring.study.securitycore.social.weixin.connection;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import spring.study.securitycore.social.weixin.api.WeiXin;
import spring.study.securitycore.social.weixin.api.WeiXinUserInfo;

public class WeiXinAdapter implements ApiAdapter<WeiXin> {

    private String openid;

    public WeiXinAdapter(String openid) {
        this.openid = openid;
    }

    public WeiXinAdapter() {
    }

    @Override
    public boolean test(WeiXin api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo weiXinUserInfo = api.getWeiXinUserInfo(openid);  //得到用户信息
        values.setDisplayName(weiXinUserInfo.getNickname()); //设置连接设置名称用的是qq昵称
        values.setImageUrl(weiXinUserInfo.getHeadimgurl()); //设置图像
        values.setProfileUrl(null);   //设置个人主页 因为qq没有个人主页，直接设置为null
        values.setProviderUserId(weiXinUserInfo.getOpenid()); //设置服务商用户id 及用户的openid
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        // 暂时不知道有什么用处
        return UserProfile.EMPTY;
    }

    @Override
    public void updateStatus(WeiXin api, String message) {

    }
}
