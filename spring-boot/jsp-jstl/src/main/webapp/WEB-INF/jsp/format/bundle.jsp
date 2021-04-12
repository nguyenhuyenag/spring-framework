<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${empty param.language or param.language == 'en'}">
		<fmt:setBundle basename="com.bundles.bundle_en" scope="session" />
	</c:when>
	<c:otherwise>
		<fmt:setBundle basename="com.bundles.bundle_vi" scope="session" />
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>fmt:bundle example</title>
</head>
<body>
	<h2>fmt:bundle example</h2>
	<a href="${CONTEXT_PATH}/format/bundle?language=en">EN</a> &nbsp; <a href="${CONTEXT_PATH}/format/bundle?language=vi">VI</a>
	<br/> <br/>
	<form action="">
        <table border="1">
           <tr>
              <td>
                 <fmt:message key="login.label.username"/>
              </td>
              <td>
                 <input type="text" name="username" />
              </td>
           </tr>
           <tr>
              <td>
                 <fmt:message key="login.label.password"/>
              </td>
              <td><input type="text" name="username" /></td>
           </tr>
        </table>
        <input type="submit"  value="Submit"/>
     </form>
</body>
</html>
