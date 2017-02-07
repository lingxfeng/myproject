package com.lanyotech.pps.mvc;

import com.lanyotech.pps.domain.Client;
import com.lanyotech.pps.service.IClientService;

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
 * ClientAction
 * @author Disco Framework
 */
@Action
public class ClientAction extends AbstractPageCmdAction {
	
	@Inject
	private IClientService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IClientService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getClientBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delClient(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		Client object = form.toPo(Client.class);
		if (!hasErrors())
			service.addClient(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Client object = service.getClient(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateClient(id, object);
		return pageForExtForm(form);
	}
}