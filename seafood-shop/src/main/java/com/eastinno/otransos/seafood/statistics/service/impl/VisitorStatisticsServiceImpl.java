package com.eastinno.otransos.seafood.statistics.service.impl;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastinno.otransos.core.support.query.IQueryObject;
import com.eastinno.otransos.web.tools.IPageList;
import com.eastinno.otransos.seafood.statistics.domain.VisitorStatistics;
import com.eastinno.otransos.seafood.statistics.service.IVisitorStatisticsService;
import com.eastinno.otransos.seafood.statistics.dao.IVisitorStatisticsDAO;


/**
 * VisitorStatisticsServiceImpl
 * @author ksmwly@gmail.com
 */
@Service
public class VisitorStatisticsServiceImpl implements IVisitorStatisticsService{
	@Resource
	private IVisitorStatisticsDAO visitorStatisticsDao;
	
	public void setVisitorStatisticsDao(IVisitorStatisticsDAO visitorStatisticsDao){
		this.visitorStatisticsDao=visitorStatisticsDao;
	}
	
	public Long addVisitorStatistics(VisitorStatistics visitorStatistics) {	
		this.visitorStatisticsDao.save(visitorStatistics);
		if (visitorStatistics != null && visitorStatistics.getId() != null) {
			return visitorStatistics.getId();
		}
		return null;
	}
	
	public VisitorStatistics getVisitorStatistics(Long id) {
		VisitorStatistics visitorStatistics = this.visitorStatisticsDao.get(id);
		return visitorStatistics;
		}
	
	public boolean delVisitorStatistics(Long id) {	
			VisitorStatistics visitorStatistics = this.getVisitorStatistics(id);
			if (visitorStatistics != null) {
				this.visitorStatisticsDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelVisitorStatisticss(List<Serializable> visitorStatisticsIds) {
		
		for (Serializable id : visitorStatisticsIds) {
			delVisitorStatistics((Long) id);
		}
		return true;
	}
	
	public IPageList getVisitorStatisticsBy(IQueryObject queryObj) {	
		return this.visitorStatisticsDao.findBy(queryObj);		
	}
	
	public boolean updateVisitorStatistics(Long id, VisitorStatistics visitorStatistics) {
		if (id != null) {
			visitorStatistics.setId(id);
		} else {
			return false;
		}
		this.visitorStatisticsDao.update(visitorStatistics);
		return true;
	}	
	
}
