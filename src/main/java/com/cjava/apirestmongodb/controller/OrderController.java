package com.cjava.apirestmongodb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjava.apirestmongodb.model.Item;
import com.cjava.apirestmongodb.model.Order;
import com.cjava.apirestmongodb.model.Product;
import com.cjava.apirestmongodb.repository.OrderRepository;

@RestController
@RequestMapping(value = "/api/v2/")
@CrossOrigin("*")
public class OrderController {
	@Autowired
	OrderRepository orderRepository;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}

	@GetMapping("/orders")
	public List<Order> getOrders()
	{
		List<Order> ordersList = orderRepository.findAll();
		return ordersList;
	}

	@GetMapping("/order/{id}")
	public Optional<Order> getOrder(@PathVariable Integer id)
	{
		Optional<Order> ord = orderRepository.findById(id);
		return ord;
	}

	@PutMapping("/order/{id}")
	public Optional<Order> updateOrder(@RequestBody Order newOrder, @PathVariable Integer id)
	{
		Date myDate = new Date();
		Optional<Order> optionalPro = orderRepository.findById(id);
		if (optionalPro.isPresent()) {
			Order ord = optionalPro.get();
			ord.setStatus(newOrder.getStatus());
			ord.setDate(myDate);
			ord.setCustomer(newOrder.getCustomer());
			ord.setOrderNumber(newOrder.getOrderNumber());
			ord.setOrderItems(newOrder.getOrderItems());
			orderRepository.save(ord);
		}
		return optionalPro;
	}

	@DeleteMapping(value = "/order/{id}", produces = "application/json; charset=utf-8")
	public String deleteOrder(@PathVariable Integer id) {
		Boolean result = orderRepository.existsById(id);
		orderRepository.deleteById(id);
		return "{ \"success\" : "+ (result ? "true" : "false") +" }";
	}

	@PostMapping("/order")
	public Order addOrder(@RequestBody Order newOrder)
	{
		Date myDate = new Date();
		Order ord = new Order();
		
		ord.setDate(myDate);
		ord.setCustomer(newOrder.getCustomer());
		ord.setOrderNumber(newOrder.getOrderNumber());
		ord.setStatus(newOrder.getStatus());
		ord.setOrderItems(newOrder.getOrderItems());

		orderRepository.insert(ord);
		return ord;
	}
}
