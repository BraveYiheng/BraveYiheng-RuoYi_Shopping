package com.ruoyi.userInfo.mapper;

import java.util.List;
import com.ruoyi.userInfo.domain.TUser;

/**
 * 用户信息Mapper接口
 * 
 * @author lhl
 * @date 2025-05-11
 */
public interface TUserMapper {
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public TUser selectTUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param tUser 用户信息
     * @return 用户信息集合
     */
    public List<TUser> selectTUserList(TUser tUser);

    /**
     * 新增用户信息
     * 
     * @param tUser 用户信息
     * @return 结果
     */
    public int insertTUser(TUser tUser);

    /**
     * 修改用户信息
     * 
     * @param tUser 用户信息
     * @return 结果
     */
    public int updateTUser(TUser tUser);

    /**
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteTUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTUserByUserIds(Long[] userIds);

    /**
     * 根据用户名查询用户信息
     * 
     * @param userName 用户名
     * @return 用户信息
     */
    public TUser selectTUserByUserName(String userName);
}
