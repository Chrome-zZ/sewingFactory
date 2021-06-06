package org.example.sewingFactory.controller;

import org.example.sewingFactory.model.Employee;
import org.example.sewingFactory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('write')")
    public List<Employee> getAll() {
        return employeeService.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public Employee getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('read')") //idk if it's necessary
    public Employee addUser(@RequestBody Employee employee) {
        return employeeService.addUser(employee);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    public void deleteUser(@PathVariable Long id) {
        employeeService.deleteUser(id);
    }
}