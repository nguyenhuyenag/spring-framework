<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

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
	<table id="example" class="table display cell-border" style="width: 100%">
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
</div>

<script type="text/javascript">
	$(function() {
		// $.getJSON("get-all-user", function(data) {console.log(data);});
		var table = $('#example').DataTable({
			"ajax" : {
				"url" : "get-all-user",
				"type" : "GET",
				"dataSrc": ""
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
                	"render": function (data, type, full, meta) {
                		let s = "Disable";
                		let cname = "btn btn-danger";
                		if (data != 0) {
                			s = "Active";
                			cname = "btn btn-success";
                		}
                		return '<button type="button" class="' + cname + '">' + s + '</button>';
					}	
                },
                {
					"render": function (data, type, full, meta) {
						return '<button type="button" class="btn btn-primary">Edit</button>';
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
		});
		$('#example tbody').on('click', 'tr', function () {
            var data = table.row(this).data();
            console.log(data);
        });
	});

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
