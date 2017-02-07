package com.lanyotech.pps.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.Supplier;
import com.lanyotech.pps.service.ISupplierService;
import com.lanyotech.pps.dao.ISupplierDAO;


/**
 * SupplierServiceImpl
 * @author Disco Framework
 */
public class SupplierServiceImpl implements ISupplierService{
	
	private ISupplierDAO supplierDao;
	
	public void setSupplierDao(ISupplierDAO supplierDao){
		this.supplierDao=supplierDao;
	}
	
	public Long addSupplier(Supplier supplier) {	
		this.supplierDao.save(supplier);
		if (supplier != null && supplier.getId() != null) {
			return supplier.getId();
		}
		return null;
	}
	
	public Supplier getSupplier(Long id) {
		Supplier supplier = this.supplierDao.get(id);
		return supplier;
		}
	
	public boolean delSupplier(Long id) {	
			Supplier supplier = this.getSupplier(id);
			if (supplier != null) {
				this.supplierDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelSuppliers(List<Serializable> supplierIds) {
		
		for (Serializable id : supplierIds) {
			delSupplier((Long) id);
		}
		return true;
	}
	
	public IPageList getSupplierBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, Supplier.class,this.supplierDao);		
	}
	
	public boolean updateSupplier(Long id, Supplier supplier) {
		if (id != null) {
			supplier.setId(id);
		} else {
			return false;
		}
		this.supplierDao.update(supplier);
		return true;
	}	
	
}
