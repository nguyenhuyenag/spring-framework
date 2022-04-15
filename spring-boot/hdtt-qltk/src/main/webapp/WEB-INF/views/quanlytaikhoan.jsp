<%@ page contentType="text/html;charset=UTF-8" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<style>
	table tbody tr {
		cursor: pointer;
	}

	button {
		width: 75px !important;
	}
</style>

<div class="container">
	<h1 class="text-center mt-3 mb-3">Quản lý tài khoản API</h1>
	<table id="uTable" class="table display cell-border" style="width: 100%">
		<thead class="thead-light">
			<tr class="text-center">
				<th>STT</th>
				<th>Email</th>
				<th>MST</th>
				<th>Tên</th>
				<th>Tình trạng</th>
				<th>Cập nhật</th>
			</tr>
		</thead>
	</table>
	<div class="modal fade" id="editModal" data-backdrop="static" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="editModalLabel">Edit form</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="form-edit">
						<div class="form-group">
							<label for="email" class="col-form-label">Email:</label>
							<input type="text" class="form-control" id="email" disabled>
						</div>
						<div class="form-group">
							<label for="mst" class="col-form-label">MST:</label>
							<input type="text" class="form-control" id="mst">
						</div>
						<div class="form-group">
							<label for="mst" class="col-form-label">Tên:</label>
							<input type="text" class="form-control" id="fullname">
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	initTable();
	// edit action
	// $(function () {
		$("#form-edit").submit(function (e) {
			e.preventDefault();
			let obj = {
				"email": $('#email').val(),
				"mstTcgp": $('#mst').val(),
				"fullname": $('#fullname').val()
			};
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "edit-user",
				data: JSON.stringify(obj),
				success: function (s) {
					initTable();
					$('#editModal').modal('hide');
				},
				error: function (e) {
					console.log("ERROR : ", e);
				}
			});
		});
	//});

	function initTable() {
		$(function () {
			// let table = $('#uTable').DataTable().clear().destroy();
			let table = $('#uTable').DataTable({
				"ajax": {
					"url": "get-all-user",
					"type": "GET",
					"dataSrc": "", // data flat
					"deferRender": true
				},
				columns: [
					{
						"render": function (data, type, full, meta) {
							return meta.row + 1;
						}
					},
					{ data: 'email' },
					{ data: 'mstTcgp' },
					{ data: 'fullname' },
					{
						data: "active",
						"render": function (data, type, row, meta) {
							let text = "Disable";
							let cname = "btn btn-danger";
							if (data != 0) {
								text = "Active";
								cname = "btn btn-success";
							}
							return '<button type="button" onclick="changeUserStatus(\`' + row['email'] + '\`)" class="' + cname + '">' + text + '</button>';
						}
					},
					{
						"targets": -1,
						"data": null,
						"render": function (data, type, row, meta) {
							return '<button type="button" class="btn-edit btn btn-primary">Edit</button>';
						}
					},
				],
				columnDefs: [
					{ "className": "dt-center", "targets": [0, 4] }
				],
				"info": false,
				"paging": false,
				"ordering": false,
				"searching": false,
				"bLengthChange": false,
				"destroy": true,
				// "stateSave": true,
				// "bDestroy": true,
				// "retrieve": true,
			});

			$('#uTable tbody').on('click', '.btn-edit', function () {
				let data = table.row($(this).parents('tr')).data();
				// console.log("data", data);
				if (data != null) {
					$('#email').val(data.email);
					$('#mst').val(data.mstTcgp);
					$('#fullname').val(data.fullname);
					$('#editModal').modal('toggle');
				}
			});
		});
	}

	function changeUserStatus(email) {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "change-user-status?email=" + email,
			success: function (data) {
				initTable();
			},
			error: function (e) {
				console.log("ERROR : ", e);
			}
		});
	}


	/* function recordNotFound() {
		$('<tr>').append(
				$('<td class="text-danger" colspan="6">').text(
						"Không có kết quả!")).appendTo('#result-table');
	} */

	/*function init() {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "get-all-user",
			success: function (data) {
				console.log(data);
				$("#result-table-body").empty();
				if (data != null && data.length > 0) {
					$.each(data, function (i, item) {
						$('<tr>').append(
							$('<td>').text(i + 1),
							$('<td>').text(item.email),
							$('<td>').text(item.mstTcgp),
							$('<td>').text(item.fullname),
							$('<td>').html(handleButton(item.active)),
							$('<td>').html('<button style="width: 80px;" type="button" class="btn btn-primary">Edit</button>')
						).appendTo('#result-table');
					});
				} else {
					recordNotFound();
				}
			},
			error: function (e) {
				console.log("ERROR : ", e);
			}
		});
	}*/

	/* function handleButton(v) {
		let s = "Disable";
		let cname = "btn btn-danger";
		if (v != 0) {
			s = "Active";
			cname = "btn btn-success";
		}
		return '<button style="width: 80px;" type="button" class="' + cname + '">'
				+ s + '</button>';
	} */
</script>