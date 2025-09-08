package com.ruoyi.userInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.userInfo.mapper.TUserMapper;
import com.ruoyi.userInfo.domain.TUser;
import com.ruoyi.userInfo.service.ITUserService;

/**
 * 用户信息Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-11
 */
@Service
public class TUserServiceImpl implements ITUserService {
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public TUser selectTUserByUserId(Long userId) {
        return tUserMapper.selectTUserByUserId(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param tUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<TUser> selectTUserList(TUser tUser) {
        return tUserMapper.selectTUserList(tUser);
    }

    /**
     * 新增用户信息
     * 
     * @param tUser 用户信息
     * @return 结果
     */
    @Override
    public int insertTUser(TUser tUser) {
        return tUserMapper.insertTUser(tUser);
    }

    /**
     * 修改用户信息
     * 
     * @param tUser 用户信息
     * @return 结果
     */
    @Override
    public int updateTUser(TUser tUser) {
        return tUserMapper.updateTUser(tUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTUserByUserIds(Long[] userIds) {
        return tUserMapper.deleteTUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTUserByUserId(Long userId) {
        return tUserMapper.deleteTUserByUserId(userId);
    }

    @Override
    public TUser selectTUserByUserName(String userName) {
        return tUserMapper.selectTUserByUserName(userName);
    }
}
