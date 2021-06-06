package org.example.sewingFactory.dto;

import lombok.Data;
import org.example.sewingFactory.model.Shift;

import java.time.LocalDate;

@Data
public class ScheduleDTO {
    LocalDate date;
    Shift shift;
    String login;
}
