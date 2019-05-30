package com.mastery.java.task;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SimplewebapApplication implements CommandLineRunner {

    @Autowired
    EmployeeService empService;

    public static void main(String[] args) {
        SpringApplication.run(SimplewebapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
        empService.insert(createRandomEmployeer());
        empService.insert(createRandomEmployeer());
        empService.insert(createRandomEmployeer());
        empService.loadAllEmployee();

        // Employee 1
        Employee emp_1 = new Employee();
        emp_1.setFirstName("alex");
        emp_1.setLastName("smith");

        // Employee 2
        Employee emp_2 = new Employee();
        emp_2.setFirstName("pam");
        emp_2.setLastName("smith");

        // Employee 3
        Employee emp_3 = new Employee();
        emp_3.setFirstName("angel");
        emp_3.setLastName("smith");
        emp_3.setJobTitle("big boss");

        empService.insert(emp_3);

        Employee emp_7 = createRandomEmployeer();
        empService.insert(emp_7);

        List<Employee> empList = new ArrayList<Employee>();
        empList.add(emp_2);
        empList.add(emp_3);
        //empService.insertBatch(empList);

        empService.insert(emp_3);

        empService.loadAllEmployee();

        //empService.getEmployeeById(Long.valueOf(emp_id));

        //empService.getEmployeeNameById(Long.valueOf(emp_id));

        empService.getTotalNumberEmployee();

        List<Employee> emp_4 = empService.findEmployeeByAny(emp_3);

        empService.update(emp_2, emp_4.get(0));

        empService.loadAllEmployee();

        empService.deleteEmployeeById(emp_4.get(0).getEmployeeId());

        empService.getTotalNumberEmployee();

        empService.loadAllEmployee();

         */
    }
/*
    private Employee createRandomEmployeer(){
        Employee emp = new Employee();
        Random r = new Random();
        String[] first_name = {"emily", "jack", "harry"};
        String[]  last_name = {"smith", "johnson", "williams"};
        long[] department_id = {12, 13, 14};
        String[] job_title = {"junior", "middle", "senior"};
        Gender[] gender = {Gender.MALE, Gender.FEMALE};
        emp.setFirstName(first_name[r.nextInt(3)]);
        emp.setLastName(last_name[r.nextInt(3)]);
        emp.setDepartmentId(department_id[r.nextInt(3)]);
        emp.setJobTitle(job_title[r.nextInt(3)]);
        //emp.setGender(gender[r.nextInt(2)]);
        String str = (r.nextInt(27) + 1) + "-" + (r.nextInt(11) + 1) + "-" + (r.nextInt(10) + 1985);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //emp.setDateOfBirth(date);
        return emp;
    }

 */
}
