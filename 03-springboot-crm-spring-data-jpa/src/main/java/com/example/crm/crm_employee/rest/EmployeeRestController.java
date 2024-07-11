package com.example.crm.crm_employee.rest;

import com.example.crm.crm_employee.entity.Employee;
import com.example.crm.crm_employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    // Quick and dirty: inject employee DAO

    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }


    //expose "/employees" and returns the list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return  this.employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){
        Employee employeeRec = this.employeeService.findById(employeeId);
        if(employeeRec == null){
            throw new RuntimeException("Employee id not found "+employeeId);
        }
        return  employeeRec;
    }

    @PostMapping("/employees")
    public Employee createNewEmployee(@RequestBody Employee employee){
        employee.setId(0);//This is to force a save item instead of updating
        Employee employeeRec = this.employeeService.save(employee);
        if(employeeRec == null){
            throw new RuntimeException("New Employee could not be made");
        }
        return  employeeRec;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        //employee.setId(0);//This is to force a save item instead of updating
        Employee employeeRec = this.employeeService.save(employee);
        if(employeeRec == null){
            throw new RuntimeException("New Employee could not be made");
        }
        return  employeeRec;
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable int employeeId){
        Employee employeeRec = this.employeeService.findById(employeeId);
        if(employeeRec == null){
            throw new RuntimeException("Employee id not found "+employeeId);
        }
        this.employeeService.deleteById(employeeId);
    }
}
