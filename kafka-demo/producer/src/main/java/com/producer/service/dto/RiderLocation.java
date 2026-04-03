package com.producer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class RiderLocation {
    private String riderId;
    private double longitude;
    private double latitude;
}

