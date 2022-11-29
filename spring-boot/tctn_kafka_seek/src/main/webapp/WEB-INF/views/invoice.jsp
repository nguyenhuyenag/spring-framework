<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	div.form {
		display: block;
		text-align: center;
	}
	
	form {
		width: 50%;
		display: inline-block;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		margin-right: auto;
		margin-left: auto;
		display: inline-block;
	}
	
	thead, th {
		text-align: center;
	}
</style>

<script>
	$(function() {
		initTable([]);
		formatDate();
	});

	function initTable(dataSet) {
		let table = $('#uTable').DataTable({
			data: dataSet,
			columns : [
				{
					"render" : function(data, type, row, meta) {
						return meta.row + 1;
					}
				},
				{ data : 'mathongdiep' },
				{
					"render" : function(data, type, row, meta) {
						return handleButton(row['mathongdiep']);
					}
				},
				{
					"render": function (data, type, row, meta) {
						return '<input type="checkbox" id="chbox' + (meta.row + 1)  + '" value="" />';
					}
				},
			],
			columnDefs : [
				{ "className" : "dt-center", "targets" : [ 0, 2, 3 ] }
			],
			"info": false,
			"paging": true,
			"ordering": false,
			"searching": false,
			"bLengthChange": false,
			"destroy": true,
		});
		
		// button search click
		$('#uTable tbody').on('click', 'button', function (e) {
			var data = table.row($(this).parents('tr')).data();
			if (data != null) {
				let _id = '#' + $(this).attr('id');
				let _class = '.' + $(this).attr('id');
				$(_id).prop('disabled', true);
				$(_class).attr("hidden", false);
				$.ajax({
					url: "seek-invoice",
					type: "POST",
					contentType: "application/json",
					data: JSON.stringify(data),
					success: function (s) {
						$(_id).prop('disabled', false);
						if (s === true) {
							$(_id).text("Success").addClass("btn-success");
						} else {
							$(_class).attr("hidden", true);
						}
					},
					error: function (e) {
						console.log("ERROR : ", e);
					}
				});
			}
		});
				
		// form submit
		$('form').submit(function (e) {
		  	e.preventDefault();
			let url = 'search-invoice?' + encodeURI($('#searchForm').serialize());
			$.ajax({
				url: url,
				type: "GET",
				success: function (data) {
					initTable(data);
				},
				error: function (e) {
					console.log("ERROR : ", e);
				}
			});
		});
		
		// check all
		$("#flowcheckall").click(function () {
			$('#uTable tbody input[type="checkbox"]').prop('checked', this.checked);
		});

		// search all
		$('#search-all').on('click', function (e) {
			$(this).prop('disabled', true);
			$('.search-all').attr("hidden", false);
			let seekList = [];
			let rows_selected = table.rows($('input[type="checkbox"]:checked').parent()).data();
			$.each(rows_selected, function(i, v) {
				seekList.push(v);
				let mtd = v.mathongdiep;
				$('#' + mtd).prop('disabled', true);
				$('.' + mtd).attr("hidden", false);
			});
			$.ajax({
				url: "seek-multi-invoice",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify(seekList),
				success: function (s) {
					resetSeekAllButton();
					$.each(s, function(i, v) {
						let mtd = v.mathongdiep;
						$('#' + mtd).prop('disabled', false);
						$('.' + mtd).attr("hidden", true);
						if (v.status) {
							$('#' + mtd).text("Success").addClass("btn-success");
						}
					});
				},
				error: function (e) {
					resetSeekAllButton();
					console.log("ERROR : ", e);
				}
			});
		});
	}

	function resetSeekAllButton() {
		$('#search-all').prop('disabled', false);
		$('.search-all').attr("hidden", true);
	}
	
	function handleButton(id) {
		return "<button id='" //
			+ id + "' class='search btn btn-primary'>Seek&nbsp;<span hidden class='" //
			+ id + " spinner-border spinner-border-sm'></span></button>";
	}

	function formatDate() {
		let pattern = "YYYY-MM-DDTHH:mm";
		let today = new Date(); // today
		let todayFormat = moment(today).format(pattern);
		// today.setDate(today.getDate() - 1);
		// let from = moment(today).format(pattern);
		$('#fromdate').val(todayFormat);
		$('#todate').val(todayFormat);
	}
</script>

<div class="container mb-4">
	<h1 class="text-center">Tìm kiếm Hóa Đơn chưa có kết quả</h1>
	<!-- search -->
	<div class="form mt-4">
		<form id="searchForm">
			<div class="form-group">
				<div class="row">
					<select id="database" name="database" class="form-control" style="width: 180px; float: none; margin: 0 auto;">
						<option value="ts24" selected>TS24</option>
						<option value="tgdd">Thế giới di động</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm">
					<input type="datetime-local" class="form-control" id="fromdate" name="fromdate" />
				</div>
				<div class="col-sm">
					<input type="datetime-local" class="form-control" id="todate" name="todate"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm">
					<input type="text" class="form-control" id="matdiep" name="matdiep" placeholder="Mã thông điệp"/>
				</div>
			</div>
			<div class="text-center">
				<button class="btn btn-primary">Tra cứu</button>
			</div>
		</form>
	</div>
	<!-- table content -->
	<div class="mt-4">
		<table id="uTable" class="table display cell-border" style="width: 100%">
			<thead class="thead-light text-center">
				<tr>
					<th class="align-middle">Số thứ tự</th>
					<th class="align-middle">Mã thông điệp</th>
					<th class="align-middle">
						<button id="search-all" class="btn btn-primary">
							Seek Checked <span hidden class='search-all spinner-border spinner-border-sm'></span>
						</button>
					</th>
					<th class="align-middle"><input id="flowcheckall" type="checkbox" /></th>
				</tr>
			</thead>
		</table>
	</div>
</div>
