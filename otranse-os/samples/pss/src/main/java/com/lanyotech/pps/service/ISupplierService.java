package com.lanyotech.pps.service;

import java.io.Serializable;
import java.util.List;
import cn.disco.web.tools.IPageList;
import cn.disco.core.support.query.IQueryObject;
import com.lanyotech.pps.domain.Supplier;
/**
 * SupplierService
 * @author Disco Framework
 */
public interface ISupplierService {
	/**
	 * 保存一个Supplier，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addSupplier(Supplier instance);
	
	/**
	 * 根据一个ID得到Supplier
	 * 
	 * @param id
	 * @return
	 */
	Supplier getSupplier(Long id);
	
	/**
	 * 删除一个Supplier
	 * @param id
	 * @return
	 */
	boolean delSupplier(Long id);
	
	/**
	 * 批量删除Supplier
	 * @param ids
	 * @return
	 */
	boolean batchDelSuppliers(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Supplier
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getSupplierBy(IQueryObject queryObject);
	
	/**
	  * 更新一个Supplier
	  * @param id 需要更新的Supplier的id
	  * @param dir 需要更新的Supplier
	  */
	boolean updateSupplier(Long id,Supplier instance);
}
