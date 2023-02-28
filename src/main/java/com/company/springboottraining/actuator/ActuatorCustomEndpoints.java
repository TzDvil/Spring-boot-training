package com.company.springboottraining.actuator;

import com.company.springboottraining.services.CustomerService;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@Endpoint(id = "arrays")
public class ActuatorCustomEndpoints {

    //TEST

    private final CustomerService customerService;

    public ActuatorCustomEndpoints(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ReadOperation
    public Map<?,?> readArrayCustomers(){
        Map<Integer,Integer> response = new HashMap<>();
        customerService.readCustomers().forEach( customer -> {
            response.put(customer.getCustomerID(), customer.getArrayCustomer().size());
        });
        return response;
    }

    @WriteOperation
    public void writeOperation() {

    }

    @DeleteOperation
    public void deleteOperation(){

    }
}
