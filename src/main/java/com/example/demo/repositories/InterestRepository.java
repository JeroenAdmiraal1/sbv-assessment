package com.example.demo.repositories;

import com.example.demo.domain.Interest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface InterestRepository extends CrudRepository<Interest, Long> {

    @Query("SELECT i FROM Interest i WHERE i.startDate = ?1")
    Optional<Interest> findByStartDate(final LocalDate date);
}
