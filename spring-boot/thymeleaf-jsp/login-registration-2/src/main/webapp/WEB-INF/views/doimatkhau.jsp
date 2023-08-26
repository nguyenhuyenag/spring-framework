<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	div.form {
		display: block;
		text-align: center;
	}
	
	form {
		display: inline-block;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		width: 30%;
	}
</style>

<div class="container">
	<h1 class="text-center mt-4">Thay đổi mật khẩu</h1>
	<div class="form mb-4 mt-4" id="doimatkhau-form">
		<form class="pd-3">
			<div id="alert-msg" class="text-center"></div>
			<div class="form-outline mb-2">
				<input type="password" class="form-control" id='matkhauhientai' placeholder="Mật khẩu hiện tại" required />
			</div>
			<div class="form-outline mb-2">
				<input type="password" class="form-control" id='matkhaumoi' placeholder="Mật khẩu mới" required />
			</div>
			<div class="form-outline mb-2">
				<input type="password" class="form-control" id='nhaplaimatkhaumoi' placeholder="Nhập lại mật khẩu mới" required />
			</div>
			<button type="submit" class="btn btn-primary btn-block bg-color">Đổi mật khẩu</button>
		</form>
	</div>

</div>

<script type="text/javascript">
	$(function() {
		$("#doimatkhau-form").submit(function(event) {
			event.preventDefault();
			changePassword();
		});
	});

	function changePassword() {
		let message = "";
		let hasError = false;
		let matkhaumoi = $("#matkhaumoi").val().trim();
		let matkhauhientai = $("#matkhauhientai").val().trim();
		let nhaplaimatkhaumoi = $("#nhaplaimatkhaumoi").val().trim();
		if (matkhauhientai === matkhaumoi) {
			hasError = true;
			message = "Mật khẩu mới cần khác mật khẩu hiện tại";
		}
		if (matkhaumoi !== nhaplaimatkhaumoi) {
			hasError = true;
			message = "Mật khẩu nhập lại không đúng";
		}
		if (matkhaumoi.length < 6) {
			hasError = true;
			message = "Mật khẩu mới phải có ít nhất 6 ký tự";
		}
		if (hasError) {
			$("#alert-msg").addClass("alert alert-danger").text(message);
			return;
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "doimatkhau?matkhauhientai=" + matkhauhientai + "&matkhaumoi=" + matkhaumoi + "&nhaplaimatkhaumoi=" + nhaplaimatkhaumoi ,
			success : function(data) {
				if (data === "OK") {
					$("#alert-msg").addClass("alert alert-success").text("Thay đổi mật khẩu thành công");
				} else {
					$("#alert-msg").addClass("alert alert-danger").text(data);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
</script>
