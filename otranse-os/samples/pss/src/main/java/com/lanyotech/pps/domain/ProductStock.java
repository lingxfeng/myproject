package com.lanyotech.pps.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.disco.container.annonation.POLoad;

@Entity
@Table(name="productstock")
public class ProductStock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@POLoad
	private Product product;
	/**
	 * 实际库存数量,表示已审核后的数量
	 */
	private BigDecimal storeNum;

	/**
	 * 成本平均价格,或者实际价格,该价格需要根据某一种算法来得到
	 */
	private BigDecimal price;// 
	/**
	 * 库存金额
	 */
	private BigDecimal totalAamount;
	
	private Date incomeDate;// 最近入库时间

	private Date outcomeDate;// 出库时间
	
	@ManyToOne(fetch = FetchType.LAZY)
	@POLoad
	private Depot depot;// 仓库
	
	private Boolean warning;// 预警,自动计算

	private BigDecimal topNum;// 最高数

	private BigDecimal bottomNum;// 最低数

	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public BigDecimal getStoreNum() {
		return storeNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTotalAamount() {
		return totalAamount;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public Date getOutcomeDate() {
		return outcomeDate;
	}

	public Depot getDepot() {
		return depot;
	}

	public Boolean getWarning() {
		return warning;
	}

	public BigDecimal getTopNum() {
		return topNum;
	}

	public BigDecimal getBottomNum() {
		return bottomNum;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setStoreNum(BigDecimal storeNum) {
		this.storeNum = storeNum;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setTotalAamount(BigDecimal totalAamount) {
		this.totalAamount = totalAamount;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public void setWarning(Boolean warning) {
		this.warning = warning;
	}

	public void setTopNum(BigDecimal topNum) {
		this.topNum = topNum;
	}

	public void setBottomNum(BigDecimal bottomNum) {
		this.bottomNum = bottomNum;
	}

	
}
