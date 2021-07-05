package org.example.sewingFactory.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.example.sewingFactory.dto.TaskDTO;
import org.example.sewingFactory.model.Employee;
import org.example.sewingFactory.model.Task;
import org.example.sewingFactory.repo.EmployeeRepo;
import org.example.sewingFactory.repo.TaskRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final EmployeeRepo employeeRepo;

    public TaskService(TaskRepo taskRepo, EmployeeRepo employeeRepo) {
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<Task> getTasksByLogin(String login) {
        List<Task> resTasks = new ArrayList<>();
//        resTasks = taskRepo.findAll().stream()
//                .filter(task -> task.getEmployee().getLogin().equals(login))
//                .collect(Collectors.toList());
        for (Task task : taskRepo.findAll()) {
            if (task.getEmployee().getLogin().equals(login)) {
                resTasks.add(task);
            }
        }
        return resTasks;
    }

    public Task addTask(TaskDTO taskDTO) throws Exception {
        Task newTask = new Task();
        newTask.setName(taskDTO.getName());
        newTask.setType(taskDTO.getType());
        newTask.setDeadline(taskDTO.getDeadline());
        if (employeeRepo.findByLogin(taskDTO.getLogin()).get().getRole().toString().contains("CUT")
                && taskDTO.getType().toString().equals("CUT")) {
            newTask.setEmployee(employeeRepo.findByLogin(taskDTO.getLogin()).get());
        } else if (employeeRepo.findByLogin(taskDTO.getLogin()).get().getRole().toString().contains("SEAM")
                && (taskDTO.getType().toString().equals("SEWING")
                || taskDTO.getType().toString().equals("QUALITY_CONTROL"))) {
            newTask.setEmployee(employeeRepo.findByLogin(taskDTO.getLogin()).get());
        } else if (employeeRepo.findByLogin(taskDTO.getLogin()).get().getRole().toString().contains("DES")
                && (taskDTO.getType().toString().equals("DESIGN")
                || taskDTO.getType().toString().equals("PATTERN_MAKING"))) {
        newTask.setEmployee(employeeRepo.findByLogin(taskDTO.getLogin()).get());
    }
        else throw new Exception("Task's type mismatch");
        return taskRepo.save(newTask);
    }

    public void completeTask(Long id) {
        Task changedTask = taskRepo.getOne(id);
        changedTask.setComplete(Boolean.TRUE);
        taskRepo.save(changedTask);
    }
}
