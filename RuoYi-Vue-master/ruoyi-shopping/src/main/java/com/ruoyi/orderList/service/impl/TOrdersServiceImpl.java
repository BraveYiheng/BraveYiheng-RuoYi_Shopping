package com.ruoyi.orderList.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.orderList.mapper.TOrdersMapper;
import com.ruoyi.orderList.domain.TOrders;
import com.ruoyi.orderList.service.ITOrdersService;

/**
 * 订单信息Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-11
 */
@Service
public class TOrdersServiceImpl implements ITOrdersService 
{
    @Autowired
    private TOrdersMapper tOrdersMapper;

    /**
     * 查询订单信息
     * 
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    @Override
    public TOrders selectTOrdersByOrderId(Long orderId)
    {
        return tOrdersMapper.selectTOrdersByOrderId(orderId);
    }

    /**
     * 查询订单信息列表
     * 
     * @param tOrders 订单信息
     * @return 订单信息
     */
    @Override
    public List<TOrders> selectTOrdersList(TOrders tOrders)
    {
        return tOrdersMapper.selectTOrdersList(tOrders);
    }

    /**
     * 查询最新订单ID
     *
     * @param tOrders 订单信息
     * @return 订单信息
     */
    @Override
    public List<TOrders> selectUpdateOrderId(TOrders tOrders)
    {
        return tOrdersMapper.selectUpdateOrderId(tOrders);
    }


    /**
     * 新增订单信息
     * 
     * @param tOrders 订单信息
     * @return 结果
     */
    @Override
    public int insertTOrders(TOrders tOrders)
    {
        return tOrdersMapper.insertTOrders(tOrders);
    }


    /**
     * 修改订单信息
     * 
     * @param tOrders 订单信息
     * @return 结果
     */
    @Override
    public int updateTOrders(TOrders tOrders)
    {
        return tOrdersMapper.updateTOrders(tOrders);
    }

    /**
     * 批量删除订单信息
     * 
     * @param orderIds 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteTOrdersByOrderIds(Long[] orderIds)
    {
        return tOrdersMapper.deleteTOrdersByOrderIds(orderIds);
    }

    /**
     * 删除订单信息信息
     * 
     * @param orderId 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteTOrdersByOrderId(Long orderId)
    {
        return tOrdersMapper.deleteTOrdersByOrderId(orderId);
    }
}
