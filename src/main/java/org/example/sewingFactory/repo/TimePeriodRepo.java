package org.example.sewingFactory.repo;

import org.example.sewingFactory.model.Shift;
import org.example.sewingFactory.model.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimePeriodRepo extends JpaRepository<TimePeriod, Long> {
   Optional<TimePeriod> findByShift(Shift shift);
}
