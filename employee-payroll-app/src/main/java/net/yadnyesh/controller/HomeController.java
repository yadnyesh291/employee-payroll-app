package net.yadnyesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.yadnyesh.model.Employee;
import net.yadnyesh.service.EmployeeService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/signin")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, ModelAndView model, Model m) {
        if(email == null) {
            return "redirect:/";
        }
        boolean isAuthenticated = employeeService.authenticate(email, password);
        if (isAuthenticated) {
            session.setAttribute("email", email);
            Employee employee = employeeService.findByEmail(email);
            m.addAttribute("employee", employee);
            session.setAttribute("employee", employee);
            return "redirect:/employee/home";
        } else {
            session.setAttribute("msg", "Invalid email or password");
            return "redirect:/signin";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee()); // Add an empty employee object to the model
        return "register";
    }

    @PostMapping("/register")
    public String registerEmployee(Employee employee) {
        // Logic to register the employee (e.g., save to database)
        employeeService.createEmployee(employee);
        return "redirect:/login"; // Redirect to the login page after registration
    }

    @GetMapping("/homee")
    public String showHomee() {
        return "homee";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute Employee employee, HttpSession session) {
        boolean f = employeeService.checkEmail(employee.getEmail());
        if(f) {
            session.setAttribute("msg", "User Already Exist");
            return "redirect:/register";
        }
        employeeService.createEmployee(employee);
        session.setAttribute("msg", "Employee Registered Successfully");
        return "redirect:/register";
    }

    @GetMapping("/clearMessage")
    public String clearMessage(HttpSession session) {
        session.removeAttribute("msg");
        return "redirect:/register";
    }
}
