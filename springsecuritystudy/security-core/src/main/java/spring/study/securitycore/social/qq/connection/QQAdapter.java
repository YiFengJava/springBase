package spring.study.securitycore.social.qq.connection;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import spring.study.securitycore.social.qq.api.QQ;
import spring.study.securitycore.social.qq.api.QQUserInfo;

/**
 * 创建ConnectionFactory 所需要的QQ适配器
 */
public class QQAdapter  implements ApiAdapter<QQ>{
    //测试是否连接的上
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
            QQUserInfo qqUserInfo = qq.getQQUserInfo();  //得到用户信息
            connectionValues.setDisplayName(qqUserInfo.getNickname()); //设置连接设置名称用的是qq昵称
            connectionValues.setImageUrl(qqUserInfo.getFigureurl_qq_1()); //设置图像
            connectionValues.setProfileUrl(null);   //设置个人主页 因为qq没有个人主页，直接设置为null
            connectionValues.setProviderUserId(qqUserInfo.getOpenId()); //设置服务商用户id 及用户的openid
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        // 暂时不知道有什么用处
        return UserProfile.EMPTY;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
