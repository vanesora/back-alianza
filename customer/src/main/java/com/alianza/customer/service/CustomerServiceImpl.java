package com.alianza.customer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alianza.customer.data.CustomerRepository;
import com.alianza.customer.model.pojo.Customer;
import com.alianza.customer.model.request.CreateCustomerRequest;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers;
		customers = repository.findAll();
		System.out.println("list: "+customers.toString());
		return customers.isEmpty() ? null : customers;
	}

	@Override
	public List<Customer> getCustomers(String name, String phone, String email, String start, String end) {
		List<Customer> customers;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		if(start != null) {
			try {
				System.out.println("start: " + start);
				startDate = formato.parse(start);
				System.out.println("here: ");
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		if(end != null) {
			try {
				System.out.println("end: " + end);
				endDate = formato.parse(end);
				System.out.println("here: ");
			} catch (ParseException e) {
				e.printStackTrace();
			} 	
		}
		if(name == null) {
			name = "";
		}
		if(phone == null) {
			phone = "";
		}
		if(email == null) {
			email = "";
		}
		if(startDate == null) {
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
			String input = "1990-01-01"; 
			try {
				startDate= ft.parse(input);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		if(endDate == null) {
			endDate = new Date(); 
		}
		System.out.println("name: " + name);
		System.out.println("phone: " + phone);
		System.out.println("email: " + email);
		System.out.println("start: " + startDate);
		System.out.println("end: " + endDate);
		customers = repository.findAllByFilter(name, phone, email, startDate, endDate);
		return customers.isEmpty() ? null : customers;
		/* return null; */
	}
	
	@Override
	public List<Customer> getCustomersKey(String key) {
		List<Customer> customers;
		customers = repository.findAllByKey(key);
		return customers.isEmpty() ? null : customers;
	}

	@Override
	public Customer getCustomer(String movieId) {
		return repository.findById(Long.valueOf(movieId)).orElse(null);
	}


	@Override
	public Customer createCustomer(CreateCustomerRequest request) {

		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getEmail().trim())
				&& StringUtils.hasLength(request.getPhone().trim()) 
				&& StringUtils.hasLength(request.getAddedDate().toString().trim())) {
			int iend = request.getEmail().indexOf("@");
			Customer customer = Customer.builder()
					.id(request.getEmail().substring(0, iend))
					.name(request.getName().toUpperCase())
					.email(request.getEmail())
					.phone(request.getPhone())
					.addedDate(request.getAddedDate())
					.build();

			return repository.save(customer);
		} else {
			return null;
		}
	}
}
