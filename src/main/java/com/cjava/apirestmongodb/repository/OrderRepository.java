package com.cjava.apirestmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cjava.apirestmongodb.model.Order;

public interface OrderRepository extends MongoRepository<Order, Integer>  {

}
