package com.example.employeedetails.Controller;

import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Response.GenericResponse;
import com.example.employeedetails.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public String createEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.createEmployee(employee);
        return "Employee Created Successfully";
    }

    @GetMapping("/employee")
    public List<Employee> getEmployees() {
        List<Employee> employee = employeeService.getEmployees();
        return employee;
    }

    @GetMapping("/employee/{id}")
    public Object getEmployeeById(@PathVariable("id") Long id) {
        GenericResponse response = new GenericResponse();
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return employee;
        } catch (Exception e) {
            response.setStatus("BAD REQUEST");
            response.setError("BAD REQUEST");
            response.setMessage(e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employee/{id}")
    public Object deleteEmployeeById(@PathVariable("id") Long id) {
        Object employee = employeeService.deleteEmployeeById(id);
        return employee;
    }

    @PutMapping("/employee/{id}")
    public Object updateEmployeeById(@RequestBody Employee employee, @PathVariable("id") Long id) throws Exception {
        GenericResponse response = new GenericResponse();
        try {
            Object employee1 = employeeService.updateEmployeeById(id, employee);
            return "updated successfully";
        } catch (Exception e) {
            response.setStatus("BAD REQUEST");
            response.setError("BAD REQUEST");
            response.setMessage(e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/salaryfilter")
    public Object getSalary() {
        List<Employee> emp = employeeService.getSalary();
        return emp;
    }



}
