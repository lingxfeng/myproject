package com.lanyotech.pps.mvc;

import com.lanyotech.pps.domain.SystemDictionary;
import com.lanyotech.pps.service.ISystemDictionaryService;

import cn.disco.container.annonation.Action;
import cn.disco.container.annonation.Inject;
import cn.disco.core.support.query.QueryObject;
import cn.disco.util.CommUtil;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;
import cn.disco.web.tools.IPageList;


/**
 * SystemDictionaryAction
 * @author Disco Framework
 */
@Action
public class SystemDictionaryAction extends AbstractPageCmdAction {
	
	@Inject
	private ISystemDictionaryService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(ISystemDictionaryService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getSystemDictionaryBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delSystemDictionary(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		SystemDictionary object = form.toPo(SystemDictionary.class);
		if (!hasErrors())
			service.addSystemDictionary(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		SystemDictionary object = service.getSystemDictionary(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateSystemDictionary(id, object);
		return pageForExtForm(form);
	}
}