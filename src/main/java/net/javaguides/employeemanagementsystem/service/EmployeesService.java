package net.javaguides.employeemanagementsystem.service;

import net.javaguides.employeemanagementsystem.entity.Employees;
import net.javaguides.employeemanagementsystem.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

     private final EmployeesRepository employeesRepository;

     @Autowired
     public EmployeesService(EmployeesRepository employeesRepository) {
          this.employeesRepository = employeesRepository;
     }

     public List<Employees> getAllEmployees() {
          return employeesRepository.findAll();
     }

     public Optional<Employees> getEmployeeById(Long id) {
          return employeesRepository.findById(id);
     }

     public Employees saveEmployee(Employees employee) {
          return employeesRepository.save(employee);
     }

     public Employees updateEmployee(Long id, Employees employeeDetails) {
          Employees employee = employeesRepository.findById(id)
                  .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));

          employee.setLastName(employeeDetails.getLastName());
          employee.setFirstName(employeeDetails.getFirstName());
          employee.setMiddleName(employeeDetails.getMiddleName());
          employee.setEmail(employeeDetails.getEmail());
          employee.setPhoneNumber(employeeDetails.getPhoneNumber());
          employee.setPosition(employeeDetails.getPosition());

          return employeesRepository.save(employee);
     }

     public void deleteEmployee(Long id) {
          employeesRepository.deleteById(id);
     }
}