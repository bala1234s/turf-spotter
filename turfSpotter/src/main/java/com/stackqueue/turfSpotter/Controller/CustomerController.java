package com.stackqueue.turfSpotter.Controller;

import com.stackqueue.turfSpotter.Dto.CustomerRequestDto;
import com.stackqueue.turfSpotter.Dto.CustomerResponseDto;
import com.stackqueue.turfSpotter.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable int customerId,@RequestBody CustomerRequestDto customerDto){
        return customerService.updateCustomer(customerId,customerDto);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId){
        return customerService.deleteCustomer(customerId);

    }
}
