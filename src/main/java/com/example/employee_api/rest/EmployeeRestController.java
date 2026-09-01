package com.example.employee_api.rest;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    // inject employee service
    private final EmployeeService employeeService;


    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> getEmployees(){
        return this.employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id){
        return this.employeeService.findById(id);
    }

    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee){
        return this.employeeService.save(employee);
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee){
        return this.employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){
        return this.employeeService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> payload){
        return this.employeeService.patch(id, payload);
    }
}
