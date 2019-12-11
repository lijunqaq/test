package com.lj.excel.service;

import com.baomidou.mybatisplus.service.IService;
import com.lj.excel.entity.SysApiRoleAccount;

/**
 * <p>
 * 三方数据对接接口权限配置表 服务类
 * </p>
 *
 * @author lijun
 * @since 2019-12-11
 */
public interface SysApiRoleAccountService extends IService<SysApiRoleAccount> {

    boolean batchImport();
}
