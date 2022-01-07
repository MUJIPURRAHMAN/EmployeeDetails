package com.example.employeedetails.Controller;

import com.example.employeedetails.DTO.EmpRequest;
import com.example.employeedetails.Services.EmployeesignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeesignupController {
    @Autowired
    EmployeesignupService userService;

    @PostMapping("/createuser")
    public Object createUser(@RequestBody EmpRequest user){
        Object userdetails=userService.createUser(user);
        return userdetails;
    }
}
