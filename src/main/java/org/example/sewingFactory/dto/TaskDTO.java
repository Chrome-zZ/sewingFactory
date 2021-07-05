package org.example.sewingFactory.dto;

import lombok.Data;
import org.example.sewingFactory.model.TaskType;

import java.time.LocalDate;

@Data
public class TaskDTO {
    String name;
    TaskType type;
    LocalDate deadline;
    String login;
}
