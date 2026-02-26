<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Employees by Department</title>
</head>
<body>
  <h2>Employees by Department</h2>

  <form method="get" action="${pageContext.request.contextPath}/employees">
    <label for="deptId">Choose a department:</label>
    <select id="deptId" name="deptId">
      <option value="">-- select --</option>
      <c:forEach var="d" items="${departments}">
        <option value="${d.deptId}" <c:if test="${selectedDeptId == d.deptId}">selected</c:if>>
          ${d.deptId} - ${d.deptName}
        </option>
      </c:forEach>
    </select>
    <button type="submit">View Employees</button>
  </form>

  <c:if test="${not empty selectedDeptId}">
    <h3>Department: ${selectedDeptName} (ID: ${selectedDeptId})</h3>

    <c:choose>
      <c:when test="${empty employees}">
        <p>No employees found for this department.</p>
      </c:when>
      <c:otherwise>
        <table border="1" cellpadding="6">
          <tr>
            <th>emp_id</th>
            <th>full_name</th>
            <th>title</th>
            <th>salary</th>
            <th>hire_date</th>
          </tr>

          <c:forEach var="e" items="${employees}">
            <tr>
              <td>${e.empId}</td>
              <td>${e.fullName}</td>
              <td>${e.title}</td>
              <td>${e.salary}</td>
              <td>${e.hireDate}</td>
            </tr>
          </c:forEach>
        </table>
      </c:otherwise>
    </c:choose>
  </c:if>

  <p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
</body>
</html>