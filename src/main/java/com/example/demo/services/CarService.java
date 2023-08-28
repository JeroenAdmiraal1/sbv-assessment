package com.example.demo.services;

import com.example.demo.domain.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface CarService {

    /**
     * method to validate the format of an uploaded file
     * @param file the uploaded file
     * @return true or false
     */
    boolean hasCSVFormat(final MultipartFile file);


    /**
     * method to import CSV file of cars
     * @param inputStream CSV file of cars
     */
    void importCars(final InputStream inputStream);

    /**
     * @param car car to be created
     * @return newly created car
     */
    Car createNewCar(final Car car);

    /**
     * @param id id of car to be deleted
     */
    void deleteCarById(final Long id);

    /**
     * @param id id of car to be patched
     * @param car car with updated values
     * @return the patched car
     */
    Car patchCar(final Long id, final Car car);
}
