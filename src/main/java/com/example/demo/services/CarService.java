package com.example.demo.services;

import com.example.demo.domain.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface CarService {

    boolean hasCSVFormat(final MultipartFile file);

    void importCars(final InputStream inputStream);

    Car createNewCar(final Car car);

    void deleteCarById(final Long id);

    Car patchCar(final Long id, final Car car);
}
