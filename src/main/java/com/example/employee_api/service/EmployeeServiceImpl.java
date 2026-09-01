package com.example.employee_api.service;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.repository.EmployeeDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    // inject DAOs
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        // force an id of 0 just in case the post request is sent with
        // anything else to force an add instead of an update
        employee.setId(0);
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee update(Employee employee){
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public String deleteById(int id) {
        Employee employee = this.employeeDAO.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + id);
        }

        this.employeeDAO.deleteById(employee);

        return employee.toString();
    }
}
