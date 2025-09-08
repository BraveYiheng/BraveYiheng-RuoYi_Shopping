package com.ruoyi.address.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地址信息对象 t_address
 *
 * @author lhl
 * @date 2025-05-11
 */
public class TAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地址ID */
    private Long addressId;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 收货手机号 */
    @Excel(name = "收货手机号")
    private String phone;

    /** 用户名Id */
    @Excel(name = "用户名Id")
    private Long addressUserId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String addressUserNickName;

    /** 是否为默认地址 */
    @Excel(name = "是否为默认地址")
    private Long addressIsfo;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAddressUserId() {
        return addressUserId;
    }

    public void setAddressUserId(Long addressUserId) {
        this.addressUserId = addressUserId;
    }

    public String getAddressUserNickName() {
        return addressUserNickName;
    }

    public void setAddressUserNickName(String addressUserNickName) {
        this.addressUserNickName = addressUserNickName;
    }

    public Long getAddressIsfo() {
        return addressIsfo;
    }

    public void setAddressIsfo(Long addressIsfo) {
        this.addressIsfo = addressIsfo;
    }

    @Override
    public String toString() {
        return "TAddress{" +
                "addressId=" + addressId +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", addressUserId=" + addressUserId +
                ", addressUserNickName='" + addressUserNickName + '\'' +
                ", addressIsfo=" + addressIsfo +
                '}';
    }
}
