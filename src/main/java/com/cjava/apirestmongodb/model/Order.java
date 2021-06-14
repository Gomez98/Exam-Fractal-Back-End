package com.cjava.apirestmongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Document(collection = "order")
public class Order {
	@Id
	private @NonNull Integer orderNumber;
	private @NonNull String status;
	private Date date;
	private @NonNull String customer;
	private Double totalAmount;
	private Double totalTaxes;
	private @NonNull List<Item> orderItems;

	public Order() {

	}

	public Double getSubTotal() {
		Double tot = 0d;
		for (int i = 0; i < orderItems.size(); i++) {
			Item item = (Item) orderItems.get(i);
			tot += item.getAmount();
		}

		return tot;
	}

	public Double getCityTax() {
		return this.getSubTotal() * 0.1;
	}

	public Double getCountryTax() {
		return this.getSubTotal() * 0.05;
	}

	public Double getStateTax() {
		return this.getSubTotal() * 0.08;
	}

	public Double getFederalTax() {
		return this.getSubTotal() * 0.02;
	}

	public Double getTotalTaxes() {
		return getCityTax() + getCountryTax() + getStateTax() + getFederalTax();
	}
	
	public Double getTotalAmount() {
		return getSubTotal() + getTotalTaxes();
	}

	
	/*
	
	
	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setTotalTaxes(Double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}
	
	*/

}
