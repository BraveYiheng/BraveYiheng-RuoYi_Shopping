package com.ruoyi.productType.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品类别对象 t_product_type
 * 
 * @author lhl
 * @date 2025-05-09
 */
public class TProductType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long typeId;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private String typeName;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }

    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .toString();
    }
}
