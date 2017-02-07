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
import com.lanyotech.pps.domain.Employee;
import com.lanyotech.pps.service.IEmployeeService;
import com.lanyotech.pps.service.LogicException;


/**
 * EmployeeAction
 * @author Disco Framework
 */
@Action
public class EmployeeAction extends AbstractPageCmdAction {
	
	@Inject
	private IEmployeeService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IEmployeeService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getEmployeeBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delEmployee(id);
		return pageForExtForm(form);
	}
	public Page doChangePassword(WebForm form) {
		String password=CommUtil.null2String(form.get("oldPassword"));
		String newPassword=CommUtil.null2String(form.get("password"));
		try{
		service.changePassword(password,newPassword);
		}
		catch(LogicException e){
			this.addError("oldPassword", e.getMessage());
			this.addError("msg", e.getMessage());
		}
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		Employee object = form.toPo(Employee.class);
		if (!hasErrors())
			service.addEmployee(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Employee object = service.getEmployee(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateEmployee(id, object);
		return pageForExtForm(form);
	}
}