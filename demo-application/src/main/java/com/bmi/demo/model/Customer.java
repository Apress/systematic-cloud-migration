package com.bmi.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Copyright Blue Meridian International
 * @author tgleb
 */
@Entity
@Table(name="customer")
public class Customer {
	

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	@Column
	private String address;
	
	@Column
	private int age;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	protected Customer() {}
	
	public Customer(String firstname, String lastname, String address, int age) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.address = address;
		this.age = age;
	}
	
	public String toString() {
		return String.format("id=%d, firstname='%s', lastname'%s', address=%s, age=%d", 
								id, firstName, lastName, address, age);
	}
}