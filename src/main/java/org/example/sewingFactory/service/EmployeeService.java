package org.example.sewingFactory.service;


import org.example.sewingFactory.model.Employee;
import org.example.sewingFactory.model.Status;
import org.example.sewingFactory.repo.EmployeeRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ScheduleService scheduleService;

    public EmployeeService(EmployeeRepo employeeRepo, ScheduleService scheduleService) {
        this.employeeRepo = employeeRepo;
        this.scheduleService = scheduleService;
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    public List<Employee> getUsers() {
        return employeeRepo.findAll();
    }

    public Employee addUser(Employee employee) {

        Employee newU = new Employee();
        newU.setLogin(employee.getLogin());
        newU.setRole(employee.getRole());
        newU.setPassword(passwordEncoder().encode(employee.getPassword()));
        newU.setName(employee.getName());
        newU.setSurname(employee.getSurname());
        newU.setStatus(Status.ACTIVE);

        return employeeRepo.save(newU);
    }

    public Employee getUserById(Long id) {

        String login = employeeRepo.findById(id).get().getLogin();

        return employeeRepo.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }

//    public String getUserName(String login) {
//        String firstName = userRepo.findByLogin(login).orElse(null).getName();
//        String lastName = userRepo.findByLogin(login).orElse(null).getSurname();
//        return firstName + " " + lastName;
//    }

    public void deleteUser(Long id) {
        employeeRepo.deleteById(id);
    }
}
