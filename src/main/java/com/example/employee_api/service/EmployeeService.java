package com.example.employee_api.service;

import com.example.employee_api.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Integer id);
    Employee save(Employee employee);
    Employee update(Integer id, Employee employee);
    Employee deleteById(Integer id);
    Employee patch(Integer id, Map<String, Object> payload);
}
