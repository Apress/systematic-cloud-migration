package com.bmi.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.bmi.demo.model.Customer;
import com.bmi.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bmi.demo.service.CustomerServices;

/**
 * @Copyright Blue Meridian International
 * @author tgleb
 */

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	CustomerServices customerServices;
	
	@PostMapping("/create")
	public ResponseEntity<Message> addNewCustomer(@RequestBody Customer customer) {
		try {
			Customer returnedCustomer = customerServices.saveCustomer(customer);
			
			return new ResponseEntity<Message>(new Message("Upload Successfully!", 
											Arrays.asList(returnedCustomer), ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail to post a new Customer!", 
											null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@GetMapping("/retrieveinfos")
	public ResponseEntity<Message> retrieveCustomerInfo() {
		
		try {
			List<Customer> customerInfos = customerServices.getCustomerInfos();
			
			return new ResponseEntity<Message>(new Message("Get Customers' Infos!", 
												customerInfos, ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail!",
												null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<Message> getCustomerById(@PathVariable long id) {
		try {
			Optional<Customer> optCustomer = customerServices.getCustomerById(id);
			
			if(optCustomer.isPresent()) {
				return new ResponseEntity<Message>(new Message("Successfully! Retrieve a customer by id = " + id,
															Arrays.asList(optCustomer.get()), ""), HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(new Message("Failure -> NOT Found a customer by id = " + id,
						null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Message> updateCustomerById(@RequestBody Customer _customer, 
																	@PathVariable long id) {
		try {
			if(customerServices.checkExistedCustomer(id)) {
				Customer customer = customerServices.getCustomerById(id).get();
				
				//set new values for customer
				customer.setFirstName(_customer.getFirstName());
				customer.setLastName(_customer.getLastName());
				customer.setAddress(customer.getAddress());
				customer.setAge(_customer.getAge());
	
				// save the change to database
				customerServices.updateCustomer(customer);
				
				return new ResponseEntity<Message>(new Message("Successfully! Updated a Customer "
																		+ "with id = " + id,
																	Arrays.asList(customer), ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Customer "
						+ "with id = " + id,
					null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Message> deleteCustomerById(@PathVariable long id) {
		try {
			// checking the existed of a Customer with id
			if(customerServices.checkExistedCustomer(id)) {
				customerServices.deleteCustomerById(id);
				
				return new ResponseEntity<Message> (new Message("Successfully! Delete a Customer with id = " + id, 
														null, ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Customer "
														+ "with id = " + id, null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}