package net.yadnyesh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.yadnyesh.model.Employee;
import net.yadnyesh.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    public boolean checkEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public boolean authenticate(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        return employee != null && employee.getPassword().equals(password);
    }

    @Override
    public void update(Employee emp) {
        employeeRepository.save(emp);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    
    @Override
    public Employee getEmployeeData(int employeeId) {
        // Retrieve the employee with the specified ID from the repository
        return employeeRepository.findById(employeeId).orElse(null);
    }
}
