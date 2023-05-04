package com.alianza.customer.model.request;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
	private String name;
	private String phone;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date addedDate;
}
