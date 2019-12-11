package com.lj.excel.entity;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 三方数据对接接口权限配置表
 * </p>
 *
 * @author lijun
 * @since 2019-12-11
 */
@TableName(value = "sys_api_role_account")
public class SysApiRoleAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自动增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号id
     */
    @TableField(value = "account_id")
    private Integer accountId;
    /**
     * 单位id
     */
    @TableField(value = "unit_id")
    private String unitId;
    /**
     * 单位名称
     */
    @TableField(value = "unit_name")
    private String unitName;
    /**
     * 是否有效（0：无效 1：有效）
     */
    private Integer valid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "SysApiRoleAccount{" +
        ", id=" + id +
        ", accountId=" + accountId +
        ", unitId=" + unitId +
        ", unitName=" + unitName +
        ", valid=" + valid +
        "}";
    }
}
