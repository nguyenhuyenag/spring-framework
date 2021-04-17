<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Page 2</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		window.onload = function() {
			var form = document.forms["form2"];
			form.name.value = localStorage.getItem("name");
			form['age'].value = localStorage.getItem("age");
		}
	</script>
</head>
<body>
	<a href="${CONTEXT_PATH}/pass-data/send-by-localstorage-1">Back</a>
	<h2>Send data by localStorage (page 2)</h2>
	<form name="form2">
		<table>
			<tr>
				<td><b>Name:</b></td>
				<td><input type='text' name='name'></td>
			</tr>
			<tr>
				<td><b>Age:</b></td>
				<td><input type='text' name='age'></td>
			</tr>
		</table>
	</form>
</body>

</html>

