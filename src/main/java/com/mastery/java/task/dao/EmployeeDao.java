package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import java.util.List;

public interface EmployeeDao {
    //add new employee
    void insert(Employee emp);
    void insertBatch(List<Employee> employees);
    List<Employee> loadAllEmployee();
    Employee findEmployeeById(long empId);
    String findNameById(long empId);
    int getTotalNumberEmployee();
    //delete employee
    void deleteEmployeeById(long empId);
    //find employee
    List<Employee> findEmployeeByAny(Employee emp);
    //update employee
    void updateEmployee(Employee emp1, Employee emp2);
}
