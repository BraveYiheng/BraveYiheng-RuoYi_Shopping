package com.ruoyi.productType.controller;

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
import com.ruoyi.productType.domain.TProductType;
import com.ruoyi.productType.service.ITProductTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品类别Controller
 * 
 * @author lhl
 * @date 2025-05-09
 */
@RestController
@RequestMapping("/product/type")
public class TProductTypeController extends BaseController
{
    @Autowired
    private ITProductTypeService tProductTypeService;

    /**
     * 查询商品类别列表
     * 包含分页功能
     */
    @PreAuthorize("@ss.hasPermi('product:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(TProductType tProductType)
    {
        startPage();
        List<TProductType> list = tProductTypeService.selectTProductTypeList(tProductType);
        return getDataTable(list);
    }


    /**
     * 查询商品类别列表
     * 不包含分页功能
     */
    @PreAuthorize("@ss.hasPermi('product:type:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll(TProductType tProductType)
    {
        List<TProductType> list = tProductTypeService.selectTProductTypeList(tProductType);
        return getDataTable(list);
    }

    /**
     * 导出商品类别列表
     */
    @PreAuthorize("@ss.hasPermi('product:type:export')")
    @Log(title = "商品类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TProductType tProductType)
    {
        List<TProductType> list = tProductTypeService.selectTProductTypeList(tProductType);
        ExcelUtil<TProductType> util = new ExcelUtil<TProductType>(TProductType.class);
        util.exportExcel(response, list, "商品类别数据");
    }

    /**
     * 获取商品类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(tProductTypeService.selectTProductTypeByTypeId(typeId));
    }

    /**
     * 新增商品类别
     */
    @PreAuthorize("@ss.hasPermi('product:type:add')")
    @Log(title = "商品类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProductType tProductType)
    {
        return toAjax(tProductTypeService.insertTProductType(tProductType));
    }

    /**
     * 修改商品类别
     */
    @PreAuthorize("@ss.hasPermi('product:type:edit')")
    @Log(title = "商品类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProductType tProductType)
    {
        return toAjax(tProductTypeService.updateTProductType(tProductType));
    }

    /**
     * 删除商品类别
     */
    @PreAuthorize("@ss.hasPermi('product:type:remove')")
    @Log(title = "商品类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(tProductTypeService.deleteTProductTypeByTypeIds(typeIds));
    }
}
