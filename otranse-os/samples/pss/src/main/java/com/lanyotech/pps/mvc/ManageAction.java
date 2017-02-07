package com.lanyotech.pps.mvc;

import cn.disco.web.IWebAction;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import com.lanyotech.pps.service.UserContext;

public class ManageAction implements IWebAction {
	public Page execute(WebForm form, Module module) throws Exception {
		//System.out.println(UserContext.getUser());
		if(UserContext.getUser()==null){
			return new Page("homePage","/login.html","html");
		}
		else {
			form.addResult("user", UserContext.getUser());
			return new Page("/index.html");
		}
	}
}
