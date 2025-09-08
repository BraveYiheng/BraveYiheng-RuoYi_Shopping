package com.ruoyi.ordersDetail.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ordersDetail.mapper.TOrderDetailMapper;
import com.ruoyi.ordersDetail.domain.TOrderDetail;
import com.ruoyi.ordersDetail.service.ITOrderDetailService;

/**
 * 订单详细信息Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-11
 */
@Service
public class TOrderDetailServiceImpl implements ITOrderDetailService 
{
    @Autowired
    private TOrderDetailMapper tOrderDetailMapper;

    /**
     * 查询订单详细信息
     * 
     * @param orderDetailId 订单详细信息主键
     * @return 订单详细信息
     */
    @Override
    public TOrderDetail selectTOrderDetailByOrderDetailId(Long orderDetailId)
    {
        return tOrderDetailMapper.selectTOrderDetailByOrderDetailId(orderDetailId);
    }

    /**
     * 查询订单详细信息列表
     * 
     * @param tOrderDetail 订单详细信息
     * @return 订单详细信息
     */
    @Override
    public List<TOrderDetail> selectTOrderDetailList(TOrderDetail tOrderDetail)
    {
        return tOrderDetailMapper.selectTOrderDetailList(tOrderDetail);
    }

    /**
     * 新增订单详细信息
     * 
     * @param tOrderDetail 订单详细信息
     * @return 结果
     */
    @Override
    public int insertTOrderDetail(TOrderDetail tOrderDetail)
    {
        return tOrderDetailMapper.insertTOrderDetail(tOrderDetail);
    }

    /**
     * 修改订单详细信息
     * 
     * @param tOrderDetail 订单详细信息
     * @return 结果
     */
    @Override
    public int updateTOrderDetail(TOrderDetail tOrderDetail)
    {
        return tOrderDetailMapper.updateTOrderDetail(tOrderDetail);
    }

    /**
     * 批量删除订单详细信息
     * 
     * @param orderDetailIds 需要删除的订单详细信息主键
     * @return 结果
     */
    @Override
    public int deleteTOrderDetailByOrderDetailIds(Long[] orderDetailIds)
    {
        return tOrderDetailMapper.deleteTOrderDetailByOrderDetailIds(orderDetailIds);
    }

    /**
     * 删除订单详细信息信息
     * 
     * @param orderDetailId 订单详细信息主键
     * @return 结果
     */
    @Override
    public int deleteTOrderDetailByOrderDetailId(Long orderDetailId)
    {
        return tOrderDetailMapper.deleteTOrderDetailByOrderDetailId(orderDetailId);
    }
}
