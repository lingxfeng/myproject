package com.lanyotech.pps.mvc;

import java.util.Date;
import java.util.List;

import cn.disco.container.annonation.Action;
import cn.disco.container.annonation.Inject;
import cn.disco.core.support.ActionUtil;
import cn.disco.core.support.query.QueryObject;
import cn.disco.util.CommUtil;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.StockIncome;
import com.lanyotech.pps.domain.StockIncomeItem;
import com.lanyotech.pps.service.IStockDetailAccountService;
import com.lanyotech.pps.service.IStockIncomeService;
import com.lanyotech.pps.service.UserContext;


/**
 * StockIncomeAction
 * @author Disco Framework
 */
@Action
public class StockIncomeAction extends AbstractPageCmdAction {
	
	@Inject
	private IStockIncomeService service;
	@Inject
	private IStockDetailAccountService accountService;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IStockIncomeService service) {
		this.service = service;
	}
	
	public void setAccountService(IStockDetailAccountService accountService) {
		this.accountService = accountService;
	}

	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		String types=CommUtil.null2String(form.get("types"));
		if(!"".equals(types)){
			qo.addQuery("obj.types",new Integer(types),"=");
		}
		IPageList pageList = service.getStockIncomeBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delStockIncome(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		StockIncome object = form.toPo(StockIncome.class);
		handleItems(form,object);
		if (!hasErrors())
			service.addStockIncome(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockIncome object = service.getStockIncome(id);
		form.toPo(object, true);
		handleItems(form,object);
		if (!hasErrors())
			service.updateStockIncome(id, object);
		return pageForExtForm(form);
	}
	public Page doView(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockIncome object = service.getStockIncome(id);
		form.jsonResult(object.toJSonObjectWithItems());
		return Page.JSONPage;
	}
	protected void handleItems(WebForm form, StockIncome object) {
		List list = ActionUtil.parseMulitItems(form, StockIncomeItem.class, new String[] { "id", "product", "price", "num", "amount", "remark" },
				"item_");
		List<Long> deletes=object.updateItems(list);
		if(!deletes.isEmpty()){
			for(Long id:deletes){
			this.service.delStockIncomeItem(id);
			}
		}
	}
	public Page doAudit(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockIncome object = service.getStockIncome(id);
		if(object.getAuditing()==null || !object.getAuditing()){
			accountService.createStockDetailAccount(object);
			object.setAuditing(true);
			object.setAuditTime(new Date());
			object.setAuditor(UserContext.getUser());
			this.service.updateStockIncome(object.getId(), object);
		}
		else {
			this.addError("msg", "该单据已经审核，无法重复审核!");
		}
		return this.pageForExtForm(form);
	}
}