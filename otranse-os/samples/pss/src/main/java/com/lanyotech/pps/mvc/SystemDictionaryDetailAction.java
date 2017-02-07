package com.lanyotech.pps.mvc;

import cn.disco.container.annonation.Action;
import cn.disco.container.annonation.Inject;
import cn.disco.core.support.query.QueryObject;
import cn.disco.util.CommUtil;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.SystemDictionaryDetail;
import com.lanyotech.pps.service.ISystemDictionaryDetailService;


/**
 * SystemDictionaryDetailAction
 * @author Disco Framework
 */
@Action
public class SystemDictionaryDetailAction extends AbstractPageCmdAction {
	
	@Inject
	private ISystemDictionaryDetailService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(ISystemDictionaryDetailService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		String parent=CommUtil.null2String(form.get("parent"));
		String parentSn=CommUtil.null2String(form.get("parentSn"));
		if(!"".equals(parent)){
			qo.addQuery("obj.parent.id",new Long(parent),"=");
		}
		if(!"".equals(parentSn)){
			qo.addQuery("obj.parent.sn",parentSn,"=");
		}
		IPageList pageList = service.getSystemDictionaryDetailBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delSystemDictionaryDetail(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		SystemDictionaryDetail object = form.toPo(SystemDictionaryDetail.class);
		if (!hasErrors())
			service.addSystemDictionaryDetail(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		SystemDictionaryDetail object = service.getSystemDictionaryDetail(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateSystemDictionaryDetail(id, object);
		return pageForExtForm(form);
	}
}