package com.example.service;

import com.example.dto.TrackingNumberResponseDto;
import com.example.utils.TrackingNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingAppService {

    @Autowired
    public TrackingNumberGenerator trackingNumberGenerator;

    public TrackingNumberResponseDto getTrackingNumberDetails(String originCountryId,
                                                               String destinationCountryId,
                                                               double weight,
                                                               String createdAt,
                                                               String customerId,
                                                               String customerName,
                                                               String customerSlug) {
        String trackingNumber = trackingNumberGenerator.buildTrackingNumber(originCountryId, destinationCountryId,customerId, customerSlug);
        String currentTrackingTime = trackingNumberGenerator.getCurrentRfc3339Timestamp();

        return TrackingNumberResponseDto.builder().tracking_number(trackingNumber).created_at(currentTrackingTime).build();
    }
}
