<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	
	<h2>1) Điều kiện so sánh</h2>
	<c:set var="age" value="20" scope="session"/>
	<c:if test="${age > 18}" var="booleanValue">
		<p>You to vote because you're <c:out value="${age}" /> years old.</p>
		<p>Value of `var:` <c:out value="${booleanValue}" /></p>
	</c:if>

	<h2>1.5) </h2>
	<c:set var="bol" value="true" scope="session" />
	<c:if test="${bol}" var="testValue">
		<p>Test result: <c:out value="${testValue}"></c:out></p>
	</c:if>
	
	<h2>2) Departments and Employees</h2>
	<!-- Dùng for để duyệt trên các phòng ban (departments) -->
	<c:forEach items="${departments}" var="dept">
		<!-- Chỉ liệt kê các phòng ban có nhân viên -->
		<c:if test="${not empty dept.employees}">
			<h3>${dept.deptName}</h3>
			<ul>
				<!-- Duyệt trên các nhân viên thuộc phòng ban hiện tại -->
				<c:forEach items="${dept.employees}" var="emp">
					<li>${emp.empName} - (${emp.job})</li>
				</c:forEach>
			</ul>
		</c:if>
	</c:forEach>
</body>

</html>
