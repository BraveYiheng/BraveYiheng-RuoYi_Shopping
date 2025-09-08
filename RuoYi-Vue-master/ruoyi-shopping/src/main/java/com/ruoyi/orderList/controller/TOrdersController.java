package com.ruoyi.orderList.controller;

import java.util.Collections;
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
import com.ruoyi.orderList.domain.TOrders;
import com.ruoyi.orderList.service.ITOrdersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单信息Controller
 * 
 * @author lhl
 * @date 2025-05-11
 */
@RestController
@RequestMapping("/shopping/orders")
public class TOrdersController extends BaseController
{
    @Autowired
    private ITOrdersService tOrdersService;

    /**
     * 查询订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:list')")
    @GetMapping("/list")
    public TableDataInfo list(TOrders tOrders)
    {
        startPage();
        List<TOrders> list = tOrdersService.selectTOrdersList(tOrders);
        return getDataTable(list);
    }

    /**
     * 查询出最新增加订单信息id
     * 控制器版
     */
    /*@PreAuthorize("@ss.hasPermi('shopping:orders:list')")
    @GetMapping("/listUpdateId")
    public TableDataInfo listUpdateId(TOrders tOrders)
    {
        // 查询所有订单（按Id排序，最新的在最后）
        List<TOrders> list = tOrdersService.selectTOrdersList(tOrders);

        // 只保留最后一条记录（最新的订单）
        if (list != null && !list.isEmpty()) {
            list = Collections.singletonList(list.get(list.size() - 1));
        }
        return getDataTable(list);
    }*/

    /**
     * 查询出最新增加订单信息id
     * XML文件版
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:list')")
    @GetMapping("/listUpdateId")
    public TableDataInfo listUpdateId(TOrders tOrders)
    {
        // 查询所有订单（按Id排序，最新的在最后）
        List<TOrders> list = tOrdersService.selectUpdateOrderId(tOrders);

        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:export')")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TOrders tOrders)
    {
        List<TOrders> list = tOrdersService.selectTOrdersList(tOrders);
        ExcelUtil<TOrders> util = new ExcelUtil<TOrders>(TOrders.class);
        util.exportExcel(response, list, "订单信息数据");
    }

    /**
     * 获取订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(tOrdersService.selectTOrdersByOrderId(orderId));
    }

    /**
     * 新增订单信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:add')")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TOrders tOrders)
    {
        return toAjax(tOrdersService.insertTOrders(tOrders));
    }

    /**
     * 修改订单信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:edit')")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TOrders tOrders)
    {
        return toAjax(tOrdersService.updateTOrders(tOrders));
    }

    /**
     * 删除订单信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:orders:remove')")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(tOrdersService.deleteTOrdersByOrderIds(orderIds));
    }
}
