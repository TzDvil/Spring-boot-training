package com.company.springboottraining.services;

import com.company.springboottraining.data.CustomerRepository;
import com.company.springboottraining.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> readCustomers(){
        return customerRepository.findAll();
    }

    public HttpStatus updateCustomer(int id, Customer customer){
        if(customerRepository.existsById(id)){
            customerRepository.save(customer);
            return HttpStatus.OK;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public void deleteCustomer(int id){
        customerRepository.deleteById(customerRepository.findById(id).get().getCustomerID());
    }

    public Customer readCustomersById(int id){
        return customerRepository.findById(id).get();
    }

    public Integer getFirstElement(int id){
        return  customerRepository.findById(id)
                .get()
                .getArrayCustomer()
                .stream()
                .findFirst()
                .get();
    }

    public List<Integer> getDuplicateElements(Integer id){
        return customerRepository.findById(id)
                .get()
                .getArrayCustomer()
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public Integer getHighestElement(int id){
        return  customerRepository.findById(id)
                .get()
                .getArrayCustomer()
                .stream()
                .max(Comparator.comparing(Integer::valueOf))
                .get();
    }
}
