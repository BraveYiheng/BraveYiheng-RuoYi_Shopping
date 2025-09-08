package com.ruoyi.orderList.service;

import java.util.List;
import com.ruoyi.orderList.domain.TOrders;

/**
 * 订单信息Service接口
 * 
 * @author lhl
 * @date 2025-05-11
 */
public interface ITOrdersService 
{
    /**
     * 查询订单信息
     * 
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    public TOrders selectTOrdersByOrderId(Long orderId);

    /**
     * 查询订单信息列表
     * 
     * @param tOrders 订单信息
     * @return 订单信息集合
     */
    public List<TOrders> selectTOrdersList(TOrders tOrders);

    /**
     * 查询订单信息列表
     *
     * @param tOrders 订单信息
     * @return 订单信息集合
     */
    public List<TOrders> selectUpdateOrderId(TOrders tOrders);


    /**
     * 新增订单信息
     * 
     * @param tOrders 订单信息
     * @return 结果
     */
    public int insertTOrders(TOrders tOrders);

    /**
     * 修改订单信息
     * 
     * @param tOrders 订单信息
     * @return 结果
     */
    public int updateTOrders(TOrders tOrders);

    /**
     * 批量删除订单信息
     * 
     * @param orderIds 需要删除的订单信息主键集合
     * @return 结果
     */
    public int deleteTOrdersByOrderIds(Long[] orderIds);

    /**
     * 删除订单信息信息
     * 
     * @param orderId 订单信息主键
     * @return 结果
     */
    public int deleteTOrdersByOrderId(Long orderId);
}
