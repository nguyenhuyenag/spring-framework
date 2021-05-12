<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>HttpServletRequest</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		window.onload = function() {
			var hashmap = {
				"scheme" : '${pageContext.request.scheme}',
				"serverName" : '${pageContext.request.serverName}',
				"serverPort" : '${pageContext.request.serverPort}',
				"contextPath" : '${pageContext.request.contextPath}',
				"servletPath" : '${pageContext.request.servletPath}',
				"requestURI" : '${pageContext.request.requestURI}',
				"requestURL" : '${pageContext.request.requestURL}',
				"method" : '${pageContext.request.method}',
				"characterEncoding" : '${pageContext.request.characterEncoding}',
				"queryString" : '${pageContext.request.queryString}',
				"param1" : '${pageContext.request.queryString}',
			};
			function makeUL() {
				var list = document.createElement('ul');
				for (var key in hashmap) {
					if (hashmap.hasOwnProperty(key)) {
						var item = document.createElement('li');
						// console.log(key + " -> " + hashmap[key]);
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
	<h2>HttpServletRequest</h2>
	<div id="dynamicUL"></div>
	<%
		// Map<String, String[]> map = request.getParameterMap();
		// request.getParameterMap().forEach((k,v) -> out.print(k, v));
	%>
</body>

</html>

