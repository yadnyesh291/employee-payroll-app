package net.yadnyesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.yadnyesh.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmail(String email);
    Employee findByEmail(String email);
}
