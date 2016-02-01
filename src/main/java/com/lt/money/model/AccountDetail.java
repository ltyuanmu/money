package com.lt.money.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountDetail implements Serializable {
    private String id;

    private String fkAccountId;

    private Integer type;

    private BigDecimal money;

    private Date accountTime;

    private String fkUserId;

    private Date updateTime;

    private Date createTime;

    private String accountDesc;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFkAccountId() {
        return fkAccountId;
    }

    public void setFkAccountId(String fkAccountId) {
        this.fkAccountId = fkAccountId == null ? null : fkAccountId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId == null ? null : fkUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc == null ? null : accountDesc.trim();
    }
}