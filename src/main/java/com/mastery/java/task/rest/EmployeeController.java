package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService empService;
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployee(@PathVariable("emp_id") long empId,
                                @PathVariable("First_name") String firstName,
                                @PathVariable("Last_name") String lastName,
                                @PathVariable("job_title") String jobTitle) {
        Employee emp = new Employee();
        emp.setEmployeeId(empId);
        return empService.findEmployeeByAny(emp).get(0);
    }
    */


    @GetMapping()
    public String getMethod(@RequestParam(value = "first_name", required=false) String firstName,
                            @RequestParam(value = "last_name", required=false) String lastName,
                            @RequestParam(value = "department_id", required=false) Long departmentId,
                            @RequestParam(value = "job_title", required=false) String jobTitle,
                            @RequestParam(value = "gender", required=false) Gender gender,
                            @RequestParam(value = "date_of_birth", required=false) Date dateOfBirth,
                            @RequestParam(value = "employee_id", required=false) Long employerId) {
        String str = "";
        Employee emp = new Employee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth, employerId);
        List<Employee> employeeList = empService.findEmployeeByAny(emp);
        if(!employeeList.isEmpty()) {
            str = createStringUnswer(employeeList);
        }
        return  "founded: " + "\n" + str;

    }

    @DeleteMapping()
    public String deleteMethod(@RequestParam(value = "first_name", required=false) String firstName,
                               @RequestParam(value = "last_name", required=false) String lastName,
                               @RequestParam(value = "department_id", required=false) Long departmentId,
                               @RequestParam(value = "job_title", required=false) String jobTitle,
                               @RequestParam(value = "gender", required=false) Gender gender,
                               @RequestParam(value = "date_of_birth", required=false) Date dateOfBirth,
                               @RequestParam(value = "employee_id", required=false) Long employerId) {
        String str = "";
        Employee emp = new Employee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth, employerId);
        List<Employee> employeeList = empService.findEmployeeByAny(emp);
        if(!employeeList.isEmpty()) {
            for(Employee emp2: employeeList){
                empService.deleteEmployeeById(emp2.getEmployeeId());
                str += emp2.getEmployeeId() + ", ";
            }
        }
        return "deleted id: " + str;
    }

    @PutMapping()
    public String putMethod(@RequestParam(value = "first_name", required=false) String firstName,
                            @RequestParam(value = "last_name", required=false) String lastName,
                            @RequestParam(value = "department_id", required=false) Long departmentId,
                            @RequestParam(value = "job_title", required=false) String jobTitle,
                            @RequestParam(value = "gender", required=false) Gender gender,
                            @RequestParam(value = "date_of_birth", required=false) Date dateOfBirth,
                            @RequestParam(value = "employee_id", required=false) Long employerId) {
        String str;
        Employee emp;
        if (firstName == null && lastName == null && departmentId == null &&
        jobTitle == null && gender == null && dateOfBirth == null && employerId == null){
            emp = new Employee().setRandom();
            empService.insert(emp);
            str = emp.getFirstName();
        } else {
            emp = new Employee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth, employerId);
            empService.insert(emp);
            str = emp.getFirstName();
        }
        return "add id: " + str;
    }

    @PostMapping()
    public String postMethod(@RequestParam(value = "first_name", required=false) String firstName,
                             @RequestParam(value = "last_name", required=false) String lastName,
                             @RequestParam(value = "department_id", required=false) Long departmentId,
                             @RequestParam(value = "job_title", required=false) String jobTitle,
                             @RequestParam(value = "gender", required=false) Gender gender,
                             @RequestParam(value = "date_of_birth", required=false) Date dateOfBirth,
                             @RequestParam(value = "employee_id", required=false) Long employerId) {
        String str = "";
        Employee emp1 = new Employee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth, null);
        Employee emp2 = new Employee(null, null, null, null, null, null, employerId);
        empService.update(emp1, emp2);
        str += employerId;
        return "updated: " + str;
    }

    private String createStringUnswer(List<Employee> list) {
        String str = "";
        for(Employee emp: list){
            str += emp + "\n";
        }
        return str;
    }
}