package com.example.demo.controllers;

import com.example.demo.domain.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.services.LeaseService;
import com.example.demo.services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(LeaseController.BASE_URL)
public class LeaseController {

    public static final String BASE_URL = "/lease";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/mileage/{mileage}/duration/{duration}/date/{startDate}/car/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public String getCategoryByName(@PathVariable final Long mileage, @PathVariable final Long duration, @PathVariable final String startDate, @PathVariable final Long carId) {
        final Car car = carRepository.findById(carId).orElse(null);
        if (car == null) {
            throw new ResourceNotFoundException("Car unknown");
        }
        return leaseService.calculateLeaseRate(mileage, duration, LocalDate.parse(startDate, formatter), car).toString();
    }

}
