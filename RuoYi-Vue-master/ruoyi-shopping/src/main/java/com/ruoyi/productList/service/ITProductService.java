package com.ruoyi.productList.service;

import java.util.List;
import com.ruoyi.productList.domain.TProduct;

/**
 * 商品列表Service接口
 * 
 * @author lhl
 * @date 2025-05-09
 */
public interface ITProductService 
{
    /**
     * 查询商品列表
     * 
     * @param productId 商品列表主键
     * @return 商品列表
     */
    public TProduct selectTProductByProductId(Long productId);

    /**
     * 查询商品列表列表
     * 
     * @param tProduct 商品列表
     * @return 商品列表集合
     */
    public List<TProduct> selectTProductList(TProduct tProduct);

    /**
     * 新增商品列表
     * 
     * @param tProduct 商品列表
     * @return 结果
     */
    public int insertTProduct(TProduct tProduct);

    /**
     * 修改商品列表
     * 
     * @param tProduct 商品列表
     * @return 结果
     */
    public int updateTProduct(TProduct tProduct);

    /**
     * 批量删除商品列表
     * 
     * @param productIds 需要删除的商品列表主键集合
     * @return 结果
     */
    public int deleteTProductByProductIds(Long[] productIds);

    /**
     * 删除商品列表信息
     * 
     * @param productId 商品列表主键
     * @return 结果
     */
    public int deleteTProductByProductId(Long productId);
}
