package net.yadnyesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import net.yadnyesh.model.Employee;
import net.yadnyesh.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Retrieve employeeId from the session attribute
        int employeeId = (int) session.getAttribute("employeeId");
        Employee employee = employeeService.getEmployeeData(employeeId);
        model.addAttribute("employee", employee);
        return "employee/home";
    }

}
