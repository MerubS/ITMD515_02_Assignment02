<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Departments</title>
</head>
<body>
  <h2>Departments</h2>

  <table border="1" cellpadding="6">
    <tr>
      <th>dept_id</th>
      <th>dept_name</th>
    </tr>

    <c:forEach var="d" items="${departments}">
      <tr>
        <td>${d.deptId}</td>
        <td>${d.deptName}</td>
      </tr>
    </c:forEach>
  </table>

  <p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
</body>
</html>