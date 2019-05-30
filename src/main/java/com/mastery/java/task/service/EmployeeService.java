package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeService {
    void insert(Employee emp);
    void insertBatch(List<Employee> employees);
    void loadAllEmployee();
    void getEmployeeById(long emp_id);
    void getEmployeeNameById(long emp_id);
    String getEmployeeNameById2(long emp_id);
    void getTotalNumberEmployee();
    void deleteEmployeeById(long emp_id);
    List<Employee> findEmployeeByAny(Employee emp);
    void update(Employee emp1, Employee emp2);
}
