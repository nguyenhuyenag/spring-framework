<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>User</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="#">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script type="text/javascript">
		<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
	</script>
</head>

<body>
	<div class="container" style="margin-top: 20px;" align="center">
		<button class="btn btn-success" id="btn-get-list">Add new</button>
		<br /><br />
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Họ tên</th>
					<th>Email</th>
					<th>Xóa</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listUser}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>
							<button class="btn btn-danger" onclick="delete_user('${user.email}')">Xóa</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button class="btn btn-primary" id="btn-get-list">Reload</button>
	</div>
	<!-- <form>
		<label for="fname">Họ tên:</label><br>
		<input type="text" id="fname" name="fname"><br>
		<label for="femail">Email:</label><br>
		<input type="email" id="femail" name="femail"><br>
		<label for="fphone">Phone:</label><br>
		<input type="text" id="fphone" name="fphone"><br>
		<label for="fpassword">Password:</label><br>
		<input type="password" id="fpassword" name="fpassword"><br>
		<br>
		<button id="btn-add-new">Tạo mới</button>
	</form> -->

	<script type="text/javascript">
		$(document).ready(function(){
			// hello();
        });
		
		function delete_user(email) {
			console.log(email);
			$.ajax({
				type : "DELETE",
				url : "/user/delete?email=" + email,
				success : function(data) {
					console.log("SUCCESS : ", data);
					window.location.reload();
				},
				error : function(e) {
					console.log("ERROR : ", e);
				}
			});
		}

/* 		function hello() {
			console.log("Hello World");
		}; */

		/* function insertNewUser(user) {
			$('.list-user').append(`
			     <tr data-id="${user.id}">
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.avatar}</td>
					<td>${user.birthday}</td>
					<td>
						<button onclick="delete_user(${user.id})">Xóa</button>
					</td>
				</tr>
			`)
		} */

		/* $('#btn-get-list').click(function() {
			$.ajax({
			   	url: 'http://localhost:8080/users',
			   	type: 'GET',
			   	error: function(data) {
			   		alert(data.responseJSON.message)
			   	},
			   	success: function(data) {
			   	    $('.list-user').html("")
			     	for (user of data) {
			     		insertNewUser(user)
			     	}
			   	}
			});	
		})

		$('#btn-add-new').click(function() {
			event.preventDefault()
			name = $('#fname').val()
			phone = $('#fphone').val()
			email = $('#femail').val()
			password = $('#fpassword').val()

			// TODO: Validate thông tin ở đây

			req = {
				name: name,
				email: email,
				phone: phone,
				password: password
			}
			console.log(req)
			var myJSON = JSON.stringify(req);
			console.log(myJSON)
			$.ajax({
			   	url: 'http://localhost:8080/users',
			   	type: 'POST',
			   	data: myJSON,
			   	dataType: "json",
			   	contentType: "application/json; charset=utf-8",
			   	error: function(data) {
			   		alert(data.responseJSON.message)
			   	},
			   	success: function(data) {
			   		alert("Tạo mới thành công")
			   	    insertNewUser(data)
			   	    $('#fname').val('')
			   	    $('#femail').val('')
			   	    $('#fphone').val('')
			   	    $('#fpassword').val('')
			   	}
			});
		}) */
	</script>
</body>
</html>

