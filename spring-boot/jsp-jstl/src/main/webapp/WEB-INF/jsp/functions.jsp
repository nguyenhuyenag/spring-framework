<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<title>${title}</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet"  href="./static/style.css">
</head>

<body>
	<h1 class="red">1) contains & containsIgnoreCase</h1>
	<h2>1.1) fn:contains</h2>
	<c:if test="${fn:contains('Welcome to JSTL Functions tags', 'jstl')}">
		Source String contains jstl.
	</c:if>
	<c:if test="${fn:contains('Welcome to JSTL Functions tags', 'JSTL')}">
		Source String contains JSTL.
	</c:if>
	<br>
	<h2>1.2) fn:containsIgnoreCase</h2>
	<c:if test="${fn:containsIgnoreCase('Welcome to JSTL Functions tags', 'jstl')}">
		Source String contains jstl.
	</c:if>
	<br>
	<c:if test="${fn:containsIgnoreCase('Welcome to JSTL Functions tags', 'JSTL')}">
		Source String contains JSTL.
	</c:if>
	
	<h1 class="red">2) toLowerCase & toUpperCase</h1>
	<h2>2.1) fn:toLowerCase</h2>
	<c:out value="${fn:toLowerCase('Welcome to JSTL Functions')}"/><br>
	<c:out value="${fn:toLowerCase('Welcome to 12JSTL@ @#Functions')}"/>     
	<h2>2.2) fn:toUpperCase</h2>
	<c:out value="${fn:toUpperCase('Welcome to jstl functions')}"/><br>
	<c:out value="${fn:toUpperCase('Welcome to 12jstl@ @#functions')}"/>
	
	<h1 class="red">3) startsWith & endsWith</h1>
	<h2>3.1) fn:startsWith</h2>
	<c:if test="${fn:startsWith('Welcome to JSTL Functions tags', 'Welcome to JSTL')}">
		Source String starts with 'Welcome to JSTL' <br>
	</c:if>
	<c:if test="${fn:startsWith('Welcome to JSTL Functions tags', 'welcome to jstl')}">
		Source String starts with 'welcome to jstl' <br>
	</c:if>
	<c:if test="${fn:startsWith('Welcome to JSTL Functions tags', 'to JSTL Functions')}">
		Source String starts with 'to JSTL Functions' <br>
	</c:if>
	<h2>3.2) fn:endsWith</h2>
	<c:if test="${fn:endsWith('Welcome to JSTL Functions tags', 'tags')}">
		Source String ends with 'tagsL' <br>
	</c:if>
	<c:if test="${fn:endsWith('Welcome to JSTL Functions tags', 'JSTL Functions tags')}">
		Source String ends with 'JSTL Functions tags' <br>
	</c:if>
	<c:if test="${fn:startsWith('Welcome to JSTL Functions tags', 'JSTL Functions')}">
		Source String ends with 'JSTL Functions' <br>
	</c:if>
	<c:if test="${fn:startsWith('Welcome to JSTL Functions tags', 'Welcome to JSTL Functions tags')}">
		Source String ends with 'Welcome to JSTL Functions tags' <br>
	</c:if>
	
	<h1 class="red">4) trim</h1>
	<c:set var="a" value="${'  hello  '}" />
	<!-- String with 2 whit spaces at beginning and end -->
	<b>Length before trim: </b>
	<c:out value="${fn:length(a)}" /><br>
	<c:set var="a" value="${fn:trim(a)}" />
	<b>Length after trim: </b>
	<c:out value="${fn:length(a)}" /><br>
	
	<h1 class="red">5) substring & substringBefore & substringAfter</h1>
	<h2>5.1) substring</h2>
	<c:out value="${fn:substring('This is just a demo', 8, 16)}" />
	<br />
	<c:out value="${fn:substring('This is just a demo', 0, 4)}" />
	<h2>5.2) substringBefore</h2>
	<c:out value="${fn:substringBefore('This is just a demo', 'just')}" />
	<br />
	<c:out value="${fn:substringBefore('This is just a demo', 'a')}" />
	<h2>5.3) substringAfter</h2>
	<c:out value="${fn:substringAfter('This is just a demo', 'just')}" />
	<br />
	<c:out value="${fn:substringAfter('This is just a demo', 'This')}" />
	
	<h1 class="red">6) replace</h1>
	<c:out value="${fn:replace('This is just a demo', 'is', 'was')}" />
	
	<h1 class="red">7) split  & join</h1>
	<h2>7.1) split</h2>
	<c:set var="a" value="${fn:split('This, is, just, a, demo', ',')}" />
	<c:forEach items="${a}" var="i">
	    ${i} <br />
	</c:forEach>
	<h2>7.2) join</h2>
	<c:set var = "string1" value = "This is first String."/>
	<c:set var = "string2" value = "${fn:split(string1, ' ')}" />
	<c:set var = "string3" value = "${fn:join(string2, '-')}" />
	<p>Final String : ${string3}</p>

	<h1 class="red">8) escapeXML</h1>
	<c:set var = "string1" value = "This is first String."/>
	<c:set var = "string2" value = "This <abc>is second String.</abc>"/>
	<h2>8.1) Without escapeXml() Function:</h2>
	<p>string (1) : ${string1}</p>
	<p>string (2) : ${string2}</p>
	<h2>8.2) With escapeXml() Function:</h2>
	<p>string (1) : ${fn:escapeXml(string1)}</p>
	<p>string (2) : ${fn:escapeXml(string2)}</p>
	
	<h1 class="red">9) indexOf</h1>
	<c:set var="str1" value="This is just a demo" />
	<b>Index of 'is': </b> <c:out value="${fn:indexOf(str1, 'is')}" /><br>
	<b>Index of 'just': </b><c:out value="${fn:indexOf(str1, 'just')}" />

	<h1 class="red">10) length</h1>
	<c:set var="str" value="This is just a demo" />
	<c:set var="arr" value="<%=new Integer[] { 1, 2, 3, 4, 5 }%>" />
	<b>Length of 'arr': </b> <c:out value="${fn:length(arr)}" />
	<br>
	<b>Length of 'str': </b> <c:out value="${fn:length(str)}" />
</body>
</html>
