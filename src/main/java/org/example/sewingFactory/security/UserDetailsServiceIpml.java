package org.example.sewingFactory.security;

import org.example.sewingFactory.model.Employee;
import org.example.sewingFactory.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceIpml")
public class UserDetailsServiceIpml implements UserDetailsService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public UserDetailsServiceIpml(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(employee);
    }
}
