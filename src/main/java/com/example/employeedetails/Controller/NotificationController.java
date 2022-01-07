package com.example.employeedetails.Controller;

import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Response.GenericResponse;
import com.example.employeedetails.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/sendemail/{id}")
    public Object sendMail(@PathVariable("id") Long id){
        GenericResponse response=new GenericResponse();

        /*Employee employee = notificationService.getEmployeeById(id);
        System.out.println(employee+">>>>>>>>>");

        String email= employee.getEmployeelogin().getEmail();
        System.out.println(email+">>>>>>>>>++++++++++=");*/
        notificationService.sendMail();

        return "email sent successfully";


    }
}
