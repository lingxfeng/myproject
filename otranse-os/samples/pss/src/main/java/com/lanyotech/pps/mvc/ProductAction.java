package com.lanyotech.pps.mvc;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.springframework.util.StringUtils;

import cn.disco.container.annonation.Action;
import cn.disco.container.annonation.Inject;
import cn.disco.core.support.query.QueryObject;
import cn.disco.util.CommUtil;
import cn.disco.web.Globals;
import cn.disco.web.Module;
import cn.disco.web.Page;
import cn.disco.web.WebForm;
import cn.disco.web.core.AbstractPageCmdAction;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.Product;
import com.lanyotech.pps.service.IProductService;


/**
 * ProductAction
 * @author Disco Framework
 */
@Action
public class ProductAction extends AbstractPageCmdAction {
	
	@Inject
	private IProductService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IProductService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		String searckKey=CommUtil.null2String(form.get("searchKey"));
		if(!"".equals(searckKey)){
			qo.addQuery("(obj.sn like ? or obj.name like ?)",new Object[]{"%"+searckKey+"%","%"+searckKey+"%"});
		}
		IPageList pageList = service.getProductBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delProduct(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		Product object = form.toPo(Product.class);
		this.parsePic(form, object);
		if (!hasErrors()){
			service.addProduct(object);
		}
		Page page= pageForExtForm(form);
		page.setContentType("html");
		return page;
	}
	private void parsePic(WebForm form,Product object){
		FileItem file=(FileItem)form.getFileElement().get("picFile");
		if(file!=null&&StringUtils.hasLength(file.getName())){
			String name="/upload/"+file.getName();
			File f=new File(Globals.APP_BASE_DIR+name);
			if(!f.getParentFile().exists())f.getParentFile().mkdirs();
			try{
			file.write(f);
			object.setPic(name);
			}
			catch(Exception e){
				System.out.println("上传文件处理出错:"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Product object = service.getProduct(id);
		form.toPo(object, true);
		this.parsePic(form, object);
		if (!hasErrors())
			service.updateProduct(id, object);
		System.out.println("success");
		Page page= pageForExtForm(form);
		page.setContentType("html");
		return page;
	}
}