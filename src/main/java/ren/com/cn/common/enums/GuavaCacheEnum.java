package ren.com.cn.common.enums;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 22:35
 * Email: renhongqiang1397@gmail.com
 */
public enum GuavaCacheEnum {

    commonBubbleList(1,100);

    public final int expireAfterWrite;

    public final int maximumSize;

    GuavaCacheEnum(int expireAfterWrite, int maximumSize) {
        this.expireAfterWrite = expireAfterWrite ;
        this.maximumSize = maximumSize;
    }
}
