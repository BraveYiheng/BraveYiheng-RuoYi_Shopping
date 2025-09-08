package com.ruoyi.ordersDetail.mapper;

import java.util.List;
import com.ruoyi.ordersDetail.domain.TOrderDetail;

/**
 * 订单详细信息Mapper接口
 * 
 * @author lhl
 * @date 2025-05-11
 */
public interface TOrderDetailMapper 
{
    /**
     * 查询订单详细信息
     * 
     * @param orderDetailId 订单详细信息主键
     * @return 订单详细信息
     */
    public TOrderDetail selectTOrderDetailByOrderDetailId(Long orderDetailId);

    /**
     * 查询订单详细信息列表
     * 
     * @param tOrderDetail 订单详细信息
     * @return 订单详细信息集合
     */
    public List<TOrderDetail> selectTOrderDetailList(TOrderDetail tOrderDetail);

    /**
     * 新增订单详细信息
     * 
     * @param tOrderDetail 订单详细信息
     * @return 结果
     */
    public int insertTOrderDetail(TOrderDetail tOrderDetail);

    /**
     * 修改订单详细信息
     * 
     * @param tOrderDetail 订单详细信息
     * @return 结果
     */
    public int updateTOrderDetail(TOrderDetail tOrderDetail);

    /**
     * 删除订单详细信息
     * 
     * @param orderDetailId 订单详细信息主键
     * @return 结果
     */
    public int deleteTOrderDetailByOrderDetailId(Long orderDetailId);

    /**
     * 批量删除订单详细信息
     * 
     * @param orderDetailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTOrderDetailByOrderDetailIds(Long[] orderDetailIds);
}
