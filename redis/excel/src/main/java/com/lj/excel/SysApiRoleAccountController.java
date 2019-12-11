package com.lj.excel;


import com.lj.excel.service.SysApiRoleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 三方数据对接接口权限配置表 前端控制器
 * </p>
 *
 * @author lijun
 * @since 2019-12-11
 */
@Controller
@RequestMapping("/sysApiRoleAccount")
public class SysApiRoleAccountController {
    @Autowired
    private SysApiRoleAccountService sysApiRoleAccountService;

    @PostMapping("/import")
    @ResponseBody
    public boolean addUser() {
        boolean a = false;
        try {
            a = sysApiRoleAccountService.batchImport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }


}

