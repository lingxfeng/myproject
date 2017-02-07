package com.lanyotech.pps.mvc;

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
import cn.disco.web.tools.ListQuery;
import cn.disco.web.tools.PageList;
import com.lanyotech.pps.domain.PurchaseBill;
import com.lanyotech.pps.domain.PurchaseBillItem;
import com.lanyotech.pps.query.PurchaseItemQuery;
import com.lanyotech.pps.service.IPurchaseBillService;


/**
 * PurchaseBillAction
 * @author Disco Framework
 */
@Action
public class PurchaseBillAction extends AbstractPageCmdAction {
	@Inject
	private IPurchaseBillService service;
	
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IPurchaseBillService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getPurchaseBillBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delPurchaseBill(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		PurchaseBill object = form.toPo(PurchaseBill.class);
		handleItems(form,object);
		if (!hasErrors())
			service.addPurchaseBill(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		PurchaseBill object = service.getPurchaseBill(id);
		form.toPo(object, true);
		handleItems(form,object);
		if (!hasErrors())
			service.updatePurchaseBill(id, object);
		return pageForExtForm(form);
	}
	public Page doView(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		PurchaseBill object = service.getPurchaseBill(id);
		form.jsonResult(object.toJSonObjectWithItems());
		return Page.JSONPage;
	}
	protected void handleItems(WebForm form, PurchaseBill object) {
		List list = ActionUtil.parseMulitItems(form, PurchaseBillItem.class, new String[] { "id", "product", "price", "num", "amount", "remark" },
				"item_");
		List<Long> deletes=object.updateItems(list);
		if(!deletes.isEmpty()){
			for(Long id:deletes){
			this.service.delPurchaseBillItem(id);
			}
		}
	}
	public Page doStatistics(WebForm form) {
		PurchaseItemQuery qo=form.toPo(PurchaseItemQuery.class);
		List list=service.statistics(qo, qo.getGroupBy());
		IPageList pageList=new PageList(new ListQuery(list));
		pageList.doList(-1, 1, "", "");
		form.jsonResult(pageList);
		return Page.JSONPage;
	}
}