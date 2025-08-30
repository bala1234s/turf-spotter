package com.stackqueue.turfSpotter.Repository;

import com.stackqueue.turfSpotter.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    boolean existsByCustomerId(int customerId);
    boolean existsByUserEntityUsername(String username);
}
