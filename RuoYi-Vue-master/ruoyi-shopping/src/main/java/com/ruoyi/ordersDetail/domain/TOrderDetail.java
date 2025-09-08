package com.ruoyi.ordersDetail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单详细信息对象 t_order_detail
 * 
 * @author lhl
 * @date 2025-05-11
 */
public class TOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;



    /** 订单详细ID */
    private Long orderDetailId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String orderProductName;

    /** 商品Id */
    @Excel(name = "商品Id")
    private Long orderProductId;

    /** 订单商品的数量 */
    @Excel(name = "订单商品的数量")
    private Long orderProductCount;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String orderProductImage;

    /** 订单Id */
    @Excel(name = "订单Id")
    private Long orderId;

    /** 订单商品的数量 */
    @Excel(name = "订单用户id")
    private Long orderUserId;

    /** 商品所属用户 */
    @Excel(name = "商品所属用户")
    private String orderUserName;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String orderProductDesc;

    @Excel(name = "该商品单个商品价格")
    private Long orderProductPrice;

    @Excel(name = "订单状态")
    private Long orderStatus;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public void setOrderProductName(String orderProductName) {
        this.orderProductName = orderProductName;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Long getOrderProductCount() {
        return orderProductCount;
    }

    public void setOrderProductCount(Long orderProductCount) {
        this.orderProductCount = orderProductCount;
    }

    public String getOrderProductImage() {
        return orderProductImage;
    }

    public void setOrderProductImage(String orderProductImage) {
        this.orderProductImage = orderProductImage;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Long orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderProductDesc() {
        return orderProductDesc;
    }

    public void setOrderProductDesc(String orderProductDesc) {
        this.orderProductDesc = orderProductDesc;
    }

    public Long getOrderProductPrice() {
        return orderProductPrice;
    }

    public void setOrderProductPrice(Long orderProductPrice) {
        this.orderProductPrice = orderProductPrice;
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "TOrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderProductName='" + orderProductName + '\'' +
                ", orderProductId=" + orderProductId +
                ", orderProductCount=" + orderProductCount +
                ", orderProductImage='" + orderProductImage + '\'' +
                ", orderId=" + orderId +
                ", orderUserId=" + orderUserId +
                ", orderUserName='" + orderUserName + '\'' +
                ", orderProductDesc='" + orderProductDesc + '\'' +
                ", orderProductPrice=" + orderProductPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
