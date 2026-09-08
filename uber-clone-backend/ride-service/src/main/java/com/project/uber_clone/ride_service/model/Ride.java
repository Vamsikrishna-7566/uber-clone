package com.project.uber_clone.ride_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long riderId;
    private Long driverId;
    private String pickupLocation;
    private String dropoffLocation;
    @Enumerated(EnumType.STRING)
    private RideStatus status;
    private Double fare;
    private Long timestamp;
}
