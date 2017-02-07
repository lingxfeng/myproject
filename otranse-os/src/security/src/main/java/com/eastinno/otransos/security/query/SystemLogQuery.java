package com.eastinno.otransos.security.query;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.eastinno.otransos.container.annonation.POLoad;
import com.eastinno.otransos.core.support.query.QueryObject;
import com.eastinno.otransos.security.domain.User;

public class SystemLogQuery extends QueryObject {

    @POLoad
    private User user;
    private Date vdate1;
    private Date vdate2;
    private String ip = "";
    private String action = "";
    private String pack = "";
    private String method = "";
    private Integer types;

    public void customizeQuery() {
        if (this.user != null) {
            addQuery("obj.user", this.user, "=");
        }
        if (this.vdate1 != null) {
            addQuery("obj.vdate1", this.vdate1, ">=");
        }
        if (this.vdate2 != null) {
            addQuery("obj.vdate2", this.vdate2, "<");
        }
        if (!"".equals(this.ip)) {
            addQuery("obj.ip", this.ip + "5", "like");
        }
        if (!"".equals(this.pack)) {
            addQuery("obj.action", this.action + "%", "like");
        }
        if (!"".equals(this.action)) {
            addQuery("obj.action", this.action + "%", "like");
        }
        if (!"".equals(this.method)) {
            addQuery("obj.cmd", this.method, "=");
        }
        if (this.types != null) {
            addQuery("obj.types", this.types, "=");
        }
        if (!StringUtils.hasText(getOrderBy())) {
            setOrderBy("vdate");
            setOrderType("desc");
        }
        super.customizeQuery();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVdate1(Date vdate1) {
        this.vdate1 = vdate1;
    }

    public void setVdate2(Date vdate2) {
        this.vdate2 = vdate2;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
}
