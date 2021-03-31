<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Expression Language (EL)</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>1) Basic</h2>
	<table border="1">
	<tbody>
		<tr>
			<td><strong>Khái niệm</strong></td>
			<td><strong>Điều kiện EL</strong></td>
			<td><strong>Kết quả</strong></td>
		</tr>
		<tr>
			<td>Số nhỏ hơn</td>
			<td><code>${1 < 2}</code></td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số lớn hơn</td>
			<td>${1 > 2}</td>
			<td>false</td>
		</tr>
		<tr>
			<td>Số nhỏ hơn<br>
			(Less than)</td>
			<td>${1 lt 2}</td>
			<td>true</td>
		</tr>
		<%-- <tr>
			<td>Số lớn hơn<br>
			(Greater than)</td>
			<td>${1 gt 2}</td>
			<td>false</td>
		</tr>
		<tr>
			<td>Số hơn hơn hoặc bằng</td>
			<td>${1 &gt;= 1}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số nhỏ hơn hoặc bằng</td>
			<td>${1 &lt;= 1}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số nhỏ hơn hoặc bằng<br>
			(Less equals)</td>
			<td>${1 le 1}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số lớn hơn hoặc bằng<br>
			(Greater than or equal)</td>
			<td>${1 ge 1}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số bằng với</td>
			<td>${1 == 1}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số bằng với<br>
			(equal)</td>
			<td>${1 eq 1}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số không bằng với</td>
			<td>${1 != 2}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Số không bằng với<br>
			&nbsp;(not equal)</td>
			<td>${1 ne 2}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Chữ nhỏ hơn</td>
			<td>${'abe' &lt; 'ade'}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Chữ lớn hơn</td>
			<td>${'abe' &gt; 'ade'}</td>
			<td>false</td>
		</tr>
		<tr>
			<td>Chữ bằng với<br>
			(Equals)</td>
			<td>${'abe' eq 'abe'}</td>
			<td>true</td>
		</tr>
		<tr>
			<td>Chữ không bằng với<br>
			(Not equals)</td>
			<td>${'abe' ne 'ade'}</td>
			<td>true</td>
		</tr> --%>
	</tbody>
</table>
	
</body>

</html>
