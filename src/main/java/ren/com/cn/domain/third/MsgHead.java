package ren.com.cn.domain.third;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 当普通微信用户向公众账号发消息时，
 * 微信服务器回调POST消息的XML数据包
 *
 * @author : hongqiangren.
 * @since: 2018/5/17 20:11
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgHead implements Serializable {

    /**
     * 开发者微信号
     */
    @XmlElement(name = "ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间
     */
    @XmlElement(name = "CreateTime")
    private Long createTime;

    /**
     * 消息类型|text
     */
    @XmlElement(name = "MsgType")
    private String msgType;

    /**
     * 文本消息内容
     */
    @XmlElement(name = "Content")
    private String content;

    /**
     * 消息id，64位整型
     */
    @XmlElement(name = "MsgId")
    private String msgId;

    public enum MsgType {
        TEXT("text"),
        EVENT("text"),
        IMAGE("image");
        private String value;
        MsgType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

}
