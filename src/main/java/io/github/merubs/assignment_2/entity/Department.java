package io.github.merubs.assignment_2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    public Department() { }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}