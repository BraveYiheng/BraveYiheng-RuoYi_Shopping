package com.ruoyi.cart.controller;

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
import com.ruoyi.cart.domain.TCart;
import com.ruoyi.cart.service.ITCartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 购物车信息管理Controller
 * 
 * @author lhl
 * @date 2025-05-11
 */
@RestController
@RequestMapping("/shopping/cart")
public class TCartController extends BaseController
{
    @Autowired
    private ITCartService tCartService;

    /**
     * 查询购物车信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:cart:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCart tCart)
    {
        startPage();
        List<TCart> list = tCartService.selectTCartList(tCart);
        return getDataTable(list);
    }

    /**
     * 导出购物车信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:cart:export')")
    @Log(title = "购物车信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCart tCart)
    {
        List<TCart> list = tCartService.selectTCartList(tCart);
        ExcelUtil<TCart> util = new ExcelUtil<TCart>(TCart.class);
        util.exportExcel(response, list, "购物车信息管理数据");
    }

    /**
     * 获取购物车信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:cart:query')")
    @GetMapping(value = "/{cartId}")
    public AjaxResult getInfo(@PathVariable("cartId") Long cartId)
    {
        return success(tCartService.selectTCartByCartId(cartId));
    }

    /**
     * 新增购物车信息管理
     */
    @PreAuthorize("@ss.hasPermi('shopping:cart:add')")
    @Log(title = "购物车信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCart tCart)
    {
        return toAjax(tCartService.insertTCart(tCart));
    }

    /**
     * 修改购物车信息管理
     */
    @PreAuthorize("@ss.hasPermi('shopping:cart:edit')")
    @Log(title = "购物车信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCart tCart)
    {
        return toAjax(tCartService.updateTCart(tCart));
    }

    /**
     * 删除购物车信息管理
     */
    @PreAuthorize("@ss.hasPermi('shopping:cart:remove')")
    @Log(title = "购物车信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cartIds}")
    public AjaxResult remove(@PathVariable Long[] cartIds)
    {
        return toAjax(tCartService.deleteTCartByCartIds(cartIds));
    }
}
