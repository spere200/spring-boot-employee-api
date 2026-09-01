package com.example.employee_api.repository;

import com.example.employee_api.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(Employee employee);
}
