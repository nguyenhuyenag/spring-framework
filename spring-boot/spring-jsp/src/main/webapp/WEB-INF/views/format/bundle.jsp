<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty param.language or param.language == 'en'}">
	<fmt:setBundle basename="com.bundles.bundle_en" scope="session" />
</c:if>
<c:if test="${param.language == 'vi'}">
	<fmt:setBundle basename="com.bundles.bundle_vi" scope="session" />
</c:if>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h3>Bundles 1</h3>
	<a href="${CONTEXT_PATH}/format/bundle?language=en">EN</a>
	&nbsp;
	<a href="${CONTEXT_PATH}/format/bundle?language=vi">VI</a>
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
     <%-- <h2>2)</h2>
     <fmt:bundle basename = "com.tutorialspoint.Example" prefix = "count.">
         <fmt:message key = "one"/><br/>
         <fmt:message key = "two"/><br/>
         <fmt:message key = "three"/><br/>
      </fmt:bundle> --%>
      
      <c:set var="userName" value="Guest"/>
      <fmt:bundle basename="net.codejava.jstl.messages">   
         <fmt:message key="codejava.user.greeting">
             <fmt:param value="${userName}"/>
          </fmt:message>
          <br/>   
      </fmt:bundle>
</body>
</html>
