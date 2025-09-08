package com.ruoyi.userInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息对象 t_user
 * 
 * @author lhl
 * @date 2025-05-11
 */
public class TUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 账号 */
    @Excel(name = "账号")
    private String userName;

    /** 密码 */
    @Excel(name = "密码")
    private String userPwd;

    /** 头像 */
    @Excel(name = "头像")
    private String userPhoto;

    /** 昵称 */
    @Excel(name = "昵称")
    private String userNickName;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("userPwd", getUserPwd())
                .append("userPhoto", getUserPhoto())
                .append("userNickName", getUserNickName())
                .toString();
    }
}
