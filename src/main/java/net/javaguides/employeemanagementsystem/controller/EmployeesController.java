package net.javaguides.employeemanagementsystem.controller;

import net.javaguides.employeemanagementsystem.entity.Employees;
import net.javaguides.employeemanagementsystem.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employees());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employees employee, RedirectAttributes redirectAttributes) {
        employeesService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        return "redirect:/api/employees";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Employees employee = employeesService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employees employee, RedirectAttributes redirectAttributes) {
        employeesService.updateEmployee(id, employee);
        redirectAttributes.addFlashAttribute("message", "Employee updated successfully!");
        return "redirect:/api/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        employeesService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("message", "Employee deleted successfully!");
        return "redirect:/api/employees";
    }

    @GetMapping
    public String listEmployees(Model model) {
        List<Employees> employees = employeesService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }
}