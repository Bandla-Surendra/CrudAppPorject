package com.example.springbootjpacrudexample.repository;

import com.example.springbootjpacrudexample.model.Employee1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo1 extends JpaRepository<Employee1,Long> {
}
