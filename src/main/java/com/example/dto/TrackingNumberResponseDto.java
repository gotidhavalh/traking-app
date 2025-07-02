package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingNumberResponseDto {

    private String tracking_number;
    private String created_at;
}
