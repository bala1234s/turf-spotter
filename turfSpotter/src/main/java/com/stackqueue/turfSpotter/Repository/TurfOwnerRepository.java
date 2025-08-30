package com.stackqueue.turfSpotter.Repository;

import com.stackqueue.turfSpotter.Entity.TurfOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurfOwnerRepository extends JpaRepository<TurfOwnerEntity, Integer> {

    boolean existsByTurfOwnerId(int turfOwnerId);
    boolean existsByUserEntityUsername(String username);
}
