package com.stackqueue.turfSpotter.Service;

import com.stackqueue.turfSpotter.Dto.TurfTypeDto;
import com.stackqueue.turfSpotter.Entity.TurfDetailsEntity;
import com.stackqueue.turfSpotter.Entity.TurfTypeEntity;
import com.stackqueue.turfSpotter.POJO.TurfDetailsException;
import com.stackqueue.turfSpotter.POJO.TurfTypeException;
import com.stackqueue.turfSpotter.Repository.TurfDetailsRepository;
import com.stackqueue.turfSpotter.Repository.TurfTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurfTypeService {

    @Autowired
    private TurfTypeRepository turfTypeRepository;

    @Autowired
    private TurfDetailsRepository turfDetailsRepository;

    @Transactional
    public void addTurfTypeToTurfDetails(int turfDetailsId, List<TurfTypeEntity> turfTypeEntities) {

        if (turfTypeEntities == null || turfTypeEntities.isEmpty()) {
            throw new TurfTypeException(HttpStatus.BAD_REQUEST, "Turf type list cannot be null or empty!", LocalDate.now());
        }

        TurfDetailsEntity turfDetails = turfDetailsRepository.findById(turfDetailsId)
                .orElseThrow(() -> new TurfDetailsException(HttpStatus.NOT_FOUND, "This Turf is not Found!!!", LocalDate.now()));

        turfTypeEntities.forEach(type -> {
            TurfTypeEntity turfTypeEntity = TurfTypeEntity.builder()
                    .turfDetailsEntity(turfDetails)   // ✅ Always set parent entity, not null
                    .turfTypeName(type.getTurfTypeName())
                    .build();
            turfTypeRepository.save(turfTypeEntity);
        });
    }


    public TurfTypeEntity getTurfTypeByTurfId(int turfId) {
        return turfTypeRepository.findById(turfId)
                .orElseThrow(()-> new TurfTypeException(HttpStatus.NOT_FOUND, "This Turf Types are not Found!!!", LocalDate.now()));
    }
}
