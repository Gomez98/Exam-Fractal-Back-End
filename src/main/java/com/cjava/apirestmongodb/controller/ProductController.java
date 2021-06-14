package com.cjava.apirestmongodb.controller;

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

import com.cjava.apirestmongodb.model.Product;
import com.cjava.apirestmongodb.repository.ProductRepository;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}

	@GetMapping("/products")
	public List<Product> getProducts()
	{
		List<Product> productsList = productRepository.findAll();
		return productsList;
	}

	@GetMapping("/product/{id}")
	public Optional<Product> getProduct(@PathVariable Integer id)
	{
		Optional<Product> pro = productRepository.findById(id);
		return pro;
	}

	@PutMapping("/product/{id}")
	public Optional<Product> updateProduct(@PathVariable Integer id,@RequestBody Product newProduct)
	{
		Optional<Product> optionalPro = productRepository.findById(id);
		if (optionalPro.isPresent()) {
			Product pro = optionalPro.get();
			pro.setName(newProduct.getName());
			pro.setUnitPrice(newProduct.getUnitPrice());
			pro.setCategory(newProduct.getCategory());
			pro.setActive(newProduct.getActive());
			productRepository.save(pro);
		}
		return optionalPro;
	}

	@DeleteMapping(value = "/product/{id}", produces = "application/json; charset=utf-8")
	public String deleteProduct(@PathVariable Integer id) {
		Boolean result = productRepository.existsById(id);
		productRepository.deleteById(id);
		return "{ \"success\" : "+ (result ? "true" : "false") +" }";
	}

	@PostMapping("/product")
	public Product addProduct(@RequestBody Product newProduct)
	{
		//Integer id = Integer.valueOf(new Random().nextInt());
		Product pro = new Product(newProduct.getId(), newProduct.getName(), newProduct.getCategory(),
				newProduct.getUnitPrice(), newProduct.getActive());
		productRepository.insert(pro);
		return pro;
	}
}

