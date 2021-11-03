package com.company.springboottraining.data;

import com.company.springboottraining.models.Customer;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CustomerRepository extends CassandraRepository<Customer, Integer> {

}
