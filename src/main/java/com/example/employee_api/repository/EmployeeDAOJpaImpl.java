package com.example.employee_api.repository;

import com.example.employee_api.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    // set up field for entity manager
    private final EntityManager em;

    // set up constructor to inject entity manager
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> query = em.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(Integer id) {
        return em.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        return em.merge(employee);
    }

    @Override
    public void delete(Employee employee) {
        em.remove(employee);
    }
}
