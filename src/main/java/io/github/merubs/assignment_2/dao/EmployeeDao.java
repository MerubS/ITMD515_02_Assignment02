package io.github.merubs.assignment_2.dao;
import io.github.merubs.assignment_2.model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDao {
    private final DataSource dataSource;

    public EmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Employee> findByDepartment(int deptId) throws Exception {
        String sql = """
            SELECT emp_id, full_name, title, dept_id, salary, hire_date
            FROM Employee
            WHERE dept_id = ?
            ORDER BY full_name
        """;

        List<Employee> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new Employee(
                            rs.getInt("emp_id"),
                            rs.getString("full_name"),
                            rs.getString("title"),
                            rs.getInt("dept_id"),
                            rs.getBigDecimal("salary"),
                            rs.getDate("hire_date").toLocalDate()
                    ));
                }
            }
        }
        return results;
    }

    public String getDepartmentName(int deptId) throws Exception {
        String sql = "SELECT dept_name FROM Department WHERE dept_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("dept_name");
            }
        }
        return null;
    }
}
