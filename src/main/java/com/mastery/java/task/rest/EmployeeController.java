package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public String getMethod(@RequestParam(value = "first_name", defaultValue = "") String firstName,
                            @RequestParam(value = "last_name", defaultValue = "") String lastName,
                            @RequestParam(value = "department_id", defaultValue = "") String departmentId,
                            @RequestParam(value = "job_title", defaultValue = "") String jobTitle,
                            @RequestParam(value = "gender", defaultValue = "") String gender,
                            @RequestParam(value = "date_of_birth", defaultValue = "") String dateOfBirth,
                            @RequestParam(value = "employee_id", defaultValue = "") String employerId) {
        String str = "";
        Employee emp = new Employee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth, employerId);
        List<Employee> employeeList = empService.findEmployeeByAny(emp);
        if(!employeeList.isEmpty()) {
            str = createStringUnswer(employeeList);
        }
        return  "founded: " + "\n" + str;
    }

    @DeleteMapping()
    public String deleteMethod(@RequestParam(value = "first_name", defaultValue = "") String firstName,
                               @RequestParam(value = "last_name", defaultValue = "") String lastName,
                               @RequestParam(value = "department_id", defaultValue = "") String departmentId,
                               @RequestParam(value = "job_title", defaultValue = "") String jobTitle,
                               @RequestParam(value = "gender", defaultValue = "") String gender,
                               @RequestParam(value = "date_of_birth", defaultValue = "") String dateOfBirth,
                               @RequestParam(value = "employee_id", defaultValue = "") String employerId) {
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
    public String putMethod(@RequestParam(value = "first_name", defaultValue = "") String firstName,
                            @RequestParam(value = "last_name", defaultValue = "") String lastName,
                            @RequestParam(value = "department_id", defaultValue = "") String departmentId,
                            @RequestParam(value = "job_title", defaultValue = "") String jobTitle,
                            @RequestParam(value = "gender", defaultValue = "") String gender,
                            @RequestParam(value = "date_of_birth", defaultValue = "") String dateOfBirth,
                            @RequestParam(value = "employee_id", defaultValue = "") String employerId) {
        String str;
        Employee emp;
        if (firstName.equals("") && lastName.equals("") && departmentId.equals("") &&
        jobTitle.equals("") && gender.equals("") && dateOfBirth.equals("") && employerId.equals("")){
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
    public String postMethod(@RequestParam(value = "first_name", defaultValue = "") String firstName,
                             @RequestParam(value = "last_name", defaultValue = "") String lastName,
                             @RequestParam(value = "department_id", defaultValue = "") String departmentId,
                             @RequestParam(value = "job_title", defaultValue = "") String jobTitle,
                             @RequestParam(value = "gender", defaultValue = "") String gender,
                             @RequestParam(value = "date_of_birth", defaultValue = "") String dateOfBirth,
                             @RequestParam(value = "employee_id", defaultValue = "") String employerId) {
        String str = "";
        Employee emp1 = new Employee(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth, "");
        Employee emp2 = new Employee("", "", "", "", "", "", employerId);
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