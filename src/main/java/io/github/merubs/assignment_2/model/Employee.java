package io.github.merubs.assignment_2.model;
import java.math.BigDecimal;
import java.time.LocalDate;
public class Employee {
    private int empId;
    private String fullName;
    private String title;
    private int deptId;
    private BigDecimal salary;
    private LocalDate hireDate;

    public Employee() {}

    public Employee(int empId, String fullName, String title, int deptId, BigDecimal salary, LocalDate hireDate) {
        this.empId = empId;
        this.fullName = fullName;
        this.title = title;
        this.deptId = deptId;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
}
