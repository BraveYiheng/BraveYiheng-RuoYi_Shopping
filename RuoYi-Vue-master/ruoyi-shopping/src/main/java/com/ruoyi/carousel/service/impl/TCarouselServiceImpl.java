package com.ruoyi.carousel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.carousel.mapper.TCarouselMapper;
import com.ruoyi.carousel.domain.TCarousel;
import com.ruoyi.carousel.service.ITCarouselService;

/**
 * 轮播图管理Service业务层处理
 * 
 * @author lhl
 * @date 2025-05-11
 */
@Service
public class TCarouselServiceImpl implements ITCarouselService 
{
    @Autowired
    private TCarouselMapper tCarouselMapper;

    /**
     * 查询轮播图管理
     * 
     * @param carouselId 轮播图管理主键
     * @return 轮播图管理
     */
    @Override
    public TCarousel selectTCarouselByCarouselId(Long carouselId)
    {
        return tCarouselMapper.selectTCarouselByCarouselId(carouselId);
    }

    /**
     * 查询轮播图管理列表
     * 
     * @param tCarousel 轮播图管理
     * @return 轮播图管理
     */
    @Override
    public List<TCarousel> selectTCarouselList(TCarousel tCarousel)
    {
        return tCarouselMapper.selectTCarouselList(tCarousel);
    }

    /**
     * 新增轮播图管理
     * 
     * @param tCarousel 轮播图管理
     * @return 结果
     */
    @Override
    public int insertTCarousel(TCarousel tCarousel)
    {
        return tCarouselMapper.insertTCarousel(tCarousel);
    }

    /**
     * 修改轮播图管理
     * 
     * @param tCarousel 轮播图管理
     * @return 结果
     */
    @Override
    public int updateTCarousel(TCarousel tCarousel)
    {
        return tCarouselMapper.updateTCarousel(tCarousel);
    }

    /**
     * 批量删除轮播图管理
     * 
     * @param carouselIds 需要删除的轮播图管理主键
     * @return 结果
     */
    @Override
    public int deleteTCarouselByCarouselIds(Long[] carouselIds)
    {
        return tCarouselMapper.deleteTCarouselByCarouselIds(carouselIds);
    }

    /**
     * 删除轮播图管理信息
     * 
     * @param carouselId 轮播图管理主键
     * @return 结果
     */
    @Override
    public int deleteTCarouselByCarouselId(Long carouselId)
    {
        return tCarouselMapper.deleteTCarouselByCarouselId(carouselId);
    }
}
