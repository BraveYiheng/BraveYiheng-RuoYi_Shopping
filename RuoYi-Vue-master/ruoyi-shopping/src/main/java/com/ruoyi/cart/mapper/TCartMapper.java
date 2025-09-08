package com.ruoyi.cart.mapper;

import java.util.List;
import com.ruoyi.cart.domain.TCart;

/**
 * 购物车信息管理Mapper接口
 * 
 * @author lhl
 * @date 2025-05-11
 */
public interface TCartMapper 
{
    /**
     * 查询购物车信息管理
     * 
     * @param cartId 购物车信息管理主键
     * @return 购物车信息管理
     */
    public TCart selectTCartByCartId(Long cartId);

    /**
     * 查询购物车信息管理列表
     * 
     * @param tCart 购物车信息管理
     * @return 购物车信息管理集合
     */
    public List<TCart> selectTCartList(TCart tCart);

    /**
     * 新增购物车信息管理
     * 
     * @param tCart 购物车信息管理
     * @return 结果
     */
    public int insertTCart(TCart tCart);

    /**
     * 修改购物车信息管理
     * 
     * @param tCart 购物车信息管理
     * @return 结果
     */
    public int updateTCart(TCart tCart);

    /**
     * 删除购物车信息管理
     * 
     * @param cartId 购物车信息管理主键
     * @return 结果
     */
    public int deleteTCartByCartId(Long cartId);

    /**
     * 批量删除购物车信息管理
     * 
     * @param cartIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCartByCartIds(Long[] cartIds);
}
