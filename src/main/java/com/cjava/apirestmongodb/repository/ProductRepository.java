package com.cjava.apirestmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cjava.apirestmongodb.model.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>  {

}
