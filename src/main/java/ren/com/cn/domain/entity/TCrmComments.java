package ren.com.cn.domain.entity;

import java.util.Date;
import lombok.Data;

@Data
public class TCrmComments {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 评论id，由平台数据部门给到
     */
    private Long commentId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 菜品编码
     */
    private Long foodCode;

    /**
     * 菜品名称
     */
    private String foodName;

    /**
     * 评价星级
     */
    private Byte starLevel;

    /**
     * 评论分类
     */
    private String commentCategory;

    /**
     * 评价类型，好中差评
     */
    private Byte commentType;

    /**
     * 评论对象，1为菜品，2为商家
     */
    private Byte commentTarget;

    /**
     * 下单城市名称
     */
    private String cityName;

    /**
     * 下单城市ID
     */
    private String cityId;

    /**
     * 业务类型，自营、代理
     */
    private Byte bizType;

    /**
     * 餐厅ID
     */
    private Long restaurantId;

    /**
     * 餐厅名称
     */
    private String restaurantName;

    /**
     * 门店类型
     */
    private Byte storeType;

    /**
     * 门店id
     */
    private Long storeId;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 与平台关联订单的id，即eleme_restaurant_id
     */
    private Long linkOrderId;

    /**
     * 区域负责人
     */
    private String areaPrincipal;

    /**
     * 评论详情
     */
    private String commentDetail;

    /**
     * 回复详情
     */
    private String replyDetail;

    /**
     * 回复状态，0为未回复，1为已回复
     */
    private Byte replyStatus;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 评论创建时间
     */
    private Date ctime;

    /**
     * 评论修改时间
     */
    private Date mtime;

    /**
     * 记录同步时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 删除状态字段，0有效，1删除
     */
    private Byte isDelete;

    /**
     * 状态,目前仅作保留字段
     */
    private Byte status;

    /**
     * 记录更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 仅供DRC数据校验使用
     */
    private Date drcCheckTime;

    /**
     * 评论状态，0为未评论，1为已评论
     */
    private Byte isCommented;

    /**
     * each菜品名称
     */
    private String eachFoodName;

    /**
     * each菜品id
     */
    private Long eachFoodId;

    /**
     * each菜品编码
     */
    private String eachFoodCode;
}