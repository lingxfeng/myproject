package com.lanyotech.pps.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.Client;
import com.lanyotech.pps.service.IClientService;
import com.lanyotech.pps.dao.IClientDAO;


/**
 * ClientServiceImpl
 * @author Disco Framework
 */
public class ClientServiceImpl implements IClientService{
	
	private IClientDAO clientDao;
	
	public void setClientDao(IClientDAO clientDao){
		this.clientDao=clientDao;
	}
	
	public Long addClient(Client client) {	
		this.clientDao.save(client);
		if (client != null && client.getId() != null) {
			return client.getId();
		}
		return null;
	}
	
	public Client getClient(Long id) {
		Client client = this.clientDao.get(id);
		return client;
		}
	
	public boolean delClient(Long id) {	
			Client client = this.getClient(id);
			if (client != null) {
				this.clientDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelClients(List<Serializable> clientIds) {
		
		for (Serializable id : clientIds) {
			delClient((Long) id);
		}
		return true;
	}
	
	public IPageList getClientBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, Client.class,this.clientDao);		
	}
	
	public boolean updateClient(Long id, Client client) {
		if (id != null) {
			client.setId(id);
		} else {
			return false;
		}
		this.clientDao.update(client);
		return true;
	}	
	
}
