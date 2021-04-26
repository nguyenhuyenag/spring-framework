<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>Spring Boot AJAX</title>
	<link rel="shortcut icon" href="#">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container" style="margin-top: 20px;" align="center">
		<button class="btn btn-success" id="btn-get-list" data-toggle="modal" data-target="#myModal">Add new</button>
		<br /><br />
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listUser}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>
							<button class="btn btn-danger" onclick="delete_user('${user.email}')">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button class="btn btn-primary" id="btn-get-list">Reload</button>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog" style="width: 500px;">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Modal Header</h4>
				</div>
				<div class="modal-body">
					<p>Some text in the modal.</p>
					<form id="addForm">
						<div class="form-group">
							<label for="name" class="col-form-label">Name:</label>
							<input type="text" class="form-control" name="name" value="Abc">
						</div>
						<div class="form-group">
							<label for="email" class="col-form-label">Email:</label>
							<input type="text" class="form-control" name="email" value="abc@abc.com">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" style="float: left;" onclick="add(event)">Add</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			// hello();
		});
		function add(e) {
			// cach 1
			var elements = document.getElementById("addForm").elements;
			var obj = {};
			for (var i = 0; i < elements.length; i++) {
				var item = elements.item(i);
				obj[item.name] = item.value;
			}
			//console.log(JSON.stringify(obj));

			// cach 2
			var formData = new FormData($('#addForm')[0]);
			const formObject = Object.fromEntries(formData);
			//console.log(formObject);

			// cach 3
			const form = new FormData($('#addForm')[0]);
			const email = form.get("email");
			//console.log(email);

			// cach 4
			//console.log($('#addForm').serialize());

			$.ajax({
				type : "PUT",
				url : "/user/add/",
				dataType : "json",
				data : JSON.stringify(obj),
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					console.log("SUCCESS : ", data);
				},
				error : function(e) {
					console.log("ERROR : ", e);
				}
			});
		}
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
	</script>
</body>
</html>

