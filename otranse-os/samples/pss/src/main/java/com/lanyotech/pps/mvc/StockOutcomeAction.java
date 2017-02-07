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
import com.lanyotech.pps.domain.StockOutcome;
import com.lanyotech.pps.domain.StockOutcomeItem;
import com.lanyotech.pps.service.IStockDetailAccountService;
import com.lanyotech.pps.service.IStockOutcomeService;
import com.lanyotech.pps.service.UserContext;


/**
 * StockOutcomeAction
 * @author Disco Framework
 */
@Action
public class StockOutcomeAction extends AbstractPageCmdAction {
	@Inject
	private IStockOutcomeService service;
	@Inject
	private IStockDetailAccountService accountService;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IStockOutcomeService service) {
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
		IPageList pageList = service.getStockOutcomeBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delStockOutcome(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		StockOutcome object = form.toPo(StockOutcome.class);
		handleItems(form,object);
		if (!hasErrors())
			service.addStockOutcome(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockOutcome object = service.getStockOutcome(id);
		form.toPo(object, true);
		handleItems(form,object);
		if (!hasErrors())
			service.updateStockOutcome(id, object);
		return pageForExtForm(form);
	}
	public Page doView(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockOutcome object = service.getStockOutcome(id);
		form.jsonResult(object.toJSonObjectWithItems());
		return Page.JSONPage;
	}
	protected void handleItems(WebForm form, StockOutcome object) {
		List list = ActionUtil.parseMulitItems(form, StockOutcomeItem.class, new String[] { "id", "product", "price","salePrice", "num", "amount","saleAmount", "remark" },
				"item_");
		List<Long> deletes=object.updateItems(list);
		if(!deletes.isEmpty()){
			for(Long id:deletes){
			this.service.delStockOutcomeItem(id);
			}
		}
	}
	public Page doAudit(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockOutcome object = service.getStockOutcome(id);
		if(object.getAuditing()==null || !object.getAuditing()){
			accountService.createStockDetailAccount(object);
			object.setAuditing(true);
			object.setAuditTime(new Date());
			object.setAuditor(UserContext.getUser());
			this.service.updateStockOutcome(object.getId(), object);
		}
		else {
			this.addError("msg", "该单据已经审核，无法重复审核!");
		}
		return this.pageForExtForm(form);
	}
}