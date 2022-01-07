package com.example.employeedetails.Repository;

import com.example.employeedetails.Entities.Employeelogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeloginRepository extends JpaRepository<Employeelogin,Long> {
}
