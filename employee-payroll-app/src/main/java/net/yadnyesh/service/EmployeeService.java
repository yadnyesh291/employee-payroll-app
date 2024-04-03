package net.yadnyesh.service;

import net.yadnyesh.model.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee emp);
    boolean checkEmail(String email);
    boolean authenticate(String email, String password);
    void update(Employee emp);
    Employee findByEmail(String email);
    Employee getEmployeeData(int employeeId); // Define the method here
}
