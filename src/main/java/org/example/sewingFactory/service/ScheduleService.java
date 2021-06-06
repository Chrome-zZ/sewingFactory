package org.example.sewingFactory.service;

import org.example.sewingFactory.dto.EmployeeScheduleRequestDTO;
import org.example.sewingFactory.dto.ScheduleDTO;
import org.example.sewingFactory.model.Schedule;
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

    public Iterable<Schedule> getSchedules() {
        return scheduleRepo.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepo.getOne(id);
    }

    public Iterable<Schedule> getUserScheduleByDate(EmployeeScheduleRequestDTO employeeScheduleRequestDTO) {
        String userLogin = employeeScheduleRequestDTO.getLogin();
        LocalDate startDate = employeeScheduleRequestDTO.getStart();
        LocalDate endDate = employeeScheduleRequestDTO.getEnd();

        List<Schedule> resSchedule = new ArrayList<>();
        for (Schedule schedule : scheduleRepo.findAll()) {
            if ((schedule.getDate().getDayOfYear() >= startDate.getDayOfYear()) &&
                    (schedule.getDate().getDayOfYear() <= endDate.getDayOfYear()) &&
                    (schedule.getEmployee().getLogin().equals(userLogin))) {
                resSchedule.add(schedule);
            }
        }
        return resSchedule;
    }

//    public Schedule getScheduleByUserName(String userName) {
//        Schedule scheduleByName = null;
//        for (Schedule schedule : getSchedules()) {
//            if (schedule.getUsers().stream().findFirst().equals(userName)) {
//                scheduleByName = schedule;
//            }
//        }
//        return scheduleByName;
//    }
}