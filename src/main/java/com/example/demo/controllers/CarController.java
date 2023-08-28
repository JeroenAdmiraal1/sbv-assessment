package com.example.demo.controllers;

import com.example.demo.domain.Car;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * controller for REST endpoints for cars
 */
@RestController
@RequestMapping(CarController.BASE_URL)
public class CarController {

    public static final String BASE_URL = "/car";

    @Autowired
    private CarService service;

    @PostMapping
    public ResponseEntity<Car> createNewCustomer(@RequestBody final Car car) {
        return new ResponseEntity<>(service.createNewCar(car), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> patchCar(@PathVariable final String id, @RequestBody final Car car) {
        return new ResponseEntity<>(service.patchCar(Long.valueOf(id), car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable final String id) {
        service.deleteCarById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") final MultipartFile file) {
        if (service.hasCSVFormat(file)) {
            try {
                service.importCars(file.getInputStream());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
