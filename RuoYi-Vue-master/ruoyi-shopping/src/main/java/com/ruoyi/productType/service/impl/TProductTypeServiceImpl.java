package com.ruoyi.productType.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.productType.mapper.TProductTypeMapper;
import com.ruoyi.productType.domain.TProductType;
import com.ruoyi.productType.service.ITProductTypeService;

/**
 * 商品类别Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-09
 */
@Service
public class TProductTypeServiceImpl implements ITProductTypeService 
{
    @Autowired
    private TProductTypeMapper tProductTypeMapper;

    /**
     * 查询商品类别
     * 
     * @param typeId 商品类别主键
     * @return 商品类别
     */
    @Override
    public TProductType selectTProductTypeByTypeId(Long typeId)
    {
        return tProductTypeMapper.selectTProductTypeByTypeId(typeId);
    }

    /**
     * 查询商品类别列表
     * 
     * @param tProductType 商品类别
     * @return 商品类别
     */
    @Override
    public List<TProductType> selectTProductTypeList(TProductType tProductType)
    {
        return tProductTypeMapper.selectTProductTypeList(tProductType);
    }

    /**
     * 新增商品类别
     * 
     * @param tProductType 商品类别
     * @return 结果
     */
    @Override
    public int insertTProductType(TProductType tProductType)
    {
        return tProductTypeMapper.insertTProductType(tProductType);
    }

    /**
     * 修改商品类别
     * 
     * @param tProductType 商品类别
     * @return 结果
     */
    @Override
    public int updateTProductType(TProductType tProductType)
    {
        return tProductTypeMapper.updateTProductType(tProductType);
    }

    /**
     * 批量删除商品类别
     * 
     * @param typeIds 需要删除的商品类别主键
     * @return 结果
     */
    @Override
    public int deleteTProductTypeByTypeIds(Long[] typeIds)
    {
        return tProductTypeMapper.deleteTProductTypeByTypeIds(typeIds);
    }

    /**
     * 删除商品类别信息
     * 
     * @param typeId 商品类别主键
     * @return 结果
     */
    @Override
    public int deleteTProductTypeByTypeId(Long typeId)
    {
        return tProductTypeMapper.deleteTProductTypeByTypeId(typeId);
    }
}
