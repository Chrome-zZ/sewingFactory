package org.example.sewingFactory.service;

import org.example.sewingFactory.model.TimePeriod;
import org.example.sewingFactory.repo.TimePeriodRepo;
import org.springframework.stereotype.Service;

@Service
public class TimePeriodService {

    private final TimePeriodRepo timePeriodRepo;

    public TimePeriodService(TimePeriodRepo timePeriodRepo) {
        this.timePeriodRepo = timePeriodRepo;
    }

    public TimePeriod addShift(TimePeriod timePeriod) {
        TimePeriod newTimePeriod = new TimePeriod();
        newTimePeriod.setBeginTime(timePeriod.getBeginTime());
        newTimePeriod.setEndTime(timePeriod.getEndTime());
        newTimePeriod.setShift(timePeriod.getShift());

        return timePeriodRepo.save(newTimePeriod);
    }
}
