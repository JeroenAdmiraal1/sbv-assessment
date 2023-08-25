package com.example.demo.services;

import com.example.demo.domain.Car;
import com.example.demo.domain.Interest;
import com.example.demo.repositories.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private InterestRepository interestRepository;

    @Override
    public BigDecimal calculateLeaseRate(final Long mileage, final Long duration, final LocalDate startDate, final Car car) {

        final BigDecimal mileageForDurationDividedByNettPrice = BigDecimal.valueOf(getMileageForDuration(mileage, duration)).divide(car.getNettPrice(), RoundingMode.CEILING);

        final Interest interest = interestRepository.findByStartDate(startDate).orElse(null);

        if (interest != null) {
            final BigDecimal interestRateForNettPrice = BigDecimal.valueOf(interest.getInterestRate()).divide(BigDecimal.valueOf(100L), RoundingMode.CEILING).multiply(car.getNettPrice());
            return mileageForDurationDividedByNettPrice.add(interestRateForNettPrice);
        }
        return null;
    }

    private long getMileageForDuration(final Long mileage, final Long duration) {
        return mileage / 12 * duration;
    }
}
