package org.example.sewingFactory.controller;

import org.example.sewingFactory.dto.EmployeeScheduleRequestDTO;
import org.example.sewingFactory.dto.ScheduleDTO;
import org.example.sewingFactory.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('read')")
    public List<EmployeeScheduleRequestDTO> getScheduleWithTimePeriod(@RequestBody EmployeeScheduleRequestDTO employeeScheduleRequestDTO) {

        return scheduleService.getUserScheduleByDate(employeeScheduleRequestDTO);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('write')")
    public ScheduleDTO addSchedule(@RequestBody ScheduleDTO scheduleDTO) {

         return scheduleService.addSchedule(scheduleDTO);
    }
}