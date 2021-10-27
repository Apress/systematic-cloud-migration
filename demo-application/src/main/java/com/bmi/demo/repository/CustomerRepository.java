package com.bmi.demo.repository;

import com.bmi.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Copyright Blue Meridian International
 * @author tgleb
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}