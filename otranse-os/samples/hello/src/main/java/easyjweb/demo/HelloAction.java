package easyjweb.demo;

import java.util.Date;

import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;

public class HelloAction extends AbstractPageCmdAction {
	public void index(WebForm form) {
		form.addResult("msg", "您好，这是Disco的第一个程序!");		
		form.addResult("date", new Date());		
	}
}
