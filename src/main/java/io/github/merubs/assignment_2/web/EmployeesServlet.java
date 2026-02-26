package io.github.merubs.assignment_2.web;
import io.github.merubs.assignment_2.dao.DepartmentDao;
import io.github.merubs.assignment_2.dao.EmployeeDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet{
    @Resource(lookup = "jdbc/HrDS")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DepartmentDao deptDao = new DepartmentDao(dataSource);
            EmployeeDao empDao = new EmployeeDao(dataSource);

            req.setAttribute("departments", deptDao.findAll());

            String deptIdParam = req.getParameter("deptId");
            if (deptIdParam != null && !deptIdParam.isBlank()) {
                int deptId = Integer.parseInt(deptIdParam);
                req.setAttribute("selectedDeptId", deptId);
                req.setAttribute("selectedDeptName", empDao.getDepartmentName(deptId));
                req.setAttribute("employees", empDao.findByDepartment(deptId));
            }

            req.getRequestDispatcher("/WEB-INF/views/employees.jsp").forward(req, resp);
        } catch (Exception ex) {
            throw new ServletException("Failed to load employees.", ex);
        }
    }
}
