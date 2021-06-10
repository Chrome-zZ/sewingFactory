package org.example.sewingFactory.service;

import org.example.sewingFactory.dto.EmployeeScheduleRequestDTO;
import org.example.sewingFactory.dto.ScheduleDTO;
import org.example.sewingFactory.model.Schedule;
import org.example.sewingFactory.model.Shift;
import org.example.sewingFactory.repo.EmployeeRepo;
import org.example.sewingFactory.repo.ScheduleRepo;
import org.example.sewingFactory.repo.TimePeriodRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final EmployeeRepo employeeRepo;
    private final TimePeriodRepo timePeriodRepo;

    public ScheduleService(ScheduleRepo scheduleRepo, EmployeeRepo employeeRepo, TimePeriodRepo timePeriodRepo) {
        this.scheduleRepo = scheduleRepo;
        this.employeeRepo = employeeRepo;
        this.timePeriodRepo = timePeriodRepo;
    }

    public ScheduleDTO addSchedule(ScheduleDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDate(scheduleDTO.getDate());
        newSchedule.setEmployee(employeeRepo.findByLogin(scheduleDTO.getLogin()).get());
        newSchedule.setTimePeriod(timePeriodRepo.findByShift(scheduleDTO.getShift()).get());
        scheduleRepo.save(newSchedule);
        return scheduleDTO;
    }

    public List<EmployeeScheduleRequestDTO> getUserScheduleByDate(EmployeeScheduleRequestDTO employeeScheduleRequestDTO) {
        String userLogin = employeeScheduleRequestDTO.getLogin();
        LocalDate startDate = employeeScheduleRequestDTO.getStart();
        LocalDate endDate = employeeScheduleRequestDTO.getEnd();
        Shift shift;

//        List<Schedule> resSchedule = new ArrayList<>();
        List<EmployeeScheduleRequestDTO> employeeScheduleRequestDTOS = new ArrayList<>();
        for (Schedule schedule : scheduleRepo.findAll()) {
            EmployeeScheduleRequestDTO employeeScheduleRequestDTO1 = new EmployeeScheduleRequestDTO();
            boolean startCheck = schedule.getDate().getDayOfYear() >= startDate.getDayOfYear();
            boolean loginCheck = schedule.getEmployee().getLogin().equals(userLogin);
            if (startCheck && (schedule.getDate().getDayOfYear() <= endDate.getDayOfYear()) && loginCheck) {
//                resSchedule.add(schedule);
                shift = timePeriodRepo.getOne(schedule.getTimePeriod().getId()).getShift();
                employeeScheduleRequestDTO1.setLogin(userLogin);
                employeeScheduleRequestDTO1.setStart(schedule.getDate());
                employeeScheduleRequestDTO1.setShift(shift);
                employeeScheduleRequestDTO1.setEnd(null);
                employeeScheduleRequestDTOS.add(employeeScheduleRequestDTO1);
            }
        }
        return employeeScheduleRequestDTOS;
    }
}