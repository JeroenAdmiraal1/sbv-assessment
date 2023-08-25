package com.example.demo.services;

import com.example.demo.domain.Car;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LeaseService {

    BigDecimal calculateLeaseRate(final Long mileage, final Long duration, final LocalDate startDate, final Car car);
}
