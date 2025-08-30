package com.stackqueue.turfSpotter.Service;

import com.stackqueue.turfSpotter.Dto.CustomerRequestDto;
import com.stackqueue.turfSpotter.Dto.CustomerResponseDto;
import com.stackqueue.turfSpotter.Dto.UserRequestDto;
import com.stackqueue.turfSpotter.Dto.UserResponseDto;
import com.stackqueue.turfSpotter.Entity.CustomerEntity;
import com.stackqueue.turfSpotter.Entity.UserEntity;
import com.stackqueue.turfSpotter.Enum.UserRole;
import com.stackqueue.turfSpotter.Enum.UserStatus;
import com.stackqueue.turfSpotter.POJO.CustomerException;
import com.stackqueue.turfSpotter.POJO.ServerResponse;
import com.stackqueue.turfSpotter.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public ResponseEntity<?> createCustomer(CustomerRequestDto customerDto) {

        if(!customerRepository.existsByUserEntityUsername(customerDto.getUserDto().getUsername())){
            UserEntity user = UserEntity.builder()
                    .username(customerDto.getUserDto().getUsername())
                    .userPassword(customerDto.getUserDto().getUserPassword())
                    .userRole(UserRole.CUSTOMER)
                    .accountCreatedAt(LocalDate.now())
                    .build();
            CustomerEntity customer = CustomerEntity.builder()
                    .customerName(customerDto.getCustomerName())
                    .customerPhoneNumber(customerDto.getCustomerPhoneNumber())
                    .isCustomerActive(true)
                    .accountRecentlyUpdateDate(LocalDate.now())
                    .customerImageUrl(customerDto.getCustomerImageUrl())
                    .customerStatus(UserStatus.ONLINE)
                    .userEntity(user)
                    .build();


            customerRepository.save(customer);
            return new ResponseEntity<>(new ServerResponse(HttpStatus.CREATED,"This Customer is Successfully Registered",LocalDate.now()), HttpStatus.CREATED);
        }

       throw new CustomerException(HttpStatus.CONFLICT,"This Customer is Already Exist!",LocalDate.now());

    }

    public ResponseEntity<?> getCustomerById(int customerId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerException(HttpStatus.NOT_FOUND, "Customer Not Found!!!",LocalDate.now()));

        return new ResponseEntity<>(customer,HttpStatus.FOUND);
    }

    @Transactional
    public ResponseEntity<?> updateCustomer(int customerId, CustomerRequestDto customerDto) {
        if(customerRepository.existsByCustomerId(customerId)){
            CustomerEntity customerDetails = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerException(HttpStatus.NOT_FOUND,"This CustomerId is Not Found",LocalDate.now()));
            UserEntity user = UserEntity.builder()
                    .username(customerDetails.getUserEntity().getUsername())
                    .userPassword(customerDto.getUserDto().getUserPassword() == null ? customerDetails.getUserEntity().getUserPassword():customerDto.getUserDto().getUserPassword() )
                    .userRole(customerDetails.getUserEntity().getUserRole())
                    .accountCreatedAt(customerDetails.getUserEntity().getAccountCreatedAt())
                    .build();
            CustomerEntity customer = CustomerEntity.builder()
                    .customerId(customerDetails.getCustomerId())
                    .customerName(customerDto.getCustomerName() == null ? customerDetails.getCustomerName():customerDto.getCustomerName())
                    .customerPhoneNumber(customerDto.getCustomerPhoneNumber() == null ? customerDetails.getCustomerPhoneNumber():customerDto.getCustomerPhoneNumber())
                    .isCustomerActive(customerDetails.isCustomerActive())
                    .accountRecentlyUpdateDate(LocalDate.now())
                    .customerImageUrl(customerDto.getCustomerImageUrl() == null ? customerDetails.getCustomerImageUrl():customerDto.getCustomerImageUrl())
                    .customerStatus(UserStatus.ONLINE)
                    .userEntity(user)
                    .build();

            customerRepository.save(customer);

            return new ResponseEntity<>(new ServerResponse(HttpStatus.OK,"The Customer is Updated Successfully", LocalDate.now()),HttpStatus.OK);
        }else {
            throw new CustomerException(HttpStatus.NOT_FOUND,"This CustomerId is Not Found",LocalDate.now());
        }
    }

    public List<CustomerResponseDto> getAllCustomers() {
        List<CustomerResponseDto> customers = customerRepository.findAll()
                .stream().map(this::mapToCustomerDto).toList();

        return customers;
    }

    private CustomerResponseDto mapToCustomerDto(CustomerEntity customer) {
        CustomerResponseDto customerDto = new CustomerResponseDto();
        UserResponseDto userResponse = new UserResponseDto();

        userResponse.setUsername(customer.getUserEntity().getUsername());
        userResponse.setUserRole(customer.getUserEntity().getUserRole());
        userResponse.setAccountCreatedAt(customer.getUserEntity().getAccountCreatedAt());

        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        customerDto.setCustomerActive(customer.isCustomerActive());
        customerDto.setCustomerImageUrl(customer.getCustomerImageUrl());
        customerDto.setAccountRecentlyUpdateDate(customer.getAccountRecentlyUpdateDate());
        customerDto.setCustomerStatus(customer.getCustomerStatus());
        customerDto.setUserResponse(userResponse);

        return customerDto;
    }
    @Transactional
    public ResponseEntity<?> deleteCustomer(int customerId) {
        if(customerRepository.existsByCustomerId(customerId)){
            customerRepository.deleteById(customerId);
        }
        else{
            throw new CustomerException(HttpStatus.NOT_FOUND,"This Customer is Not Found", LocalDate.now());
        }

       return new ResponseEntity<>(new ServerResponse(HttpStatus.OK,"This Customer is Deleted Successfully!!!", LocalDate.now()), HttpStatus.OK);
    }
}
