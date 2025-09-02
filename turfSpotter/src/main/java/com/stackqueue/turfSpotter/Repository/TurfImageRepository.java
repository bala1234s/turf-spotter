package com.stackqueue.turfSpotter.Repository;

import com.stackqueue.turfSpotter.Entity.TurfImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurfImageRepository extends JpaRepository<TurfImageEntity, Integer> {
    List<TurfImageEntity> findByTurfDetailsEntity_TurfId(int turfId);
}
