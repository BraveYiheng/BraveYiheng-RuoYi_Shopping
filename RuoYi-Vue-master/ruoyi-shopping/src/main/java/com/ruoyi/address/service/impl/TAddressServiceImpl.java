package com.ruoyi.address.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.address.mapper.TAddressMapper;
import com.ruoyi.address.domain.TAddress;
import com.ruoyi.address.service.ITAddressService;

/**
 * 地址信息Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-11
 */
@Service
public class TAddressServiceImpl implements ITAddressService 
{
    @Autowired
    private TAddressMapper tAddressMapper;

    /**
     * 查询地址信息
     * 
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    @Override
    public TAddress selectTAddressByAddressId(Long addressId)
    {
        return tAddressMapper.selectTAddressByAddressId(addressId);
    }

    /**
     * 查询地址信息列表
     * 
     * @param tAddress 地址信息
     * @return 地址信息
     */
    @Override
    public List<TAddress> selectTAddressList(TAddress tAddress)
    {
        return tAddressMapper.selectTAddressList(tAddress);
    }

    /**
     * 新增地址信息
     * 
     * @param tAddress 地址信息
     * @return 结果
     */
    @Override
    public int insertTAddress(TAddress tAddress)
    {
        return tAddressMapper.insertTAddress(tAddress);
    }

    /**
     * 修改地址信息
     * 
     * @param tAddress 地址信息
     * @return 结果
     */
    @Override
    public int updateTAddress(TAddress tAddress)
    {
        return tAddressMapper.updateTAddress(tAddress);
    }

    /**
     * 批量删除地址信息
     * 
     * @param addressIds 需要删除的地址信息主键
     * @return 结果
     */
    @Override
    public int deleteTAddressByAddressIds(Long[] addressIds)
    {
        return tAddressMapper.deleteTAddressByAddressIds(addressIds);
    }

    /**
     * 删除地址信息信息
     * 
     * @param addressId 地址信息主键
     * @return 结果
     */
    @Override
    public int deleteTAddressByAddressId(Long addressId)
    {
        return tAddressMapper.deleteTAddressByAddressId(addressId);
    }
}
