package com.example.demo.controllers;

import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CarController.BASE_URL)
public class CarController {

    public static final String BASE_URL = "/car";

    @Autowired
    private CarService carService;
}
