package org.example.sewingFactory.controller;

import org.example.sewingFactory.dto.EmployeeScheduleRequestDTO;
import org.example.sewingFactory.dto.ScheduleDTO;
import org.example.sewingFactory.model.Schedule;
import org.example.sewingFactory.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('read')")
    public Iterable<Schedule> getScheduleWithTimePeriod(@RequestBody EmployeeScheduleRequestDTO employeeScheduleRequestDTO) {

        return scheduleService.getUserScheduleByDate(employeeScheduleRequestDTO);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('write')")
    public ScheduleDTO addSchedule(@RequestBody ScheduleDTO scheduleDTO) {

         return scheduleService.addSchedule(scheduleDTO);
    }

//    @PostMapping("/user_add")
//    @PreAuthorize("hasAuthority('write')")
//    public void addPeopleInSchedule(@RequestBody ScheduleDTO scheduleDTO) {
//        scheduleService.addUserInSchedule(scheduleDTO);
//}
//    public void addPeopleInSchedule(@PathVariable Long userId,
//                                    @PathVariable Long scheduleId) {
//        scheduleService.addUserInSchedule(userId, scheduleId);
//    }
}