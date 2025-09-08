package com.ruoyi.productType.mapper;

import java.util.List;
import com.ruoyi.productType.domain.TProductType;

/**
 * 商品类别Mapper接口
 * 
 * @author lhl
 * @date 2025-05-09
 */
public interface TProductTypeMapper 
{
    /**
     * 查询商品类别
     * 
     * @param typeId 商品类别主键
     * @return 商品类别
     */
    public TProductType selectTProductTypeByTypeId(Long typeId);

    /**
     * 查询商品类别列表
     * 
     * @param tProductType 商品类别
     * @return 商品类别集合
     */
    public List<TProductType> selectTProductTypeList(TProductType tProductType);

    /**
     * 新增商品类别
     * 
     * @param tProductType 商品类别
     * @return 结果
     */
    public int insertTProductType(TProductType tProductType);

    /**
     * 修改商品类别
     * 
     * @param tProductType 商品类别
     * @return 结果
     */
    public int updateTProductType(TProductType tProductType);

    /**
     * 删除商品类别
     * 
     * @param typeId 商品类别主键
     * @return 结果
     */
    public int deleteTProductTypeByTypeId(Long typeId);

    /**
     * 批量删除商品类别
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTProductTypeByTypeIds(Long[] typeIds);
}
