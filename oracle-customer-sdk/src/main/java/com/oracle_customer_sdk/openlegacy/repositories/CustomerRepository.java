package com.oracle_customer_sdk.openlegacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oracle_customer_sdk.openlegacy.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
