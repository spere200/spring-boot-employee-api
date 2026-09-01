package com.example.employee_api.service;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.repository.EmployeeDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    // inject DAOs
    private final EmployeeDAO employeeDAO;
    private final JsonMapper jsonMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, JsonMapper jsonMapper){
        this.employeeDAO = employeeDAO;
        this.jsonMapper = jsonMapper;
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
        if(employee.getId() != null){
            throw new RuntimeException("The \"id\" field is auto-generated. Try again without \"id\".");
        }
        // no longer need to set id to 0, now using persists, id field is explicitly not allowed
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee update(int id, Employee employee){
        Employee dbEmployee = employeeDAO.findById(id);

        if(dbEmployee == null){
            throw new RuntimeException("Employee id not found - " + id);
        }

        employee.setId(id);

        return employeeDAO.update(employee);
    }

    @Override
    @Transactional
    public Employee deleteById(int id) {
        Employee employee = this.employeeDAO.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + id);
        }

        this.employeeDAO.delete(employee);

        return employee;
    }

    @Override
    @Transactional
    public Employee patch(int id, Map<String, Object> payload){
        Employee employee = this.employeeDAO.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + id);
        }

        // throw error if request body contains id
        if(payload.containsKey("id")){
            throw new RuntimeException("Employee id cannot be changed. Please resubmit without id.");
        }

        // apply partial updates to the existing employee object using JsonMapper
        Employee patchedEmployee = this.jsonMapper.updateValue(employee, payload);
        return this.employeeDAO.save(patchedEmployee);
    }
}
