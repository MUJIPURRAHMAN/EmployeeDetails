package com.example.employeedetails.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFileRequest {

    private String name;
    private String dob;
    private String doj;
    private String mobileno;
    private String salary;

}
