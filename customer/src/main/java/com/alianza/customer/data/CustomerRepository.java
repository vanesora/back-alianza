package com.alianza.customer.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alianza.customer.model.pojo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT p FROM Customer p WHERE  LOWER(p.id) LIKE LOWER(CONCAT('%', :keyword,'%')) ")
	public List<Customer> findAllByKey(String keyword);

	@Query("SELECT p FROM Customer p WHERE  LOWER(p.name) LIKE LOWER(CONCAT('%', :name,'%')) AND p.phone LIKE(CONCAT('%', :phone,'%'))"
			+ "AND p.email LIKE(CONCAT('%', :email,'%')) AND p.addedDate BETWEEN :start AND :end")
	public List<Customer> findAllByFilter(String name, String phone, String email, Date start, Date end);
}
