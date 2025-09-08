package com.ruoyi.address.mapper;

import java.util.List;
import com.ruoyi.address.domain.TAddress;

/**
 * 地址信息Mapper接口
 * 
 * @author lhl
 * @date 2025-05-11
 */
public interface TAddressMapper 
{
    /**
     * 查询地址信息
     * 
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    public TAddress selectTAddressByAddressId(Long addressId);

    /**
     * 查询地址信息列表
     * 
     * @param tAddress 地址信息
     * @return 地址信息集合
     */
    public List<TAddress> selectTAddressList(TAddress tAddress);

    /**
     * 新增地址信息
     * 
     * @param tAddress 地址信息
     * @return 结果
     */
    public int insertTAddress(TAddress tAddress);

    /**
     * 修改地址信息
     * 
     * @param tAddress 地址信息
     * @return 结果
     */
    public int updateTAddress(TAddress tAddress);

    /**
     * 删除地址信息
     * 
     * @param addressId 地址信息主键
     * @return 结果
     */
    public int deleteTAddressByAddressId(Long addressId);

    /**
     * 批量删除地址信息
     * 
     * @param addressIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTAddressByAddressIds(Long[] addressIds);
}
