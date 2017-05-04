package ren.com.cn.common.enums;

/**
 * Created by user on 2016/8/19.
 */
public enum ConfigEnum {
    年费VIP返回B券金额("annual_vip_bcoin_coupon_money"),
    年费VIP返B券活动ID ("annual_vip_bcoin_coupon_activity_id"),
    年费VIP促销提示("annual_vip_promotion_tips"),
    年费VIPB券发放每月第几天("annual_vip_bcoin_day"),
    支付方式IOS("pay_way_ios"),
    支付方式ANDROID("pay_way_android"),
    账号冻结分钟("account_frozen_minute"),
    业务方每十分钟调用限制次数("request_limit_inernal"),
    用户每十分钟调用次数("request_limit_user"),
    未登录每十分钟调用次数("request_limit_unkonw"),
    积分获取规则("point_get_rule"),
    会员兑换vip时长规则("member_exchange_vip_rule"),
    积分兑换TITLE("bubble_point_change_title"),
    积分兑换SUB_TITLE("bubble_point_change_sub_title"),
    积分兑换大会员落地页URL("bubble_point_change_link_url"),
    积分兑换_文字气泡文案("bubble_point_change_words_content"),
    VIP过期TITLE("bubble_vip_expire_title"),
    VIP过期SUB_TITLE("bubble_vip_expire_sub_title"),
    VIP过期落地页URL("bubble_vip_expire_link_url"),
    VIP过期_文字气泡文案("bubble_vip_expire_words_content");

    public final String code;

    ConfigEnum(String code){
        this.code = code;
    }
}
