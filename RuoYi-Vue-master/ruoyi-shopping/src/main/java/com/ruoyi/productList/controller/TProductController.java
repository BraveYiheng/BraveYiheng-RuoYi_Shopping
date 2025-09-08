package com.ruoyi.productList.controller;

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
import com.ruoyi.productList.domain.TProduct;
import com.ruoyi.productList.service.ITProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品列表Controller
 * 
 * @author lhl
 * @date 2025-05-09
 */
@RestController
@RequestMapping("/product/list")
public class TProductController extends BaseController
{
    @Autowired
    private ITProductService tProductService;

    /**
     * 查询商品列表列表
     */
    @PreAuthorize("@ss.hasPermi('product:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(TProduct tProduct)
    {
        startPage();
        List<TProduct> list = tProductService.selectTProductList(tProduct);
        return getDataTable(list);
    }
    

    /**
     * 查询商品列表列表
     */
    @PreAuthorize("@ss.hasPermi('product:list:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll(TProduct tProduct)
    {
        List<TProduct> list = tProductService.selectTProductList(tProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品列表列表
     */
    @PreAuthorize("@ss.hasPermi('product:list:export')")
    @Log(title = "商品列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TProduct tProduct)
    {
        List<TProduct> list = tProductService.selectTProductList(tProduct);
        ExcelUtil<TProduct> util = new ExcelUtil<TProduct>(TProduct.class);
        util.exportExcel(response, list, "商品列表数据");
    }

    /**
     * 获取商品列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:list:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return success(tProductService.selectTProductByProductId(productId));
    }

    /**
     * 新增商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:list:add')")
    @Log(title = "商品列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProduct tProduct)
    {
        return toAjax(tProductService.insertTProduct(tProduct));
    }

    /**
     * 修改商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:list:edit')")
    @Log(title = "商品列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProduct tProduct)
    {
        return toAjax(tProductService.updateTProduct(tProduct));
    }

    /**
     * 删除商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:list:remove')")
    @Log(title = "商品列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(tProductService.deleteTProductByProductIds(productIds));
    }
}
