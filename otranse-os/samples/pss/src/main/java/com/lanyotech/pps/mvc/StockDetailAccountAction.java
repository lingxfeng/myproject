package com.lanyotech.pps.mvc;

import java.util.List;

import cn.disco.container.annonation.Action;
import cn.disco.container.annonation.Inject;
import cn.disco.core.support.query.QueryObject;
import cn.disco.util.CommUtil;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;
import cn.disco.web.tools.IPageList;
import cn.disco.web.tools.ListQuery;
import cn.disco.web.tools.PageList;
import com.lanyotech.pps.domain.StockDetailAccount;
import com.lanyotech.pps.query.StockDetailAccountQuery;
import com.lanyotech.pps.service.IStockDetailAccountService;


/**
 * StockDetailAccountAction
 * @author Disco Framework
 */
@Action
public class StockDetailAccountAction extends AbstractPageCmdAction {
	
	@Inject
	private IStockDetailAccountService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IStockDetailAccountService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getStockDetailAccountBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delStockDetailAccount(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		StockDetailAccount object = form.toPo(StockDetailAccount.class);
		if (!hasErrors())
			service.addStockDetailAccount(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		StockDetailAccount object = service.getStockDetailAccount(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateStockDetailAccount(id, object);
		return pageForExtForm(form);
	}
	public Page doStatistics(WebForm form) {
		StockDetailAccountQuery qo=form.toPo(StockDetailAccountQuery.class);
		List list=service.statistics(qo);
		IPageList pageList=new PageList(new ListQuery(list));
		pageList.doList(-1, 1, "", "");
		form.jsonResult(pageList);
		return Page.JSONPage;
	}
	public Page doStatisticsTotal(WebForm form) {
		StockDetailAccountQuery qo=form.toPo(StockDetailAccountQuery.class);
		List list=service.statisticsTotal(qo);
		IPageList pageList=new PageList(new ListQuery(list));
		pageList.doList(-1, 1, "", "");
		form.jsonResult(pageList);
		return Page.JSONPage;
	}
}