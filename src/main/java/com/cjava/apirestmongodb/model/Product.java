package com.cjava.apirestmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Document(collection = "product")
public class Product {
	@Id
	private @NonNull Integer id;
	private @NonNull String name;
	private @NonNull String category;
	private @NonNull Double unitPrice;
	private @NonNull String active;
}
