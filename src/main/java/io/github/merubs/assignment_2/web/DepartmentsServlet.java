package io.github.merubs.assignment_2.web;
import io.github.merubs.assignment_2.dao.DepartmentDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/departments")
public class DepartmentsServlet extends HttpServlet {
    @Resource(lookup = "jdbc/HrDS")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DepartmentDao dao = new DepartmentDao(dataSource);
            req.setAttribute("departments", dao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/departments.jsp").forward(req, resp);
        } catch (Exception ex) {
            throw new ServletException("Failed to load departments.", ex);
        }
    }
}
