package com.example.employeedetails.Services;

import com.example.employeedetails.DTO.EmpRequest;
import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Entities.Employeelogin;
import com.example.employeedetails.Repository.EmployeeRepository;
import com.example.employeedetails.Repository.EmployeeloginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeesignupService {
    @Autowired
    EmployeeloginRepository employeeloginRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Object createUser(EmpRequest user){

        Employeelogin employeelogin=new Employeelogin();
        employeelogin.setEmail(user.getEmployeelogin().getEmail());
        employeelogin.setPassword(user.getEmployeelogin().getPassword());
        Employeelogin employeelogin1=employeeloginRepository.save(employeelogin);


        Employee employee=new Employee();
        employee.setName(user.getName());
        employee.setDob(user.getDob());
        employee.setDoj(user.getDoj());
        employee.setMobileno(user.getMobileno());
        employee.setSalary(user.getSalary());
        employee.setEmployeelogin(employeelogin1);
        Employee employee1=employeeRepository.save(employee);
        System.out.println(employee1+"::::::::::::::;");




        return "user created Successfully";
    }
}
