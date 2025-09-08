package com.ruoyi.carousel.mapper;

import java.util.List;
import com.ruoyi.carousel.domain.TCarousel;

/**
 * 轮播图管理Mapper接口
 * 
 * @author lhl
 * @date 2025-05-11
 */
public interface TCarouselMapper 
{
    /**
     * 查询轮播图管理
     * 
     * @param carouselId 轮播图管理主键
     * @return 轮播图管理
     */
    public TCarousel selectTCarouselByCarouselId(Long carouselId);

    /**
     * 查询轮播图管理列表
     * 
     * @param tCarousel 轮播图管理
     * @return 轮播图管理集合
     */
    public List<TCarousel> selectTCarouselList(TCarousel tCarousel);

    /**
     * 新增轮播图管理
     * 
     * @param tCarousel 轮播图管理
     * @return 结果
     */
    public int insertTCarousel(TCarousel tCarousel);

    /**
     * 修改轮播图管理
     * 
     * @param tCarousel 轮播图管理
     * @return 结果
     */
    public int updateTCarousel(TCarousel tCarousel);

    /**
     * 删除轮播图管理
     * 
     * @param carouselId 轮播图管理主键
     * @return 结果
     */
    public int deleteTCarouselByCarouselId(Long carouselId);

    /**
     * 批量删除轮播图管理
     * 
     * @param carouselIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCarouselByCarouselIds(Long[] carouselIds);
}
