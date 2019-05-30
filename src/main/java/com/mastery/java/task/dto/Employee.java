package com.mastery.java.task.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private Gender gender;
    private Date dateOfBirth;
    private Long employeeId;

    public Employee(){}

    public Employee(String firstName, String lastName, Long departmentId, String jobTitle,
                    Gender gender, Date dateOfBirth, Long employeeId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.employeeId = employeeId;
    }

    public Employee(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        if(!s1.isEmpty()){
            this.firstName = s1;
        }
        if(!s2.isEmpty()){
            this.lastName = s2;
        }
        if(!s3.isEmpty()){
            this.departmentId = Long.valueOf(s3);
        }
        if(!s4.isEmpty()){
            this.jobTitle = s4;
        }
        if(!s5.isEmpty()){
            if(s5.equals("male")) {
                this.gender = Gender.MALE;
            }else{
                this.gender = Gender.FEMALE;
            }
        }
        if(!s6.isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.dateOfBirth = format.parse(s6);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!s7.isEmpty()){
            this.employeeId = Long.valueOf(s7);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }

    public boolean isEmpty(){
        if(firstName == null && lastName == null && departmentId == null && jobTitle == null &&
        gender == null && dateOfBirth == null && employeeId == null) {
            return true;
        }
        return false;
    }

    public Employee setRandom(){
        Random r = new Random();
        String[] first_name = {"emily", "jack", "harry"};
        String[]  last_name = {"smith", "johnson", "williams"};
        String[] job_title = {"junior", "middle", "senior"};
        String[] gender = {"male", "female"};
        String[] strLines = {"", "", "", "", "", "", ""};
        strLines[0] = first_name[r.nextInt(3)];
        strLines[1] = last_name[r.nextInt(3)];
        strLines[2] = String.valueOf(r.nextInt(3) + 12);
        strLines[3] = job_title[r.nextInt(3)];
        strLines[4] = gender[r.nextInt(2)];
        strLines[5] = (r.nextInt(27) + 1) + "." + (r.nextInt(11) + 1) + "." + (r.nextInt(10) + 1985);
        Employee emp = new Employee(strLines[0], strLines[1], strLines[2], strLines[3], strLines[4], strLines[5], strLines[6]);
        return emp;
     }
}
