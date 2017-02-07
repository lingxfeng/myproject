package com.lanyotech.pps.service;

import java.io.Serializable;
import java.util.List;
import cn.disco.web.tools.IPageList;
import cn.disco.core.support.query.IQueryObject;
import com.lanyotech.pps.domain.SystemDictionary;
/**
 * SystemDictionaryService
 * @author Disco Framework
 */
public interface ISystemDictionaryService {
	/**
	 * 保存一个SystemDictionary，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addSystemDictionary(SystemDictionary instance);
	
	/**
	 * 根据一个ID得到SystemDictionary
	 * 
	 * @param id
	 * @return
	 */
	SystemDictionary getSystemDictionary(Long id);
	
	/**
	 * 删除一个SystemDictionary
	 * @param id
	 * @return
	 */
	boolean delSystemDictionary(Long id);
	
	/**
	 * 批量删除SystemDictionary
	 * @param ids
	 * @return
	 */
	boolean batchDelSystemDictionarys(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到SystemDictionary
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getSystemDictionaryBy(IQueryObject queryObject);
	
	/**
	  * 更新一个SystemDictionary
	  * @param id 需要更新的SystemDictionary的id
	  * @param dir 需要更新的SystemDictionary
	  */
	boolean updateSystemDictionary(Long id,SystemDictionary instance);
}
