package xyz.yudong520.manageadmin.core.security.social.qq.user;

import lombok.Data;

@Data
public class QQUserInfo {

    private String openId;

    private String ret;

    private String msg;

    private String is_lost;

    private String nickname;

    private String figureurl;

    private String figureurl_1;

    private String figureurl_2;

    private String figureurl_qq_1;

    private String figureurl_qq_2;

    private String gender;

    private String is_yellow_vip;

    private String vip;

    private String yellow_vip_level;

    private String level;
    private String province;
    private String is_yellow_year_vip;
    private String year;
    private String city;
    private String constellation;
}
