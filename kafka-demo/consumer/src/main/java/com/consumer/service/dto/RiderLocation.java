package com.consumer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RiderLocation {
    private String riderId;
    private Double longitude;
    private Double latitude;
}

