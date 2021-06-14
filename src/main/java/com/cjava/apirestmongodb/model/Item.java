package com.cjava.apirestmongodb.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Item {
	private Integer id;
	private Product product;
	private Integer quantity;
	
	 public Double getAmount(){
	        return product.getUnitPrice()*quantity;
	    }
	
}
