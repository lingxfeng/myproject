package com.lanyotech.pps.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier extends Client {
	private BigDecimal assureAmount;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SupplierProduct> products=new java.util.ArrayList<SupplierProduct>();
	
	public List<SupplierProduct> getProducts() {
		return products;
	}
	
	public void setProducts(List<SupplierProduct> products) {
		this.products = products;
	}

	public BigDecimal getAssureAmount() {
		return assureAmount;
	}

	public void setAssureAmount(BigDecimal assureAmount) {
		this.assureAmount = assureAmount;
	}
	
}
