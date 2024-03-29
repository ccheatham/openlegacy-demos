package com.oracle_table_sdk.openlegacy.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oracle_table_sdk.openlegacy.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Collection<Customer>findByNameContaining(String name);
}
