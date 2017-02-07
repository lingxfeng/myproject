package myapp.service;

import java.io.Serializable;
import java.util.List;
import cn.disco.web.tools.IPageList;
import cn.disco.core.support.query.IQueryObject;
import myapp.domain.Customer;
/**
 * CustomerService
 * @author Disco Framework
 */
public interface ICustomerService {
	/**
	 * 保存一个Customer，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addCustomer(Customer instance);
	
	/**
	 * 根据一个ID得到Customer
	 * 
	 * @param id
	 * @return
	 */
	Customer getCustomer(Long id);
	
	/**
	 * 删除一个Customer
	 * @param id
	 * @return
	 */
	boolean delCustomer(Long id);
	
	/**
	 * 批量删除Customer
	 * @param ids
	 * @return
	 */
	boolean batchDelCustomers(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Customer
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getCustomerBy(IQueryObject queryObject);
	
	/**
	  * 更新一个Customer
	  * @param id 需要更新的Customer的id
	  * @param dir 需要更新的Customer
	  */
	boolean updateCustomer(Long id,Customer instance);
}
