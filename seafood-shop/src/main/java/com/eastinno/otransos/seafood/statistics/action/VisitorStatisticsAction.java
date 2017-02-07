package com.eastinno.otransos.seafood.statistics.action;

import com.eastinno.otransos.container.annonation.Action;
import com.eastinno.otransos.container.annonation.Inject;
import com.eastinno.otransos.core.support.query.QueryObject;
import com.eastinno.otransos.web.core.AbstractPageCmdAction;
import com.eastinno.otransos.core.util.CommUtil;
import com.eastinno.otransos.web.ajax.AjaxUtil;
import com.eastinno.otransos.web.Module;
import com.eastinno.otransos.web.Page;
import com.eastinno.otransos.web.WebForm;
import com.eastinno.otransos.web.tools.IPageList;
import com.eastinno.otransos.seafood.statistics.domain.VisitorStatistics;
import com.eastinno.otransos.seafood.statistics.service.IVisitorStatisticsService;

/**
 * VisitorStatisticsAction
 * @author 
 */
@Action
public class VisitorStatisticsAction extends AbstractPageCmdAction {
    @Inject
    private IVisitorStatisticsService service;
    /**
     * 列表页面
     * 
     * @param form
     */
    public Page doList(WebForm form) {
        QueryObject qo = (QueryObject) form.toPo(QueryObject.class);
        IPageList pageList = this.service.getVisitorStatisticsBy(qo);
		AjaxUtil.convertEntityToJson(pageList);
        form.jsonResult(pageList);
        return Page.JSONPage;
    }
    
    /**
     * 保存数据
     * 
     * @param form
     */
    public Page doSave(WebForm form) {
        VisitorStatistics entry = (VisitorStatistics)form.toPo(VisitorStatistics.class);
        form.toPo(entry);
        if (!hasErrors()) {
            Long id = this.service.addVisitorStatistics(entry);
            if (id != null) {
                form.addResult("msg", "添加成功");
            }
        }
        Page page = pageForExtForm(form);
        page.setContentType("html");
        return page;
    }
    /**
     * 修改数据
     * 
     * @param form
     */
    public Page doUpdate(WebForm form) {
        Long id = Long.valueOf(Long.parseLong(CommUtil.null2String(form.get("id"))));
        VisitorStatistics entry = this.service.getVisitorStatistics(id);
        form.toPo(entry);
        if (!hasErrors()) {
            boolean ret = service.updateVisitorStatistics(id,entry);
            if(ret){
                form.addResult("msg", "修改成功");
            }
        }
        Page page = pageForExtForm(form);
        page.setContentType("html");
        return page;
    }
    
    /**
     * 删除数据
     * 
     * @param form
     */
    public Page doRemove(WebForm form) {
        Long id = new Long(CommUtil.null2String(form.get("id")));
        this.service.delVisitorStatistics(id);
        Page page = pageForExtForm(form);
        page.setContentType("html");
        return page;
    }
    
    public void setService(IVisitorStatisticsService service) {
        this.service = service;
    }
}