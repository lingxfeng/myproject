package com.lanyotech.pps.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.disco.container.annonation.POLoad;

@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	/**
	 * 客户编号
	 */
	@Column(unique = true)
	private String sn;
	private String name;
	private String shortName;
	private String zip;
	private String fax;
	private String tel;
	private String address;
	private String linkMan;
	private String email;
	private String homePage;
	/**
	 * 客户类型
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private SystemDictionaryDetail types;
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private SystemDictionaryDetail source;
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private SystemDictionaryDetail trade;
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private Employee seller;
	private Date inputTime=new Date();
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private Employee inputUser;
	private String intro;
	/**
	 * 0为普通,1为热点,2为签约,-1为丢单
	 */
	private Integer status;
	
	public Long getId() {
		return id;
	}
	public String getSn() {
		return sn;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public String getAddress() {
		return address;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public String getEmail() {
		return email;
	}
	public String getHomePage() {
		return homePage;
	}
	
	public String getIntro() {
		return intro;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public SystemDictionaryDetail getTypes() {
		return types;
	}
	public SystemDictionaryDetail getSource() {
		return source;
	}
	public void setTypes(SystemDictionaryDetail types) {
		this.types = types;
	}
	public void setSource(SystemDictionaryDetail source) {
		this.source = source;
	}
	public String getShortName() {
		return shortName;
	}
	public String getZip() {
		return zip;
	}
	public String getFax() {
		return fax;
	}
	public SystemDictionaryDetail getTrade() {
		return trade;
	}
	public Employee getSeller() {
		return seller;
	}
	public Integer getStatus() {
		return status;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setTrade(SystemDictionaryDetail trade) {
		this.trade = trade;
	}
	public void setSeller(Employee seller) {
		this.seller = seller;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public Employee getInputUser() {
		return inputUser;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}	
}
