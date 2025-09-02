package com.stackqueue.turfSpotter.Repository;

import com.stackqueue.turfSpotter.Entity.TurfTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurfTypeRepository extends JpaRepository<TurfTypeEntity, Integer> {

//    public boolean existsByTurfTypeEntitiesTurfId(int turfId);
}
