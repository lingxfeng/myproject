package com.lanyotech.pps.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
@Entity
@Table(name = "systemdictionary")
public class SystemDictionary {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.TABLE)
	private Long id;

	private String sn;

	private String title;

	private String intro;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@OrderBy(" sequence asc")
	private java.util.List<SystemDictionaryDetail> children = new java.util.ArrayList<SystemDictionaryDetail>();
	
	public Long getId() {
		return id;
	}
	public String getSn() {
		return sn;
	}
	public String getTitle() {
		return title;
	}
	public String getIntro() {
		return intro;
	}
	public java.util.List<SystemDictionaryDetail> getChildren() {
		return children;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setChildren(java.util.List<SystemDictionaryDetail> children) {
		this.children = children;
	}
}
