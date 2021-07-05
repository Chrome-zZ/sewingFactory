package org.example.sewingFactory.service;


import org.example.sewingFactory.dto.EmployeeDTO;
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

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
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

    public EmployeeDTO getUserByLogin(String login) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = employeeRepo.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        employeeDTO.setLogin(employee.getLogin());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setRole(employee.getRole().toString());
        return employeeDTO;
    }

    public void deleteUser(Long id) {
        employeeRepo.deleteById(id);
    }
}
