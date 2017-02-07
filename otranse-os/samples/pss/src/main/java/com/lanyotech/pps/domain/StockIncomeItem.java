package com.lanyotech.pps.domain;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.disco.container.annonation.POLoad;
import cn.disco.util.CommUtil;
import cn.disco.web.ajax.IJsonObject;

@Entity
@Table(name = "stockincomeitem")
public class StockIncomeItem implements IJsonObject{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	private StockIncome bill;
	@ManyToOne(fetch=FetchType.LAZY)
	@POLoad
	private Product product;
	private BigDecimal price;
	private BigDecimal num;
	private BigDecimal amount;
	private String remark;
	
	public Object toJSonObject() {
		Map map=CommUtil.obj2mapExcept(this, new String[]{"bill","product"});
		if(product!=null){
			map.put("product", product.toJSonObject());
		}
		return map;
	}
	public Long getId() {
		return id;
	}
	public StockIncome getBill() {
		return bill;
	}
	public Product getProduct() {
		return product;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public BigDecimal getNum() {
		return num;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBill(StockIncome bill) {
		this.bill = bill;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
