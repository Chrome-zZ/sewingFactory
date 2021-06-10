package org.example.sewingFactory.dto;

import lombok.Data;
import org.example.sewingFactory.model.Shift;

import java.time.LocalDate;

@Data
public class EmployeeScheduleRequestDTO {
    String login;
    LocalDate start;
    LocalDate end;
    Shift shift;
}
