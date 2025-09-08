package com.ruoyi.carousel.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.carousel.domain.TCarousel;
import com.ruoyi.carousel.service.ITCarouselService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 轮播图管理Controller
 * 
 * @author lhl
 * @date 2025-05-11
 */
@RestController
@RequestMapping("/shopping/carousel")
public class TCarouselController extends BaseController
{
    @Autowired
    private ITCarouselService tCarouselService;

    /**
     * 查询轮播图管理列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:carousel:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCarousel tCarousel)
    {
        startPage();
        List<TCarousel> list = tCarouselService.selectTCarouselList(tCarousel);
        return getDataTable(list);
    }

    /**
     * 导出轮播图管理列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:carousel:export')")
    @Log(title = "轮播图管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCarousel tCarousel)
    {
        List<TCarousel> list = tCarouselService.selectTCarouselList(tCarousel);
        ExcelUtil<TCarousel> util = new ExcelUtil<TCarousel>(TCarousel.class);
        util.exportExcel(response, list, "轮播图管理数据");
    }

    /**
     * 获取轮播图管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:carousel:query')")
    @GetMapping(value = "/{carouselId}")
    public AjaxResult getInfo(@PathVariable("carouselId") Long carouselId)
    {
        return success(tCarouselService.selectTCarouselByCarouselId(carouselId));
    }

    /**
     * 新增轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('shopping:carousel:add')")
    @Log(title = "轮播图管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCarousel tCarousel)
    {
        return toAjax(tCarouselService.insertTCarousel(tCarousel));
    }

    /**
     * 修改轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('shopping:carousel:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCarousel tCarousel)
    {
        return toAjax(tCarouselService.updateTCarousel(tCarousel));
    }

    /**
     * 删除轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('shopping:carousel:remove')")
    @Log(title = "轮播图管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{carouselIds}")
    public AjaxResult remove(@PathVariable Long[] carouselIds)
    {
        return toAjax(tCarouselService.deleteTCarouselByCarouselIds(carouselIds));
    }
}
