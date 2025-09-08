package com.ruoyi.ordersDetail.controller;

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
import com.ruoyi.ordersDetail.domain.TOrderDetail;
import com.ruoyi.ordersDetail.service.ITOrderDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单详细信息Controller
 * 
 * @author lhl
 * @date 2025-05-11
 */
@RestController
@RequestMapping("/shopping/ordersDetail")
public class TOrderDetailController extends BaseController
{
    @Autowired
    private ITOrderDetailService tOrderDetailService;

    /**
     * 查询订单详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:ordersDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(TOrderDetail tOrderDetail)
    {
        startPage();
        List<TOrderDetail> list = tOrderDetailService.selectTOrderDetailList(tOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出订单详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:ordersDetail:export')")
    @Log(title = "订单详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TOrderDetail tOrderDetail)
    {
        List<TOrderDetail> list = tOrderDetailService.selectTOrderDetailList(tOrderDetail);
        ExcelUtil<TOrderDetail> util = new ExcelUtil<TOrderDetail>(TOrderDetail.class);
        util.exportExcel(response, list, "订单详细信息数据");
    }

    /**
     * 获取订单详细信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:ordersDetail:query')")
    @GetMapping(value = "/{orderDetailId}")
    public AjaxResult getInfo(@PathVariable("orderDetailId") Long orderDetailId)
    {
        return success(tOrderDetailService.selectTOrderDetailByOrderDetailId(orderDetailId));
    }

    /**
     * 新增订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:ordersDetail:add')")
    @Log(title = "订单详细信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TOrderDetail tOrderDetail)
    {
        return toAjax(tOrderDetailService.insertTOrderDetail(tOrderDetail));
    }

    /**
     * 修改订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:ordersDetail:edit')")
    @Log(title = "订单详细信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TOrderDetail tOrderDetail)
    {
        return toAjax(tOrderDetailService.updateTOrderDetail(tOrderDetail));
    }

    /**
     * 删除订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:ordersDetail:remove')")
    @Log(title = "订单详细信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderDetailIds}")
    public AjaxResult remove(@PathVariable Long[] orderDetailIds)
    {
        return toAjax(tOrderDetailService.deleteTOrderDetailByOrderDetailIds(orderDetailIds));
    }
}
