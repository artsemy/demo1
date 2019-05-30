package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired EmployeeDao employeeDao;

    @Override
    public void insert(Employee emp) {
        employeeDao.insert(emp);
        System.out.println("inserted: " + emp.toString());
    }

    @Override
    public void insertBatch(List<Employee> employees) {
        employeeDao.insertBatch(employees);
    }

    @Override
    public void loadAllEmployee() {
        List<Employee> employeeList = employeeDao.loadAllEmployee();
        for(Employee emp : employeeList){
            System.out.println(emp.toString());
        }
    }

    @Override
    public void getEmployeeById(long emp_id) {
        Employee emp = employeeDao.findEmployeeById(emp_id);
        System.out.println("get by id: " + emp.toString());
    }

    @Override
    public void getEmployeeNameById(long emp_id) {
        String name = employeeDao.findNameById(emp_id);
        System.out.println("employee's name is " +name);
    }

    @Override
    public String getEmployeeNameById2(long emp_id) {
        String name = employeeDao.findNameById(emp_id);
        System.out.println("employee's name is " +name);
        return employeeDao.findNameById(emp_id);
    }

    @Override
    public void getTotalNumberEmployee() {
        int total = employeeDao.getTotalNumberEmployee();
        System.out.println("total number employee is " + total);
    }

    @Override
    public void deleteEmployeeById(long emp_id) {
        employeeDao.deleteEmployeeById(emp_id);
        System.out.println(emp_id + " id employee was deleted");
    }

    @Override
    public List<Employee> findEmployeeByAny(Employee emp) {
        List<Employee> employeeList = employeeDao.findEmployeeByAny(emp);
        for(Employee emp2 : employeeList){
            System.out.println("was found: " + emp2.toString());
        }
        return employeeList;
    }

    @Override
    public void update(Employee emp1, Employee emp2) {
        employeeDao.updateEmployee(emp1, emp2);
        System.out.println("was updated " + emp2.toString());
    }
}
