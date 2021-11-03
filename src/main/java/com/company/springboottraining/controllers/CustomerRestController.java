package com.company.springboottraining.controllers;


import com.company.springboottraining.models.Customer;
import com.company.springboottraining.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //---------------------------------- CRUD -------------------------------------------//
    @PostMapping(value = "/API/customers/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        if(customer.getArrayCustomer().isEmpty() || customer.getName().isEmpty()){
            return new ResponseEntity<>("MISSING/WRONG INFORMATION FOR REGISTRATION", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerService.createCustomer(customer),HttpStatus.CREATED);
    }

    @GetMapping("/API/customers/read")
    public ResponseEntity<?> readCustomers(){
        if(customerService.readCustomers().isEmpty()){
            return new ResponseEntity<>("NO CONTENT REGISTERED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerService.readCustomers(),HttpStatus.OK);
    }

    @PutMapping("/API/customers/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") Integer id,
                              @RequestBody Customer customer){
        if(customer.getArrayCustomer().isEmpty() || customer.getName().isEmpty() || customer.getCustomerID() != id){
            return new ResponseEntity<>("MISSING/WRONG INFORMATION FOR REGISTRATION", HttpStatus.BAD_REQUEST);
        }else{
            if(customerService.updateCustomer(id,customer) == HttpStatus.OK){
                return new ResponseEntity<>("SUCCESSFULLY UPDATE ON CUSTOMER #" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("CUSTOMER #" + id + " DOES NOT EXISTS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/API/customers/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "id") Integer id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("SUCCESSFULLY ELIMINATION OF CUSTOMER #" + id,  HttpStatus.OK);
    }

    //---------------------------------- ADDITIONAL OPS ----------------------------------------//

    @GetMapping("/API/customers/read/{id}")
    public ResponseEntity<?> readCustomersById(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(customerService.readCustomersById(id), HttpStatus.OK);
    }

    @GetMapping("/API/customers/first/{id}")
    public ResponseEntity<?> getFirstElement(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>("FIRST ELEMENT IN CUSTOMER #" + id + " ARRAY IS - " +
                customerService.getFirstElement(id),HttpStatus.OK);
    }

    @GetMapping("/API/customers/duplicated/{id}")
    public ResponseEntity<?> getDuplicateElements(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>("DUPLICATED VALUES IN CUSTOMER #" + id + " ARRAY ARE - " +
                customerService.getDuplicateElements(id),HttpStatus.OK);
    }

    @GetMapping("/API/customers/highest/{id}")
    public ResponseEntity<?> getHighestElement(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>("HIGHEST VALUE IN CUSTOMER #" + id + " IS - " +
                customerService.getHighestElement(id),HttpStatus.OK);
    }

    //----------------------------- EXCEPTION HANDLERS -----------------------------//
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentHandler(MethodArgumentTypeMismatchException ex){
        return new ResponseEntity<>("NOT A VALID PARAM INPUT. MUST BE A NUMBER", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementHandler(NoSuchElementException ex){
        return new ResponseEntity<>("CUSTOMER NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointerHandler(NullPointerException ex){
        return new ResponseEntity<>("MISSING PARAMS IN INPUT. CHECK PARAM LIST", HttpStatus.BAD_REQUEST);
    }
}