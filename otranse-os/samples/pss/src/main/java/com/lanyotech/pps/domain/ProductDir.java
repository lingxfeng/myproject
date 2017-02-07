package com.lanyotech.pps.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.disco.container.annonation.POLoad;

@Entity
@Table(name = "productdir")
public class ProductDir {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private String sn;
	private String name;
	private String intro;
	private String dirPath;
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private ProductDir parent;
	@OneToMany(mappedBy = "parent")
	private List<ProductDir> children = new java.util.ArrayList<ProductDir>();
	private Integer sequence;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getIntro() {
		return intro;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public ProductDir getParent() {
		return parent;
	}
	public List<ProductDir> getChildren() {
		return children;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setParent(ProductDir parent) {
		if(parent==null || !parent.getId().equals(this.id))
		this.parent = parent;
	}
	public void setChildren(List<ProductDir> children) {
		this.children = children;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDirPath() {
		return dirPath;
	}
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
}
