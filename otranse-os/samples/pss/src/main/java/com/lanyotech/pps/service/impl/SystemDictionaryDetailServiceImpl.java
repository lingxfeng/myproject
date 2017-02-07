package com.lanyotech.pps.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.SystemDictionaryDetail;
import com.lanyotech.pps.service.ISystemDictionaryDetailService;
import com.lanyotech.pps.dao.ISystemDictionaryDetailDAO;


/**
 * SystemDictionaryDetailServiceImpl
 * @author Disco Framework
 */
public class SystemDictionaryDetailServiceImpl implements ISystemDictionaryDetailService{
	
	private ISystemDictionaryDetailDAO systemDictionaryDetailDao;
	
	public void setSystemDictionaryDetailDao(ISystemDictionaryDetailDAO systemDictionaryDetailDao){
		this.systemDictionaryDetailDao=systemDictionaryDetailDao;
	}
	
	public Long addSystemDictionaryDetail(SystemDictionaryDetail systemDictionaryDetail) {	
		this.systemDictionaryDetailDao.save(systemDictionaryDetail);
		if (systemDictionaryDetail != null && systemDictionaryDetail.getId() != null) {
			return systemDictionaryDetail.getId();
		}
		return null;
	}
	
	public SystemDictionaryDetail getSystemDictionaryDetail(Long id) {
		SystemDictionaryDetail systemDictionaryDetail = this.systemDictionaryDetailDao.get(id);
		return systemDictionaryDetail;
		}
	
	public boolean delSystemDictionaryDetail(Long id) {	
			SystemDictionaryDetail systemDictionaryDetail = this.getSystemDictionaryDetail(id);
			if (systemDictionaryDetail != null) {
				this.systemDictionaryDetailDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelSystemDictionaryDetails(List<Serializable> systemDictionaryDetailIds) {
		
		for (Serializable id : systemDictionaryDetailIds) {
			delSystemDictionaryDetail((Long) id);
		}
		return true;
	}
	
	public IPageList getSystemDictionaryDetailBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, SystemDictionaryDetail.class,this.systemDictionaryDetailDao);		
	}
	
	public boolean updateSystemDictionaryDetail(Long id, SystemDictionaryDetail systemDictionaryDetail) {
		if (id != null) {
			systemDictionaryDetail.setId(id);
		} else {
			return false;
		}
		this.systemDictionaryDetailDao.update(systemDictionaryDetail);
		return true;
	}	
	
}
