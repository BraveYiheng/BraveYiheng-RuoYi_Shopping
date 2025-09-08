package com.ruoyi.userInfo.controller;

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
import com.ruoyi.userInfo.domain.TUser;
import com.ruoyi.userInfo.service.ITUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 用户信息Controller
 * 
 * @author lhl
 * @date 2025-05-11
 */
@RestController
@RequestMapping("/shopping/userInfo")
public class TUserController extends BaseController {
    @Autowired
    private ITUserService tUserService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUser tUser) {
        startPage();
        List<TUser> list = tUserService.selectTUserList(tUser);
        return getDataTable(list);
    }

    /**
     * 查询用户信息列表
     * 不包含分页，不含参
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll(TUser tUser) {
        List<TUser> list = tUserService.selectTUserList(tUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUser tUser) {
        List<TUser> list = tUserService.selectTUserList(tUser);
        ExcelUtil<TUser> util = new ExcelUtil<TUser>(TUser.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return success(tUserService.selectTUserByUserId(userId));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUser tUser) {
        return toAjax(tUserService.insertTUser(tUser));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUser tUser) {
        return toAjax(tUserService.updateTUser(tUser));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('shopping:userInfo:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(tUserService.deleteTUserByUserIds(userIds));
    }

    /**
     * 小程序用户登录
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody TUser loginBody) {
        // 1. 校验用户名和密码
        TUser user = tUserService.selectTUserByUserName(loginBody.getUserName());
        if (user == null || !user.getUserPwd().equals(loginBody.getUserPwd())) {
            return AjaxResult.error("用户名或密码错误");
        }
        // 2. 构造 LoginUser（只需 userId、userName，其他可为 null）
        SysUser sysUser = new SysUser();
        sysUser.setUserId(user.getUserId());
        sysUser.setUserName(user.getUserName());
        sysUser.setPassword(user.getUserPwd());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(sysUser);
        loginUser.setUserId(user.getUserId());
        // 3. 生成 token
        String token = tokenService.createToken(loginUser);
        // 4. 返回 token
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.TOKEN, token);

        ajax.put("userId", user.getUserId());
        ajax.put("userName", user.getUserName());
        ajax.put("userPhoto", user.getUserPhoto());


        return ajax;
    }
}
