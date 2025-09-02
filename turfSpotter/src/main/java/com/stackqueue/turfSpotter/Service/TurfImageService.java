package com.stackqueue.turfSpotter.Service;

import com.stackqueue.turfSpotter.Dto.TurfImageResponseDto;
import com.stackqueue.turfSpotter.Entity.TurfDetailsEntity;
import com.stackqueue.turfSpotter.Entity.TurfImageEntity;
import com.stackqueue.turfSpotter.POJO.TurfDetailsException;
import com.stackqueue.turfSpotter.Repository.TurfDetailsRepository;
import com.stackqueue.turfSpotter.Repository.TurfImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurfImageService {
    @Autowired
    private TurfImageRepository turfImageRepository;

    @Autowired
    private TurfDetailsRepository turfDetailsRepository;

    // ✅ POST images
    @Transactional
    public void addTurfImagesToTurfDetails(int turfDetailsId, List<TurfImageEntity> images) {
        if (images == null || images.isEmpty()) {
            throw new TurfDetailsException(HttpStatus.BAD_REQUEST, "Image list cannot be null or empty!", LocalDate.now());
        }

        TurfDetailsEntity turfDetails = turfDetailsRepository.findById(turfDetailsId)
                .orElseThrow(() -> new TurfDetailsException(HttpStatus.NOT_FOUND, "This Turf is not Found!!!", LocalDate.now()));

        images.forEach(dto -> {
            TurfImageEntity turfImage = TurfImageEntity.builder()
                    .imageUrl(dto.getImageUrl())
                    .caption(dto.getCaption())
                    .turfDetailsEntity(turfDetails)
                    .build();
            turfImageRepository.save(turfImage);
        });
    }

    // ✅ GET images by TurfId
    public List<TurfImageResponseDto> getImagesByTurfId(int turfId) {
        List<TurfImageEntity> images = turfImageRepository.findByTurfDetailsEntity_TurfId(turfId);
        return images.stream().map(img -> {
            TurfImageResponseDto dto = new TurfImageResponseDto();
            dto.setImageUrl(img.getImageUrl());
            dto.setCaption(img.getCaption());
            return dto;
        }).toList();
    }
}
