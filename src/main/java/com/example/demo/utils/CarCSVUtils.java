package com.example.demo.utils;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarMakeType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils class for CarService
 */
@Component
public class CarCSVUtils {

    public final String TYPE = "text/csv";

    public boolean hasCSVFormat(final MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public List<Car> cvsToCarList(final InputStream inputStream) {
        try {
            final BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            final CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            final List<Car> cars = new ArrayList<>();
            final Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            csvRecords.forEach(record -> {
                final Car car = Car.builder()
                        .makeType(CarMakeType.fromDescription(record.get("make")))
                        .model(record.get("model"))
                        .version(record.get("version"))
                        .numberOfDoors(Integer.parseInt(record.get("#doors")))
                        .grossPrice(BigDecimal.valueOf(Double.parseDouble(record.get("gross_price"))))
                        .nettPrice(BigDecimal.valueOf(Double.parseDouble(record.get("nett_price"))))
                        .hp(Long.valueOf(record.get("hp")))
                        .build();
                cars.add(car);
            });
            return cars;
        } catch (final Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
