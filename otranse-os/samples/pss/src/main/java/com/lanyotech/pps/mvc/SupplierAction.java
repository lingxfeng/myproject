package com.lanyotech.pps.mvc;

import com.lanyotech.pps.domain.Supplier;
import com.lanyotech.pps.service.ISupplierService;

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
 * SupplierAction
 * @author Disco Framework
 */
@Action
public class SupplierAction extends AbstractPageCmdAction {
	
	@Inject
	private ISupplierService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(ISupplierService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getSupplierBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delSupplier(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		Supplier object = form.toPo(Supplier.class);
		if (!hasErrors())
			service.addSupplier(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Supplier object = service.getSupplier(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateSupplier(id, object);
		return pageForExtForm(form);
	}
}