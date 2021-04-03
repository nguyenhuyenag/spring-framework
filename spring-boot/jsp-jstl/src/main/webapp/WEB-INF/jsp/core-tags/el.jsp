<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title} (EL)</title>
	<link rel="stylesheet"  href="static/style.css">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	<h2>1) Điều kiện so sánh</h2>
	<table>
		<tbody>
			<tr>
				<td>Bằng với<br> (equals)
				</td>
				<td>==</td>
				<td>eq</td>
			</tr>
			<tr>
				<td>Không bằng<br> (Not equals)
				</td>
				<td>!=</td>
				<td>ne</td>
			</tr>
			<tr>
				<td>Nhỏ hơn<br> (Less than)
				</td>
				<td>&lt;</td>
				<td>lt</td>
			</tr>
			<tr>
				<td>Lớn hơn<br> (Greater than)
				</td>
				<td>&gt;</td>
				<td>gt</td>
			</tr>
			<tr>
				<td>Nhỏ hơn hoặc bằng<br> (Less than or equals)
				</td>
				<td>&lt;=</td>
				<td>le</td>
			</tr>
			<tr>
				<td>Lớn hơn hoặc bằng<br> (Greater than or equals)
				</td>
				<td>&gt;=</td>
				<td>ge</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<table>
		<tbody>
			<tr>
				<td><strong>Toán tử</strong></td>
				<td><strong>Mô tả</strong></td>
			</tr>
			<tr>
				<td>&amp;&amp;</td>
				<td>Và</td>
			</tr>
			<tr>
				<td>||</td>
				<td>Hoặc</td>
			</tr>
		</tbody>
	</table>
	<p>Ví dụ: ${4 > 2 && 3 < 5}</p>
</body>

</html>
