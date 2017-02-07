package com.lanyotech.pps.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.ProductDir;
import com.lanyotech.pps.service.IProductDirService;
import com.lanyotech.pps.dao.IProductDirDAO;


/**
 * ProductDirServiceImpl
 * @author Disco Framework
 */
public class ProductDirServiceImpl implements IProductDirService{
	
	private IProductDirDAO productDirDao;
	
	public void setProductDirDao(IProductDirDAO productDirDao){
		this.productDirDao=productDirDao;
	}
	
	public Long addProductDir(ProductDir productDir) {	
		this.productDirDao.save(productDir);
		if (productDir != null && productDir.getId() != null) {
			return productDir.getId();
		}
		return null;
	}
	
	public ProductDir getProductDir(Long id) {
		ProductDir productDir = this.productDirDao.get(id);
		return productDir;
		}
	
	public boolean delProductDir(Long id) {	
			ProductDir productDir = this.getProductDir(id);
			if (productDir != null) {
				this.productDirDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelProductDirs(List<Serializable> productDirIds) {
		
		for (Serializable id : productDirIds) {
			delProductDir((Long) id);
		}
		return true;
	}
	
	public IPageList getProductDirBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, ProductDir.class,this.productDirDao);		
	}
	
	public boolean updateProductDir(Long id, ProductDir productDir) {
		if (id != null) {
			productDir.setId(id);
		} else {
			return false;
		}
		this.productDirDao.update(productDir);
		return true;
	}	
	
}
