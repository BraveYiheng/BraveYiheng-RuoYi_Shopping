package com.ruoyi.productList.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.productList.mapper.TProductMapper;
import com.ruoyi.productList.domain.TProduct;
import com.ruoyi.productList.service.ITProductService;

/**
 * 商品列表Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-09
 */
@Service
public class TProductServiceImpl implements ITProductService 
{
    @Autowired
    private TProductMapper tProductMapper;

    /**
     * 查询商品列表
     * 
     * @param productId 商品列表主键
     * @return 商品列表
     */
    @Override
    public TProduct selectTProductByProductId(Long productId)
    {
        return tProductMapper.selectTProductByProductId(productId);
    }

    /**
     * 查询商品列表列表
     * 
     * @param tProduct 商品列表
     * @return 商品列表
     */
    @Override
    public List<TProduct> selectTProductList(TProduct tProduct)
    {
        return tProductMapper.selectTProductList(tProduct);
    }

    /**
     * 新增商品列表
     * 
     * @param tProduct 商品列表
     * @return 结果
     */
    @Override
    public int insertTProduct(TProduct tProduct)
    {
        return tProductMapper.insertTProduct(tProduct);
    }

    /**
     * 修改商品列表
     * 
     * @param tProduct 商品列表
     * @return 结果
     */
    @Override
    public int updateTProduct(TProduct tProduct)
    {
        return tProductMapper.updateTProduct(tProduct);
    }

    /**
     * 批量删除商品列表
     * 
     * @param productIds 需要删除的商品列表主键
     * @return 结果
     */
    @Override
    public int deleteTProductByProductIds(Long[] productIds)
    {
        return tProductMapper.deleteTProductByProductIds(productIds);
    }

    /**
     * 删除商品列表信息
     * 
     * @param productId 商品列表主键
     * @return 结果
     */
    @Override
    public int deleteTProductByProductId(Long productId)
    {
        return tProductMapper.deleteTProductByProductId(productId);
    }
}
