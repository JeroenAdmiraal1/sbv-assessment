package com.example.demo.services;

import com.example.demo.domain.Car;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LeaseService {

    /**
     * Method to calculate the leaserate for a car based on the interest rate on a particular start date
     * @param mileage the amount of kilometers on an annual base
     * @param duration duration of the contract in months
     * @param startDate start date for determining the interest rate
     * @param car for obtaining the nett price
     * @return the leaserate in euros per month
     */
    BigDecimal calculateLeaseRate(final Long mileage, final Long duration, final LocalDate startDate, final Car car);
}
