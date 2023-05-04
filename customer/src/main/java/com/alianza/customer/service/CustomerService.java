package com.alianza.customer.service;

import java.util.List;

import com.alianza.customer.model.pojo.Customer;
import com.alianza.customer.model.request.CreateCustomerRequest;

public interface CustomerService {
	Customer getCustomer(String customerId);
	Customer createCustomer(CreateCustomerRequest request);
	List<Customer> getCustomers();
	List<Customer> getCustomers(String name, String phone, String email, String start, String end);
	List<Customer> getCustomersKey(String key);
}
