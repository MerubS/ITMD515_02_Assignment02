package io.github.merubs.assignment_2.dao;
import io.github.merubs.assignment_2.model.Department;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class DepartmentDao {
    private final DataSource dataSource;

    public DepartmentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Department> findAll() throws Exception {
        String sql = "SELECT dept_id, dept_name FROM Department ORDER BY dept_id";
        List<Department> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(new Department(
                        rs.getInt("dept_id"),
                        rs.getString("dept_name")
                ));
            }
        }
        return results;
    }
}
