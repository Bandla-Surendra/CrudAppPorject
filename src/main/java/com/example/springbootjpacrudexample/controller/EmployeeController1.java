package com.example.springbootjpacrudexample.controller;

import com.example.springbootjpacrudexample.exception.ResourceNotFoundException;
import com.example.springbootjpacrudexample.model.Employee;
import com.example.springbootjpacrudexample.model.Employee1;
import com.example.springbootjpacrudexample.repository.EmployeeRepo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class EmployeeController1 {
    @Autowired
    EmployeeRepo1 employeeRepo1;
    @GetMapping("/employees1")
    public List<Employee1> getAllEmployees() {
        return employeeRepo1.findAll();
    }

    @GetMapping("/employees1/{id}")
    public ResponseEntity< Employee1> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee1 employee = employeeRepo1.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    @PostMapping("/employees1")
    public Employee1 createEmployee( @RequestBody Employee1 employee) {
        return employeeRepo1.save(employee);
    }

    @PutMapping("/employees1/{id}")
    public ResponseEntity < Employee1 > updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                      @RequestBody Employee1 employeeDetails) throws ResourceNotFoundException {
        Employee1 employee =employeeRepo1.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmpNum(employeeDetails.getEmpNum());
        employee.setEmpName(employeeDetails.getEmpName());
        employee.setEmpAge(employeeDetails.getEmpAge());
        employee.setEmpDeptNum(employeeDetails.getEmpDeptNum());
        employee.setEmpGender(employee.getEmpGender());
        employee.setEmpJob(employeeDetails.getEmpJob());
        employee.setEmpSalary(employeeDetails.getEmpSalary());

        final Employee1 updatedEmployee = employeeRepo1.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/employees1/{id}")
    public Map< String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee1 employee = employeeRepo1.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepo1.delete(employee);
        Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
