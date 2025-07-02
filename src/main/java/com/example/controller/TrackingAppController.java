package com.example.controller;

import com.example.dto.TrackingNumberResponseDto;
import com.example.service.TrackingAppService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TrackingAppController {

    @Autowired
    private TrackingAppService trackingAppService;

    @GetMapping("/next-tracking-number")
    public TrackingNumberResponseDto nextTrackingNumber(
            @RequestParam(name = "origin_country_id", required = false) @NotBlank(message = "Origin Country id must not be blank") String originCountryId,
            @RequestParam(name = "destination_country_id", required = false) @NotBlank(message = "Destination Country id must not be blank") String destinationCountryId,
            @RequestParam(defaultValue = "1") double weight,
            @RequestParam(name = "created_at" , required = false) String createdAt,
            @RequestParam(name = "customer_id",  required = false) @NotBlank(message = "Customer id must not be blank") String customerId,
            @RequestParam(name =  "customer_name" , required = false) String customerName,
            @RequestParam(name = "customer_slug",  required = false) @NotBlank(message = "Customer slug must not be blank") String customerSlug ) {
        return trackingAppService.getTrackingNumberDetails(originCountryId, destinationCountryId, weight, createdAt,customerId, customerName, customerSlug);
    }
}
