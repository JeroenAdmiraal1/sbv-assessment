package com.example.demo.services;

import com.example.demo.domain.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.utils.CarCSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarCSVUtils carCSVUtils;

    @Override
    public boolean hasCSVFormat(final MultipartFile file) {
        return carCSVUtils.hasCSVFormat(file);
    }

    @Override
    public void saveCars(final InputStream inputStream) {
        final List<Car> cars = carCSVUtils.cvsToCarList(inputStream);
        carRepository.saveAll(cars);
    }
}
