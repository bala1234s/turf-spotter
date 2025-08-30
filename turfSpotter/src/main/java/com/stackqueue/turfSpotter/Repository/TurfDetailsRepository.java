package com.stackqueue.turfSpotter.Repository;

import com.stackqueue.turfSpotter.Entity.TurfDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurfDetailsRepository extends JpaRepository<TurfDetailsEntity, Integer> {
}
