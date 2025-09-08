package com.ruoyi.productList.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品列表对象 t_product
 * 
 * @author lhl
 * @date 2025-05-09
 */
public class TProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private Double productPrice;

    /** 起始商品价格 */
    private Double beginProductPrice;

    /** 结束商品价格 */
    private Double endProductPrice;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Long productCount;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String productImg;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String productSize;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productCreateTime;

    /** 商品品牌 */
    @Excel(name = "商品品牌")
    private String productBrand;

    /** 商品类别 */
    @Excel(name = "商品类别id")
    private Long productTypeId;

    /** 商品包装 */
    @Excel(name = "商品包装")
    private String productPackage;

    /** 商品标识 */
    @Excel(name = "商品标识")
    private String productSign;

    /** 是否加糖 */
    @Excel(name = "是否加糖")
    private Long productSugar;

    /** 商品状态 */
    @Excel(name = "商品状态")
    private Long productStatus;

    /** 商品类别联表后查出属性 */
    @Excel(name = "商品类别名称")
    private String productTypeName;

    /** 商品状态 */
    @Excel(name = "商品描述")
    private String productDescribe;

    /** 商品排序 0升序 1降序 */
    @Excel(name = "商品价格排序")
    private Long productSort;

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductPrice(Double productPrice)
    {
        this.productPrice = productPrice;
    }

    public Double getProductPrice()
    {
        return productPrice;
    }

    public Double getEndProductPrice() {
        return endProductPrice;
    }

    public void setEndProductPrice(Double endProductPrice) {
        this.endProductPrice = endProductPrice;
    }

    public Double getBeginProductPrice() {
        return beginProductPrice;
    }

    public void setBeginProductPrice(Double beginProductPrice) {
        this.beginProductPrice = beginProductPrice;
    }

    public void setProductCount(Long productCount)
    {
        this.productCount = productCount;
    }

    public Long getProductCount()
    {
        return productCount;
    }

    public void setProductImg(String productImg)
    {
        this.productImg = productImg;
    }

    public String getProductImg()
    {
        return productImg;
    }

    public void setProductSize(String productSize)
    {
        this.productSize = productSize;
    }

    public String getProductSize()
    {
        return productSize;
    }

    public void setProductCreateTime(Date productCreateTime)
    {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductCreateTime()
    {
        return productCreateTime;
    }

    public void setProductBrand(String productBrand)
    {
        this.productBrand = productBrand;
    }

    public String getProductBrand()
    {
        return productBrand;
    }

    public void setProductTypeId(Long productTypeId)
    {
        this.productTypeId = productTypeId;
    }

    public Long getProductTypeId()
    {
        return productTypeId;
    }

    public void setProductPackage(String productPackage)
    {
        this.productPackage = productPackage;
    }

    public String getProductPackage()
    {
        return productPackage;
    }

    public void setProductSign(String productSign)
    {
        this.productSign = productSign;
    }

    public String getProductSign()
    {
        return productSign;
    }

    public void setProductSugar(Long productSugar)
    {
        this.productSugar = productSugar;
    }

    public Long getProductSugar()
    {
        return productSugar;
    }

    public void setProductStatus(Long productStatus)
    {
        this.productStatus = productStatus;
    }

    public Long getProductStatus()
    {
        return productStatus;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
    }

    public Long getProductSort() {
        return productSort;
    }

    public void setProductSort(Long productSort) {
        this.productSort = productSort;
    }

    @Override
    public String toString() {
        return "TProduct{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", beginProductPrice=" + beginProductPrice +
                ", endProductPrice=" + endProductPrice +
                ", productCount=" + productCount +
                ", productImg='" + productImg + '\'' +
                ", productSize='" + productSize + '\'' +
                ", productCreateTime=" + productCreateTime +
                ", productBrand='" + productBrand + '\'' +
                ", productTypeId=" + productTypeId +
                ", productPackage='" + productPackage + '\'' +
                ", productSign='" + productSign + '\'' +
                ", productSugar=" + productSugar +
                ", productStatus=" + productStatus +
                ", productTypeName='" + productTypeName + '\'' +
                ", productDescribe='" + productDescribe + '\'' +
                ", productSort=" + productSort +
                '}';
    }
}
