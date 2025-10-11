<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>HttpServletRequest</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		window.onload = function() {
			const hashmap = {
				"scheme": '${pageContext.request.scheme}',
				"serverName": '${pageContext.request.serverName}',
				"serverPort": '${pageContext.request.serverPort}',
				"contextPath": '${pageContext.request.contextPath}',
				"servletPath": '${pageContext.request.servletPath}',
				"requestURI": '${pageContext.request.requestURI}',
				"requestURL": '${pageContext.request.requestURL}',
				"method": '${pageContext.request.method}',
				"characterEncoding": '${pageContext.request.characterEncoding}',
				"queryString": '${pageContext.request.queryString}',
				"controller path": '${controller_path}',
			};

			function makeUL() {
				const list = document.createElement('ul');
				for (const key in hashmap) {
					if (hashmap.hasOwnProperty(key)) {
						const item = document.createElement('li');
						item.appendChild(document.createTextNode(key + " : " + hashmap[key]));
						list.appendChild(item);
					}
				}
				return list;
			}
			document.getElementById("dynamicUL").appendChild(makeUL());
		}
	</script>
</head>
<body>
	<h2>1) HttpServletRequest from 'pageContext'</h2>
	<div id="dynamicUL"></div>
	<h2>2) From Server Controller</h2>
	<ul>
		<jsp:useBean id="requestInfo" scope="request" type="java.util.Map"/>
		<c:forEach var="entry" items="${requestInfo}">
			<li><strong>${entry.key}</strong>: ${entry.value}</li>
		</c:forEach>
	</ul>
</body>

</html>
