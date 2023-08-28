package com.example.demo.services;

import com.example.demo.domain.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.utils.CarCSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * service for maintaining car information
 */
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
    public void importCars(final InputStream inputStream) {
        final List<Car> cars = carCSVUtils.cvsToCarList(inputStream);
        carRepository.saveAll(cars);
    }

    @Override
    public Car createNewCar(final Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCarById(final Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car patchCar(final Long id, final Car car) {
        return carRepository.findById(id).map(c -> {

            //TODO: expand options

            if (car.getMakeType() != null) {
                c.setMakeType(car.getMakeType());
            }
            if (car.getModel() != null) {
                c.setModel(car.getModel());
            }
            if (car.getVersion() != null) {
                c.setVersion(car.getVersion());
            }
            if (car.getGrossPrice() != null) {
                c.setGrossPrice(car.getGrossPrice());
            }

            return carRepository.save(c);
        }).orElseThrow(ResourceNotFoundException::new);
    }
}
