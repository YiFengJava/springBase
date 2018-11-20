package spring.study.securitycore.social.qq.api;

import lombok.Data;

/**
 * QQ用户信息
 */
@Data
public class QQUserInfo {

    private String openId;

    private String ret;

    private String   msg;

    private String     nickname;

    private String    figureurl;

    private String    figureurl_1;

    private String     figureurl_2;

    private String     figureurl_qq_1;

    private String     figureurl_qq_2;

    private String      gender;

    private String     is_yellow_vip;

    private String    vip;

    private String    yellow_vip_level;

    private String    level;

    private String     is_yellow_year_vip;
}
