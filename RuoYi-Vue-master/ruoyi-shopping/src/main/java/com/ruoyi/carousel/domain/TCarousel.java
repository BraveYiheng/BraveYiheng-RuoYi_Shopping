package com.ruoyi.carousel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图管理对象 t_carousel
 * 
 * @author lhl
 * @date 2025-05-11
 */
public class TCarousel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 轮播图ID */
    private Long carouselId;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String carouselUrl;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String carouselName;

    public void setCarouselId(Long carouselId) 
    {
        this.carouselId = carouselId;
    }

    public Long getCarouselId() 
    {
        return carouselId;
    }

    public void setCarouselUrl(String carouselUrl) 
    {
        this.carouselUrl = carouselUrl;
    }

    public String getCarouselUrl() 
    {
        return carouselUrl;
    }

    public void setCarouselName(String carouselName) 
    {
        this.carouselName = carouselName;
    }

    public String getCarouselName() 
    {
        return carouselName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("carouselId", getCarouselId())
            .append("carouselUrl", getCarouselUrl())
            .append("carouselName", getCarouselName())
            .toString();
    }
}
