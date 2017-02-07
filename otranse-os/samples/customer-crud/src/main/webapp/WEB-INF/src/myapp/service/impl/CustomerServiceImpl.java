package myapp.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import myapp.domain.Customer;
import myapp.service.ICustomerService;
import myapp.dao.ICustomerDAO;


/**
 * CustomerServiceImpl
 * @author Disco Framework
 */
public class CustomerServiceImpl implements ICustomerService{
	
	private ICustomerDAO customerDao;
	
	public void setCustomerDao(ICustomerDAO customerDao){
		this.customerDao=customerDao;
	}
	
	public Long addCustomer(Customer customer) {	
		this.customerDao.save(customer);
		if (customer != null && customer.getId() != null) {
			return customer.getId();
		}
		return null;
	}
	
	public Customer getCustomer(Long id) {
		Customer customer = this.customerDao.get(id);
		return customer;
		}
	
	public boolean delCustomer(Long id) {	
			Customer customer = this.getCustomer(id);
			if (customer != null) {
				this.customerDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelCustomers(List<Serializable> customerIds) {
		
		for (Serializable id : customerIds) {
			delCustomer((Long) id);
		}
		return true;
	}
	
	public IPageList getCustomerBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, Customer.class,this.customerDao);		
	}
	
	public boolean updateCustomer(Long id, Customer customer) {
		if (id != null) {
			customer.setId(id);
		} else {
			return false;
		}
		this.customerDao.update(customer);
		return true;
	}	
	
}
