package com.project.uber_clone.ride_service.service;


import com.project.uber_clone.ride_service.model.Ride;
import com.project.uber_clone.ride_service.model.RideStatus;
import com.project.uber_clone.ride_service.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    // Rider request a ride
    public Ride requestRide(Ride ride){
        ride.setStatus(RideStatus.REQUESTED);
        ride.setTimestamp(System.currentTimeMillis());
        return rideRepository.save(ride);
    }

    //Driver accepts a ride.
    public Ride acceptRide(Long rideId, Long driverId){
        Ride ride =  rideRepository.findById(rideId)
                .orElseThrow(()-> new RuntimeException("Ride not found"));

        if(ride.getStatus() != RideStatus.REQUESTED){
            throw new RuntimeException("Ride is not available for acceptance");
        }

        ride.setDriverId(driverId);
        ride.setStatus(RideStatus.ACCEPTED);

        return rideRepository.save(ride);
    }

    //Driver starts the ride
    public Ride startRide(Long rideId, Long driverId){
       Ride ride =  rideRepository.findById(rideId).orElseThrow(()-> new RuntimeException("Ride not found"));
       if(ride.getStatus() != RideStatus.ACCEPTED){
           throw new RuntimeException("Ride cannot be started");
       }

       if(!ride.getDriverId().equals(driverId)){
           throw new RuntimeException("This driver is not assigned to this ride");
       }

       ride.setStatus(RideStatus.IN_PROGRESS);

       return rideRepository.save(ride);
    }

    //get all rides of a rider
    public List<Ride> getRidesByRider(Long riderId){
        return rideRepository.findByRiderId(riderId);
    }

    //get all rider of a driver
    public List<Ride> getRiderByDriver(Long driverId){
       return rideRepository.findByDriverId(driverId);
    }
}
