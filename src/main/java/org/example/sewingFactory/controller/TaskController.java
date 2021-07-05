package org.example.sewingFactory.controller;

import org.example.sewingFactory.dto.TaskDTO;
import org.example.sewingFactory.model.Task;
import org.example.sewingFactory.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasAuthority('read')")
    public List<Task> getTasksByLogin(@PathVariable String login) {
        return taskService.getTasksByLogin(login);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public Task addTask(@RequestBody TaskDTO taskDTO) throws Exception {
           return taskService.addTask(taskDTO);
    }

    @PostMapping("/complete/{id}")
    @PreAuthorize("hasAuthority('read')")
    public void addCompletionPoint(@PathVariable Long id) {
        taskService.completeTask(id);
    }
}
