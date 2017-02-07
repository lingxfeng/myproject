package com.eastinno.otransos.security.mvc.ajax;

import java.io.Serializable;
import java.util.List;

import com.eastinno.otransos.container.annonation.Action;
import com.eastinno.otransos.core.support.ActionUtil;
import com.eastinno.otransos.core.support.query.QueryObject;
import com.eastinno.otransos.core.util.CommUtil;
import com.eastinno.otransos.security.query.SystemLogQuery;
import com.eastinno.otransos.security.service.ISystemLogService;
import com.eastinno.otransos.web.Module;
import com.eastinno.otransos.web.Page;
import com.eastinno.otransos.web.WebForm;
import com.eastinno.otransos.web.ajax.AjaxUtil;
import com.eastinno.otransos.web.core.AbstractPageCmdAction;
import com.eastinno.otransos.web.tools.IPageList;

@Action
public class SystemLogAction extends AbstractPageCmdAction {
    private ISystemLogService service;

    public void setService(ISystemLogService service) {
        this.service = service;
    }

    public Page doIndex(WebForm f, Module m) {
        return page("list");
    }

    public Page doList(WebForm form) {
        QueryObject query = (QueryObject) form.toPo(SystemLogQuery.class);
        IPageList pageList = this.service.getSystemLogBy(query);
        AjaxUtil.convertEntityToJson(pageList);
        form.jsonResult(pageList);
        return Page.JSONPage;
    }

    public Page doRemove(WebForm form) {
        Long id = new Long(CommUtil.null2String(form.get("id")));
        List<Serializable> list = ActionUtil.processIds(form);
        if ((list != null) && (!list.isEmpty())) {
            for (Serializable i : list) {
                this.service.delSystemLog(id);
            }
        } else
            this.service.delSystemLog(id);
        return pageForExtForm(form);
    }

    public Page doExport(WebForm form) throws Exception {
        QueryObject qo = (QueryObject) form.toPo(SystemLogQuery.class);
        qo.setPageSize(Integer.valueOf(-1));
        this.service.exportSystemLog(qo);
        return Page.nullPage;
    }
}
