package com.ruoyi.orderList.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单信息对象 t_orders
 * 
 * @author lhl
 * @date 2025-05-11
 */
public class TOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long orderId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long orderUserId;

    /** 用户ID */
    @Excel(name = "用户名")
    private String orderUserNickName;

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private Long orderMoney;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long orderStatus;

    /** 创建订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderAddTime;

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

    public String getOrderUserNickName() {
        return orderUserNickName;
    }

    public void setOrderUserNickName(String orderUserNickName) {
        this.orderUserNickName = orderUserNickName;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderAddTime() {
        return orderAddTime;
    }

    public void setOrderAddTime(Date orderAddTime) {
        this.orderAddTime = orderAddTime;
    }

    @Override
    public String toString() {
        return "TOrders{" +
                "orderId=" + orderId +
                ", orderUserId=" + orderUserId +
                ", orderUserNickName='" + orderUserNickName + '\'' +
                ", orderMoney=" + orderMoney +
                ", orderStatus=" + orderStatus +
                ", orderAddTime=" + orderAddTime +
                '}';
    }
}
