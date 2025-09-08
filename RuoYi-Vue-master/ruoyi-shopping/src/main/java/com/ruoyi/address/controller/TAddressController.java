package com.ruoyi.address.controller;

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
import com.ruoyi.address.domain.TAddress;
import com.ruoyi.address.service.ITAddressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地址信息Controller
 * 
 * @author lhl
 * @date 2025-05-11
 */
@RestController
@RequestMapping("/shopping/address")
public class TAddressController extends BaseController
{
    @Autowired
    private ITAddressService tAddressService;

    /**
     * 查询地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAddress tAddress)
    {
        startPage();
        List<TAddress> list = tAddressService.selectTAddressList(tAddress);
        return getDataTable(list);
    }


    /**
     * 导出地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:address:export')")
    @Log(title = "地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TAddress tAddress)
    {
        List<TAddress> list = tAddressService.selectTAddressList(tAddress);
        ExcelUtil<TAddress> util = new ExcelUtil<TAddress>(TAddress.class);
        util.exportExcel(response, list, "地址信息数据");
    }

    /**
     * 获取地址信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:address:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return success(tAddressService.selectTAddressByAddressId(addressId));
    }

    /**
     * 新增地址信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:address:add')")
    @Log(title = "地址信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAddress tAddress)
    {
        return toAjax(tAddressService.insertTAddress(tAddress));
    }

    /**
     * 修改地址信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:address:edit')")
    @Log(title = "地址信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAddress tAddress)
    {
        return toAjax(tAddressService.updateTAddress(tAddress));
    }

    /**
     * 删除地址信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:address:remove')")
    @Log(title = "地址信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(tAddressService.deleteTAddressByAddressIds(addressIds));
    }
}
