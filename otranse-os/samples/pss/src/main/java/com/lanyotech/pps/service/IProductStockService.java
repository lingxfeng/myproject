package com.lanyotech.pps.service;

import java.io.Serializable;
import java.util.List;
import cn.disco.web.tools.IPageList;
import cn.disco.core.support.query.IQueryObject;
import com.lanyotech.pps.domain.ProductStock;
/**
 * ProductStockService
 * @author Disco Framework
 */
public interface IProductStockService {
	/**
	 * 保存一个ProductStock，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addProductStock(ProductStock instance);
	
	/**
	 * 根据一个ID得到ProductStock
	 * 
	 * @param id
	 * @return
	 */
	ProductStock getProductStock(Long id);
	
	/**
	 * 删除一个ProductStock
	 * @param id
	 * @return
	 */
	boolean delProductStock(Long id);
	
	/**
	 * 批量删除ProductStock
	 * @param ids
	 * @return
	 */
	boolean batchDelProductStocks(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到ProductStock
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getProductStockBy(IQueryObject queryObject);
	
	/**
	  * 更新一个ProductStock
	  * @param id 需要更新的ProductStock的id
	  * @param dir 需要更新的ProductStock
	  */
	boolean updateProductStock(Long id,ProductStock instance);
}
