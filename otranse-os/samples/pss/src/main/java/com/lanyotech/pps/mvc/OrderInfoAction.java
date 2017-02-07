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
import com.lanyotech.pps.domain.OrderInfo;
import com.lanyotech.pps.domain.OrderInfoItem;
import com.lanyotech.pps.query.OrderInfoItemQuery;
import com.lanyotech.pps.service.IOrderInfoService;


/**
 * OrderInfoAction
 * @author Disco Framework
 */
@Action
public class OrderInfoAction extends AbstractPageCmdAction {
	
	@Inject
	private IOrderInfoService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IOrderInfoService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getOrderInfoBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delOrderInfo(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		OrderInfo object = form.toPo(OrderInfo.class);
		this.handleItems(form, object);
		if (!hasErrors())
			service.addOrderInfo(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		OrderInfo object = service.getOrderInfo(id);
		form.toPo(object, true);
		this.handleItems(form, object);
		if (!hasErrors())
			service.updateOrderInfo(id, object);
		return pageForExtForm(form);
	}
	public Page doView(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		OrderInfo object = service.getOrderInfo(id);
		form.jsonResult(object.toJSonObjectWithItems());
		return Page.JSONPage;
	}
	protected void handleItems(WebForm form, OrderInfo object) {
		List list = ActionUtil.parseMulitItems(form, OrderInfoItem.class, new String[] { "id", "product", "price", "num", "amount", "remark" },
				"item_");
		List<Long> deletes=object.updateItems(list);
		if(!deletes.isEmpty()){
			for(Long id:deletes){
			this.service.delOrderInfoItem(id);
			}
		}
	}
	public Page doStatistics(WebForm form) {
		OrderInfoItemQuery qo=form.toPo(OrderInfoItemQuery.class);
		List list=service.statistics(qo, qo.getGroupBy());
		IPageList pageList=new PageList(new ListQuery(list));
		pageList.doList(-1, 1, "", "");
		form.jsonResult(pageList);
		return Page.JSONPage;
	}
}