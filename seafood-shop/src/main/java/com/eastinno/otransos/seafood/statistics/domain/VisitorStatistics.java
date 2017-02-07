package com.eastinno.otransos.seafood.statistics.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

/** 
*@author dll 作者 E-mail：dongliangliang@teleinfo.cn 
*@date 创建时间：2017年1月24日 下午12:38:51
*@version 1.0
*@parameter
*@since
*@return 
*/
@Entity(name="disco_shop_visitorstatistics")
public class VisitorStatistics {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;//id标识
	private List<String> ips = new ArrayList<>();//当天的所有访问Ip
	private List<String> memberId = new ArrayList<>();//当天的所有访问会员id
	private String currentDay;//统计的日期
	private int visitorCountIp=0;//独立IP访问次数
	private int visitorCountPV=0;//pv访问次数
	private int visitorCountMember=0;//独立会员访问次数
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getIps() {
		return ips;
	}
	public void setIps(List<String> ips) {
		this.ips = ips;
	}
	public List<String> getMemberId() {
		return memberId;
	}
	public void setMemberId(List<String> memberId) {
		this.memberId = memberId;
	}
	public String getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}
	public int getVisitorCountIp() {
		return visitorCountIp;
	}
	public void setVisitorCountIp(int visitorCountIp) {
		this.visitorCountIp = visitorCountIp;
	}
	public int getVisitorCountPV() {
		return visitorCountPV;
	}
	public void setVisitorCountPV(int visitorCountPV) {
		this.visitorCountPV = visitorCountPV;
	}
	public int getVisitorCountMember() {
		return visitorCountMember;
	}
	public void setVisitorCountMember(int visitorCountMember) {
		this.visitorCountMember = visitorCountMember;
	}
}
