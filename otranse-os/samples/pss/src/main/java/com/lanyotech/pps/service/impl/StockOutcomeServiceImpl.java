package com.lanyotech.pps.service.impl;
import java.io.Serializable;
import java.util.List;

import cn.disco.core.support.query.IQueryObject;
import cn.disco.core.support.query.QueryUtil;
import cn.disco.web.tools.IPageList;
import com.lanyotech.pps.domain.StockOutcome;
import com.lanyotech.pps.service.IStockOutcomeService;
import com.lanyotech.pps.dao.IStockOutcomeDAO;


/**
 * StockOutcomeServiceImpl
 * @author Disco Framework
 */
public class StockOutcomeServiceImpl implements IStockOutcomeService{
	
	private IStockOutcomeDAO stockOutcomeDao;
	
	public void setStockOutcomeDao(IStockOutcomeDAO stockOutcomeDao){
		this.stockOutcomeDao=stockOutcomeDao;
	}
	
	public Long addStockOutcome(StockOutcome stockOutcome) {
		stockOutcome.countAmount();
		this.stockOutcomeDao.save(stockOutcome);
		if (stockOutcome != null && stockOutcome.getId() != null) {
			return stockOutcome.getId();
		}
		return null;
	}
	
	public StockOutcome getStockOutcome(Long id) {
		StockOutcome stockOutcome = this.stockOutcomeDao.get(id);
		return stockOutcome;
		}
	
	public boolean delStockOutcome(Long id) {	
			StockOutcome stockOutcome = this.getStockOutcome(id);
			if (stockOutcome != null) {
				this.stockOutcomeDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelStockOutcomes(List<Serializable> stockOutcomeIds) {
		
		for (Serializable id : stockOutcomeIds) {
			delStockOutcome((Long) id);
		}
		return true;
	}
	
	public IPageList getStockOutcomeBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, StockOutcome.class,this.stockOutcomeDao);		
	}
	
	public boolean updateStockOutcome(Long id, StockOutcome stockOutcome) {
		if (id != null) {
			stockOutcome.setId(id);
		} else {
			return false;
		}
		stockOutcome.countAmount();
		this.stockOutcomeDao.update(stockOutcome);
		return true;
	}

	public boolean delStockOutcomeItem(Long id) {
		int ret=this.stockOutcomeDao.batchUpdate("delete from StockOutcomeItem obj where obj.id=?", new Object[]{id});
		return ret>0;
	}	
	
}
