package easyjweb.demo;

import java.util.Date;

import cn.disco.web.IWebAction;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;

public class Hello1Action implements IWebAction{
	public Page execute(WebForm form, Module module) throws Exception {
		form.addResult("msg", "您好，这是Disco的第一个程序!");
		form.addResult("date", new Date());
		return new Page("/hello/index.html");
	}
}
