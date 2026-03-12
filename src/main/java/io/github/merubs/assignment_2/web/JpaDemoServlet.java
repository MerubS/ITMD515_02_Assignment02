package io.github.merubs.assignment_2.web;

import io.github.merubs.assignment_2.entity.Department;
import io.github.merubs.assignment_2.entity.Employee;
import io.github.merubs.assignment_2.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/jpa/demo")
public class JpaDemoServlet extends HttpServlet {

    @Inject
    private EmployeeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        String action = req.getParameter("action");

        if (action == null) {
            resp.getWriter().println("Use ?action=create / find / update / updateSalary / findDepartment");
            return;
        }

        switch (action) {

            case "create" -> {
                int empId       = Integer.parseInt(req.getParameter("empId"));
                String fullName = req.getParameter("fullName");
                String title    = req.getParameter("title");
                int deptId      = Integer.parseInt(req.getParameter("deptId"));
                BigDecimal sal  = new BigDecimal(req.getParameter("salary"));
                LocalDate date  = LocalDate.parse(req.getParameter("hireDate"));
                service.createEmployee(empId, fullName, title, deptId, sal, date);
                resp.getWriter().println("Created employee emp_id=" + empId);
            }

            case "find" -> {
                int empId  = Integer.parseInt(req.getParameter("empId"));
                Employee e = service.findEmployee(empId);
                if (e == null) { resp.getWriter().println("Not found"); return; }
                resp.getWriter().println("Found emp_id="  + e.getEmpId());
                resp.getWriter().println("full_name="     + e.getFullName());
                resp.getWriter().println("title="         + e.getTitle());
                resp.getWriter().println("salary="        + e.getSalary());
                resp.getWriter().println("hire_date="     + e.getHireDate());
                resp.getWriter().println("dept_id="       + e.getDepartment().getDeptId());
            }

            case "update" -> {
                int empId       = Integer.parseInt(req.getParameter("empId"));
                String newTitle = req.getParameter("title");
                service.updateTitle(empId, newTitle);
                resp.getWriter().println("Updated emp_id=" + empId + " title=" + newTitle);
            }

            case "updateSalary" -> {
                int empId         = Integer.parseInt(req.getParameter("empId"));
                BigDecimal newSal = new BigDecimal(req.getParameter("salary"));
                service.updateSalary(empId, newSal);
                resp.getWriter().println("Updated emp_id=" + empId + " salary=" + newSal);
                resp.getWriter().println("(dirty checking — no em.merge() was called)");
            }

            case "findDepartment" -> {
                int deptId      = Integer.parseInt(req.getParameter("deptId"));
                Department dept = service.findDepartment(deptId);
                if (dept == null) { resp.getWriter().println("Department not found"); return; }
                resp.getWriter().println("dept_id="   + dept.getDeptId());
                resp.getWriter().println("dept_name=" + dept.getDeptName());
            }

            default -> resp.getWriter().println("Unknown action=" + action);
        }
    }
}