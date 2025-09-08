package com.ruoyi.cart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cart.mapper.TCartMapper;
import com.ruoyi.cart.domain.TCart;
import com.ruoyi.cart.service.ITCartService;

/**
 * 购物车信息管理Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-11
 */
@Service
public class TCartServiceImpl implements ITCartService 
{
    @Autowired
    private TCartMapper tCartMapper;

    /**
     * 查询购物车信息管理
     * 
     * @param cartId 购物车信息管理主键
     * @return 购物车信息管理
     */
    @Override
    public TCart selectTCartByCartId(Long cartId)
    {
        return tCartMapper.selectTCartByCartId(cartId);
    }

    /**
     * 查询购物车信息管理列表
     * 
     * @param tCart 购物车信息管理
     * @return 购物车信息管理
     */
    @Override
    public List<TCart> selectTCartList(TCart tCart)
    {
        return tCartMapper.selectTCartList(tCart);
    }

    /**
     * 新增购物车信息管理
     * 
     * @param tCart 购物车信息管理
     * @return 结果
     */
    @Override
    public int insertTCart(TCart tCart)
    {
        return tCartMapper.insertTCart(tCart);
    }

    /**
     * 修改购物车信息管理
     * 
     * @param tCart 购物车信息管理
     * @return 结果
     */
    @Override
    public int updateTCart(TCart tCart)
    {
        return tCartMapper.updateTCart(tCart);
    }

    /**
     * 批量删除购物车信息管理
     * 
     * @param cartIds 需要删除的购物车信息管理主键
     * @return 结果
     */
    @Override
    public int deleteTCartByCartIds(Long[] cartIds)
    {
        return tCartMapper.deleteTCartByCartIds(cartIds);
    }

    /**
     * 删除购物车信息管理信息
     * 
     * @param cartId 购物车信息管理主键
     * @return 结果
     */
    @Override
    public int deleteTCartByCartId(Long cartId)
    {
        return tCartMapper.deleteTCartByCartId(cartId);
    }
}
