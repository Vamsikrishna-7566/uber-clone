package com.project.uber_clone.ride_service.controller;

import com.project.uber_clone.ride_service.model.Ride;
import com.project.uber_clone.ride_service.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {
    @Autowired
    RideService rideService;

    //Rider request a ride
    @PreAuthorize("hasRole('RIDER')")
    @PostMapping("/request")
    public Ride requestRide(@RequestBody Ride ride){
         return rideService.requestRide(ride);
    }
    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping("/{rideId}/accept")
    public Ride acceptRide(@PathVariable Long rideId, @RequestParam Long driverId){
        return rideService.acceptRide(rideId, driverId);
    }

    //get rides by rider
    @GetMapping("/rider/{riderId}")
    public List<Ride> getRidesByRider(@PathVariable Long riderId){
        return rideService.getRidesByRider(riderId);
    }
    @GetMapping("/driver/{driverId}")
    public List<Ride> getRidesByDriver(@PathVariable Long driverId){
        return rideService.getRiderByDriver(driverId);
    }
}
