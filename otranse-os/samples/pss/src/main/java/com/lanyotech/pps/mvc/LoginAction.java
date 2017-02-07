package com.lanyotech.pps.mvc;

import java.util.HashMap;
import java.util.Map;

import cn.disco.container.annonation.Action;
import cn.disco.util.CommUtil;
import cn.disco.web.ActionContext;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;
import com.lanyotech.pps.domain.Employee;
import com.lanyotech.pps.service.IEmployeeService;
import com.lanyotech.pps.service.LogicException;

@Action
public class LoginAction extends AbstractPageCmdAction {
	private IEmployeeService employeeService;

	public Page doLogin(WebForm form) {
		String name = CommUtil.null2String(form.get("name"));
		String password = CommUtil.null2String(form.get("password"));
		try {
			Employee emp = employeeService.login(name, password, ActionContext.getContext()
					.getRequest().getRemoteAddr());
		} catch (LogicException e) {
			this.addError("msg", "登录失败，失败原因"+e.getMessage());
		}
		return this.pageForExtForm(form);
	}
	
	public Page doLogout(WebForm form) {
		employeeService.logout();
		return Page.JSONPage;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
