package com.lanyotech.pps.service;

import java.io.Serializable;
import java.util.List;
import cn.disco.web.tools.IPageList;
import cn.disco.core.support.query.IQueryObject;
import com.lanyotech.pps.domain.Product;
/**
 * ProductService
 * @author Disco Framework
 */
public interface IProductService {
	/**
	 * 保存一个Product，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addProduct(Product instance);
	
	/**
	 * 根据一个ID得到Product
	 * 
	 * @param id
	 * @return
	 */
	Product getProduct(Long id);
	
	/**
	 * 删除一个Product
	 * @param id
	 * @return
	 */
	boolean delProduct(Long id);
	
	/**
	 * 批量删除Product
	 * @param ids
	 * @return
	 */
	boolean batchDelProducts(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Product
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getProductBy(IQueryObject queryObject);
	
	/**
	  * 更新一个Product
	  * @param id 需要更新的Product的id
	  * @param dir 需要更新的Product
	  */
	boolean updateProduct(Long id,Product instance);
}
