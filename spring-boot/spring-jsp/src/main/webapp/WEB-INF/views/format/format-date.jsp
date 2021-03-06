<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title}</title>
</head>
<body>
	<c:set var="current_time" value="<%= new java.util.Date() %>" />
	Date -
	<br>
	<fmt:formatDate value="${current_time}" type="date" />
	<br>
	<br> Time -
	<br>
	<fmt:formatDate value="${current_time}" type="time" />
	<br>
	<br> Date and Time Both -
	<br>
	<fmt:formatDate value="${current_time}" type="both" />
	<br>
	<br> Date in 'dd-MM-yyyy' format -
	<br>
	<fmt:formatDate value="${current_time}" type="date"
		pattern="dd-MM-yyyy" />
	<br>
	<br> Date and time in short style -
	<br>
	<fmt:formatDate value="${current_time}" type="both" dateStyle="short"
		timeStyle="short" />
	<br>
	<br> Date and time in long style -
	<br>
	<fmt:formatDate value="${current_time}" type="both" dateStyle="long"
		timeStyle="long" />
	<br>
	<br> Date and time in US/Alaska(timeZone="GMT-9:00") -
	<br>
	<fmt:formatDate value="${current_time}" type="both" timeZone="GMT-9:00" />
	<br>
</body>

</html>
