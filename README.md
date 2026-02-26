# HR Viewer — Jakarta EE / JDBC Web App

A read-only HR department and employee browser built with Jakarta EE, JDBC, and MySQL.

---

## Project Overview

This web application connects to a MySQL database (`HR_Db`) through a Payara-managed JDBC DataSource and displays HR data across two views — a department list and an employee browser filtered by department.

**No insert, update, or delete operations are performed by the application.**

---

## Endpoints / URLs

| Page | URL |
|------|-----|
| Home | `[http://localhost:8080/<contextRoot>/index.jsp](http://localhost:8080/assignment_2-1.0-SNAPSHOT/index.jsp)` |
| Departments | `[http://localhost:8080/<contextRoot>/departments](http://localhost:8080/assignment_2-1.0-SNAPSHOT/departments)` |
| Employees (by Department) | `[http://localhost:8080/<contextRoot>/employees](http://localhost:8080/assignment_2-1.0-SNAPSHOT/employees)` |

---

## Database Setup

### 1. MySQL Database

- **Database name:** `HR_Db`
- **Tables:** `Department(dept_id, dept_name)` and `Employee(emp_id, full_name, title, dept_id, salary, hire_date)`
- Employee has a foreign key referencing Department

### 2. Load Data

Run the following in MySQL Workbench in order:

```sql
-- 1. Create schema
CREATE DATABASE IF NOT EXISTS HR_Db;
USE HR_Db;

-- 2. Run Department.sql (Mockaroo-generated, 10 rows)
-- 3. Run Employee.sql  (Mockaroo-generated, 75 rows)
```

---

## Payara JDBC Configuration

### MySQL Connector/J

Copy the MySQL Connector/J JAR (e.g. `mysql-connector-j-8.x.x.jar`) into:

```
<PAYARA_HOME>/glassfish/domains/domain1/lib
```

Restart Payara after copying.

### JDBC Connection Pool

| Setting | Value |
|---------|-------|
| Pool Name | `MySqlHrPool` |
| Resource Type | `javax.sql.DataSource` |
| user | `root` (or your MySQL username) |
| password | your MySQL password |
| URL | `jdbc:mysql://localhost:3306/HR_Db?useSSL=false&serverTimezone=UTC` |
| driverClass | `com.mysql.cj.jdbc.Driver` |

After creating the pool, click **Ping** in the Payara Admin Console to verify the connection.

### JDBC Resource (JNDI Name)

| Setting | Value |
|---------|-------|
| JNDI Name | `jdbc/HrDS` |
| Pool Name | `MySqlHrPool` |

> **Important:** The JNDI name in Payara must exactly match the `@Resource(lookup = "...")` annotation in the servlet classes.

---

## Project Structure

```
src/main/java/.../
    model/
        Department.java
        Employee.java
    dao/
        DepartmentDao.java
        EmployeeDao.java
    web/
        DepartmentsServlet.java   → maps /departments
        EmployeesServlet.java     → maps /employees
src/main/webapp/
    index.jsp
    WEB-INF/views/
        departments.jsp
        employees.jsp
```

---

## Tech Stack

- **Jakarta EE 10** (WAR packaging)
- **Payara 6** application server
- **MySQL 8.0** database
- **JSTL 3.0** for JSP views
- **JDBC** via container-managed DataSource (`@Resource` injection)

---

## Common Troubleshooting

| Symptom | Likely Cause |
|---------|-------------|
| 500 error | JNDI name mismatch, pool ping fails, MySQL not running |
| 404 error | Wrong context root or servlet mapping mismatch |
| JSTL tag errors | Missing JSTL Maven dependencies — clean rebuild and redeploy |
| Ping fails in Payara | MySQL not running, wrong password, connector JAR not in `lib` folder |

---

## Notes

- Data was generated using [Mockaroo](https://mockaroo.com) — 10 departments, 75 employees
- The app is strictly read-only; there are no forms for inserting or modifying data
- Hire dates span the last 15 years (2011–2026)
