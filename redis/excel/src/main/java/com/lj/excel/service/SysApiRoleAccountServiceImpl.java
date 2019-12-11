package com.lj.excel.service;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.lj.excel.entity.SysApiRoleAccount;
import com.lj.excel.mapper.SysApiRoleAccountMapper;
import com.lj.excel.utils.ExcelUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 三方数据对接接口权限配置表 服务实现类
 * </p>
 *
 * @author lijun
 * @since 2019-12-11
 */
@Service
public class SysApiRoleAccountServiceImpl extends ServiceImpl<SysApiRoleAccountMapper, SysApiRoleAccount> implements SysApiRoleAccountService {


    @Override
    public boolean batchImport() {
//        try {
//            String fileName = "d:/麻栗坡县单位.xlsx";
//            List<Object[]> list = ExcelUtil.importExcel(fileName);
//            List<SysApiRoleAccount> result = new ArrayList<>();
//
//            for (int i = 0; i < list.size(); i++) {
//                SysApiRoleAccount user = new SysApiRoleAccount();
////                user.setId((Integer) list.get(i)[0]);
//                user.setAccountId(19);
//                user.setUnitId((String) list.get(i)[1]);
//                user.setUnitName((String) list.get(i)[2]);
//                user.setValid(1);
//                result.add(user);
//            }
//            insertBatch(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SysApiRoleAccount sysApiRoleAccount = selectById(1);
        System.out.println("ok");
        return true;
    }
}
