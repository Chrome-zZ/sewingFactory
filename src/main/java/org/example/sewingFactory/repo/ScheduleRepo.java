package org.example.sewingFactory.repo;

import org.example.sewingFactory.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
