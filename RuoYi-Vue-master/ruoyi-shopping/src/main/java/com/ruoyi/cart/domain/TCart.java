package com.ruoyi.cart.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车信息管理对象 t_cart
 *
 * @author lhl
 * @date 2025-05-11
 */
public class TCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 购物车记录ID */
    private Long cartId;

    /** 用户id */
    @Excel(name = "用户Id")
    private Long cartUserId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String cartUserNickName;

    /** 商品id */
    @Excel(name = "商品Id")
    private Long cartProductId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String cartProductName;

    /** 商品图片地址 */
    @Excel(name = "商品图片地址")
    private String cartProductUrl;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private String cartProductPrice;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long productCount;

//    /** 需要花费 */
//    @Excel(name = "商品数量")
//    private Long productCount;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productAddTime;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(Long cartUserId) {
        this.cartUserId = cartUserId;
    }

    public String getCartUserNickName() {
        return cartUserNickName;
    }

    public void setCartUserNickName(String cartUserNickName) {
        this.cartUserNickName = cartUserNickName;
    }

    public Long getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Long cartProductId) {
        this.cartProductId = cartProductId;
    }

    public String getCartProductName() {
        return cartProductName;
    }

    public void setCartProductName(String cartProductName) {
        this.cartProductName = cartProductName;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Date getProductAddTime() {
        return productAddTime;
    }

    public void setProductAddTime(Date productAddTime) {
        this.productAddTime = productAddTime;
    }

    public String getCartProductUrl() {
        return cartProductUrl;
    }

    public void setCartProductUrl(String cartProductUrl) {
        this.cartProductUrl = cartProductUrl;
    }

    public String getCartProductPrice() {
        return cartProductPrice;
    }

    public void setCartProductPrice(String cartProductPrice) {
        this.cartProductPrice = cartProductPrice;
    }

    @Override
    public String toString() {
        return "TCart{" +
                "cartId=" + cartId +
                ", cartUserId=" + cartUserId +
                ", cartUserNickName='" + cartUserNickName + '\'' +
                ", cartProductId=" + cartProductId +
                ", cartProductName='" + cartProductName + '\'' +
                ", cartProductUrl='" + cartProductUrl + '\'' +
                ", cartProductPrice='" + cartProductPrice + '\'' +
                ", productCount=" + productCount +
                ", productAddTime=" + productAddTime +
                '}';
    }
}
