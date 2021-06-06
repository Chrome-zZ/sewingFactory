package org.example.sewingFactory.controller;

import org.example.sewingFactory.model.TimePeriod;
import org.example.sewingFactory.service.TimePeriodService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shifts")
public class TimePeriodController {

    private final TimePeriodService timePeriodService;

    public TimePeriodController(TimePeriodService timePeriodService) {
        this.timePeriodService = timePeriodService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public TimePeriod addShift(@RequestBody TimePeriod timePeriod) {

        return timePeriodService.addShift(timePeriod);
    }

}