package io.github.merubs.assignment_2.service;

import io.github.merubs.assignment_2.entity.Department;
import io.github.merubs.assignment_2.entity.Employee;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@RequestScoped
public class EmployeeService {

    @PersistenceContext(unitName = "deptPU")
    private EntityManager em;

    @Transactional
    public void createEmployee(int empId, String fullName, String title,
                               int deptId, BigDecimal salary, LocalDate hireDate) {
        Department dept = em.find(Department.class, deptId);
        if (dept == null) throw new IllegalArgumentException("No such dept_id=" + deptId);

        Employee e = new Employee();
        e.setEmpId(empId);
        e.setFullName(fullName);
        e.setTitle(title);
        e.setDepartment(dept);
        e.setSalary(salary);
        e.setHireDate(hireDate);
        em.persist(e);
    }

    public Employee findEmployee(int empId) {
        return em.find(Employee.class, empId);
    }

    @Transactional
    public void updateTitle(int empId, String newTitle) {
        Employee e = em.find(Employee.class, empId);
        if (e == null) throw new IllegalArgumentException("No such emp_id=" + empId);
        // Dirty checking: no em.merge() or em.update() needed
        e.setTitle(newTitle);
    }

    // Required TODO #1: updateSalary — dirty checking
    @Transactional
    public void updateSalary(int empId, BigDecimal newSalary) {
        Employee e = em.find(Employee.class, empId);
        if (e == null) throw new IllegalArgumentException("No such emp_id=" + empId);
        e.setSalary(newSalary); // dirty checking handles the UPDATE automatically
    }

    // Required TODO #2: findDepartment
    public Department findDepartment(int deptId) {
        return em.find(Department.class, deptId);
    }
}