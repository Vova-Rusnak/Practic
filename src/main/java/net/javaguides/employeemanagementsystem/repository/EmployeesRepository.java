package net.javaguides.employeemanagementsystem.repository;

import net.javaguides.employeemanagementsystem.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
}