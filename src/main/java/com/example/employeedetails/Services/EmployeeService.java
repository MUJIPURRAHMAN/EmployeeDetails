package com.example.employeedetails.Services;

import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.ELException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee createEmployee(Employee employee) {
        Employee emp = new Employee();
        emp.setName(employee.getName());
        emp.setDob(employee.getDob());
        emp.setDoj(employee.getDoj());
        emp.setSalary(employee.getSalary());
        Employee employee1 = employeeRepository.save(emp);
        return employee1;
    }

    public Employee getEmployeeById(Long id)throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new Exception("id not found"));
        System.out.println(employee + "||||||||||||||||||||");
        return employee;
    }

    public String deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
        return "Record Deleted Successfully";
    }

    public Object updateEmployeeById(Long id, Employee employee)throws Exception {

        Employee employee1 = employeeRepository.findById(id).orElseThrow(()->new ELException("id not found"));
        Employee employee2;
            if (employee1.getId() == null) {
                employee1.setName(employee1.getName());
                employee1.setDob(employee1.getDob());
                employee1.setDoj(employee1.getDoj());
                employee1.setSalary(employee1.getSalary());
                employee2 = employeeRepository.save(employee1);
            }else{
                employee1.setName(employee.getName());
                employee1.setDob(employee.getDob());
                employee1.setDoj(employee.getDoj());
                employee1.setSalary(employee.getSalary());
                employee2 = employeeRepository.save(employee1);
            }

        return employee2;
    }

    public List<Employee> getSalary() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<Employee> employeeList1 = employeeList.stream().filter(employee -> employee.getSalary() > 20000).collect(Collectors.toList());
        return employeeList1;
    }

}
