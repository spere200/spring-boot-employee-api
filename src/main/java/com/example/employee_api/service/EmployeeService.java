package com.example.employee_api.service;

import com.example.employee_api.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(int id, Employee employee);
    Employee deleteById(int id);
    Employee patch(int id, Map<String, Object> payload);
}
