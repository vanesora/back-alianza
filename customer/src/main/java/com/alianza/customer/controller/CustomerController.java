package com.alianza.customer.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.customer.model.pojo.Customer;
import com.alianza.customer.model.request.CreateCustomerRequest;
import com.alianza.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins= {"*"}, maxAge = 16000, allowCredentials = "false" )
@RestController
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService service;

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) {
		List<Customer> customers = null;
		if (key != null) {
			customers = service.getCustomersKey(key);
		} else if (name != null || phone != null || email != null || start != null || end != null) {
			customers = service.getCustomers(name, phone, email, start, end);
		} else{
			customers = service.getCustomers();
		}
		{
		}
		if (customers != null) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {
		Customer customer = service.getCustomer(customerId);

		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> getCustomer(@RequestBody CreateCustomerRequest request) {

		Customer createdCustomer = service.createCustomer(request);

		if (createdCustomer != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}