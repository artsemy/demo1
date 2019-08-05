package com.mastery.java.task.dao.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(Employee emp) {
        String sql = "insert into employee (first_name, last_name, department_id, job_title, gender, date_of_birth) " +
                "values (?, ?, ?, ?, ?, ?)" ;
        if(!emp.isEmpty()) {
            getJdbcTemplate().update(sql, new Object[]{emp.getFirstName(), emp.getLastName(), emp.getDepartmentId(),
                    emp.getJobTitle(), emp.getGender().toString(), emp.getDateOfBirth()});
        }
    }


    @Override
    public void insertBatch(List<Employee> employees) {

        String sql = "insert into employee (first_name, last_name) values (?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Employee employee = employees.get(i);
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
            }

            public int getBatchSize() {
                return employees.size();
            }
        });

    }

    @Override
    public List<Employee> loadAllEmployee() {
        Employee emp = new Employee();
        List<Employee> result = findEmployeeByAny(emp);
        return result;
    }

    @Override
    public Employee findEmployeeById(long empId) {

        String sql = "select* from employee where employee_id = ?";
        return (Employee) getJdbcTemplate().queryForObject(sql, new Object[]{empId}, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Employee emp = new Employee();
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                return emp;
            }
        });
    }

    @Override
    public String findNameById(long empId) {

        String sql = "select first_name from employee where employee_id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{empId}, String.class);
    }

    @Override
    public int getTotalNumberEmployee() {

        String sql = "select Count(*) from employee";
        int total = getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }

    @Override
    public void deleteEmployeeById(long empId) {
        String sql = "delete from employee where employee_id = ?";
        getJdbcTemplate().update(sql, new Object[]{empId});
    }

    @Override
    public List<Employee> findEmployeeByAny(Employee employee) {
        String sql = "select* from employee where ";
        sql = sql + createSqlRequest(employee);
        if(sql.equals("select* from employee where ")) {
            sql = "select * from employee";
        }
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Employee> result = new ArrayList<Employee>();
        for(Map<String, Object> row:rows){
            Employee emp = setEmployee(row);
            result.add(emp);
        }
        return result;
    }

    private Employee setEmployee(Map<String, Object> row){
        int id;
        long lId;
        String sGender;
        Employee emp = new Employee();
        emp.setFirstName((String) row.get("first_name"));
        emp.setLastName((String) row.get("last_name"));
        emp.setDateOfBirth((Date) row.get("date_of_birth"));
        if(row.get("department_id") != null) {
            id = (Integer) row.get("department_id");
            lId = Long.valueOf(String.valueOf(id));
            //lId = (Long) row.get("department_id");
            emp.setDepartmentId(lId);
        }
        if(row.get("employee_id") != null) {
            id = (Integer) row.get("employee_id");
            lId = Long.valueOf(id);
            //lId = (Long) row.get("employee_id");
            emp.setEmployeeId(lId);
        }
        if(row.get("gender") != null) {
            sGender = (String) row.get("gender");
            if (sGender.equals("male")) {
                emp.setGender(Gender.MALE);
            } else {
                emp.setGender(Gender.FEMALE);
            }
        }
        emp.setJobTitle((String) row.get("job_title"));
        return emp;
    }

    private String createSqlRequest(Employee emp){
        String sql = getFirstNameParam(emp) + getLastNameParam(emp) + getDepartmentIdParam(emp)
                + getJobTitleParam(emp) + getGenderParam(emp) + getDateOfBirthParam(emp)
                + getEmployeeIdParam(emp);
        return sql.replaceFirst(" and ", "");
    }

    private String getFirstNameParam(Employee emp){
        String str1 = "first_name = ";
        if(emp.getFirstName() == null) {
            return "";
        }
        String str2 = "\'" + emp.getFirstName() + "\'";
        return getStringParam(str1, str2);
    }

    private String getLastNameParam(Employee emp){
        String str1 = "last_name = ";
        if(emp.getLastName() == null) {
            return "";
        }
        String str2 = "\'" + emp.getLastName() + "\'";
        return getStringParam(str1, str2);
    }

    private String getDepartmentIdParam(Employee emp){
        String str1 = "department_id = ";
        if(emp.getDepartmentId() == null){
            return "";
        }
        String str2 = "\'" + emp.getDepartmentId().toString() + "\'";
        return getStringParam(str1, str2);
    }

    private String getJobTitleParam(Employee emp){
        String str1 = "job_title = ";
        if(emp.getJobTitle() == null) {
            return "";
        }
        String str2 = "\'" + emp.getJobTitle() + "\'";
        return getStringParam(str1, str2);
    }

    private String getGenderParam(Employee emp){
        String str1 = "gender = ";
        if(emp.getGender() == null) {
            return "";
        }
        String str2 = "\'" + emp.getGender().toString() + "\'";
        return getStringParam(str1, str2);
    }

    private String getDateOfBirthParam(Employee emp){
        String str1 = "date_of_birth = ";
        if(emp.getDateOfBirth() == null) {
            return "";
        }
        String str2 = "\'" + emp.getDateOfBirth().toString() + "\'";
        return getStringParam(str1, str2);
    }

    private String getEmployeeIdParam(Employee emp){
        String str1 = "employee_id = ";
        if(emp.getEmployeeId() == null) {
            return "";
        }
        String str2 = "\'" + emp.getEmployeeId().toString() + "\'";
        return getStringParam(str1, str2);
    }

    private String getStringParam(String str1, String str2){
        String result = "";
        if (str2 != null){
            result = " and " + str1 + str2;
        }
        return result;
    }

    @Override
    public void updateEmployee(Employee emp1, Employee emp2) {
        String str1, str2;
        str1 = createSqlRequest(emp1);
        str1 = str1.replaceAll(" and", ",");
        str2 = createSqlRequest(emp2);
        String sql = "update employee set " + str1 + "where " + str2;
        getJdbcTemplate().update(sql);
    }
}
