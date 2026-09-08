package com.project.uber_clone.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/user")
    public String getTestMessage(){
        return "Hello man! your JWT is working !!";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminConfirmation(){
        return "Admin here!";
    }
    @GetMapping("/rider")
    @PreAuthorize("hasRole('RIDER')")
    public String getRiderConfirmation(){
        return "Rider here!";
    }

    @GetMapping("/driver")
    @PreAuthorize("hasRole('DRIVER')")
    public String getDriverConfirmation(){
        return "Driver here!";
    }
}
