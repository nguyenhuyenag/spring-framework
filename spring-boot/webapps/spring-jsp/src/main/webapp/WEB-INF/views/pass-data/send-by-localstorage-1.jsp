<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Page 1</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		function send() {
			var form = document.forms["formName"];
			form.action = "${CONTEXT_PATH}/pass-data/send-by-localstorage-2";
			localStorage.setItem("name", form.name.value);
			localStorage.setItem("age", form.age.value);
			form.submit();
		}
	</script>
</head>

<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>Send data by localStorage (page 1)</h2>
	<form name='formName'>
		<table>
			<tr>
				<td>Name:</td>
				<td><input type='text' name='name' value='Java 8'></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type='text' name='age' value='19952021'></td>
			</tr>
			<tr>
				<td><button onclick="send()">Send</button></td>
			</tr>
		</table>
	</form>
</body>

</html>
