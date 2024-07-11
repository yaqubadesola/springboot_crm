package com.example.crm.crm_employee.dao;

import com.example.crm.crm_employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//DAO manages database access by implementing all the methods specified in the interfaces
@Repository//Define a Data Access Object (DAO) that interacts with the database.
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    //define the field for Entity Manager
    private EntityManager entityManager;

    //Set up the contructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll(){
        //create a query
        TypedQuery<Employee> theQry = entityManager.createQuery("from Employee", Employee.class);

        // execute qry & get result list
        List<Employee> employees = theQry.getResultList();
        //return the result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employeeRec = entityManager.find(Employee.class,id);
        return employeeRec;
    }

    @Override
    public Employee save(Employee newEmployee) {
        Employee theEmployee = entityManager.merge(newEmployee);
        return theEmployee;
    }

    @Override
    public void deleteById(int id) {
        Employee employeeRec = this.entityManager.find(Employee.class,id);
        this.entityManager.remove(employeeRec);
    }
}
