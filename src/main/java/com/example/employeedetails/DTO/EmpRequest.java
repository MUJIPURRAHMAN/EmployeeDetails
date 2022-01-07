package com.example.employeedetails.DTO;

import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Entities.Employeelogin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpRequest {
    private String name;
    private String dob;
    private String doj;
    private String mobileno;
    private Long salary;

    private Employeelogin employeelogin;
}
