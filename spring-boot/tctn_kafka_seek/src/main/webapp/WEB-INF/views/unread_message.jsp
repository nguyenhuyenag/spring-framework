<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	thead, th {
		text-align: center;
	}
</style>

<script>
	$(function() {
		initTable();
		$('#count').on('click', function (e) {
			$.ajax({
				type: "POST",
				url: "count-unread-message",
				success: function (data) {
					initTable(data);
				},
				error: function (e) {
					console.log("ERROR : ", e);
				}
			});
		});
	});

	function initTable(dataSet = []) {
		let table = $('#uTable').DataTable({
			data: dataSet,
			columns : [
				{
					sort : "asc",
					data : 'partition'
				},
				{ data : 'amount' },
			],
			columnDefs : [
				{ "className" : "dt-center", "targets" : [ 0, 1] }
			],
			"info": false,
			"paging": false,
			"ordering": false,
			"searching": false,
			"bLengthChange": false,
			"destroy": true,
			"footerCallback": function (row, data, start, end, display) {
                let totalAmount = 0;
                for (let i = 0; i < data.length; i++) {
                	totalAmount += parseInt(data[i]["amount"]);
                }
                $(".total_amount").text(totalAmount);
       		}
		});
	}
</script>

<div class="container mb-4">
	<h1 class="text-center">Đếm số Hóa Đơn chưa nhận</h1>
	<!-- search -->
	<div class="text-center">
		<div class="form-group">
			<button id="count" class="btn btn-primary">Count</button>
		</div>
		<div class="form-group">
			<h3>Tổng cộng: <span class="total_amount"></span></h3>
		</div>
	</div>
	<!-- table content -->
	<div class="mt-4">
		<table id="uTable" class="table display cell-border" style="width: 70%">
			<thead class="thead-light text-center">
				<tr>
					<th class="align-middle">Partition</th>
					<th class="align-middle">Hóa Đơn chưa nhận</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
