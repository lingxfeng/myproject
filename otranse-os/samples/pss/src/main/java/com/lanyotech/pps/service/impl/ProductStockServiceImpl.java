package com.lanyotech.pps.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.ProductStock;
import com.lanyotech.pps.service.IProductStockService;
import com.lanyotech.pps.dao.IProductStockDAO;


/**
 * ProductStockServiceImpl
 * @author Disco Framework
 */
public class ProductStockServiceImpl implements IProductStockService{
	
	private IProductStockDAO productStockDao;
	
	public void setProductStockDao(IProductStockDAO productStockDao){
		this.productStockDao=productStockDao;
	}
	
	public Long addProductStock(ProductStock productStock) {	
		this.productStockDao.save(productStock);
		if (productStock != null && productStock.getId() != null) {
			return productStock.getId();
		}
		return null;
	}
	
	public ProductStock getProductStock(Long id) {
		ProductStock productStock = this.productStockDao.get(id);
		return productStock;
		}
	
	public boolean delProductStock(Long id) {	
			ProductStock productStock = this.getProductStock(id);
			if (productStock != null) {
				this.productStockDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelProductStocks(List<Serializable> productStockIds) {
		
		for (Serializable id : productStockIds) {
			delProductStock((Long) id);
		}
		return true;
	}
	
	public IPageList getProductStockBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, ProductStock.class,this.productStockDao);		
	}
	
	public boolean updateProductStock(Long id, ProductStock productStock) {
		if (id != null) {
			productStock.setId(id);
		} else {
			return false;
		}
		this.productStockDao.update(productStock);
		return true;
	}	
	
}
