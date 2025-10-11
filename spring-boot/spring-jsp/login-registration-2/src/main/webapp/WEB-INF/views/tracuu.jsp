<%@ page contentType="text/html;charset=UTF-8" %>
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
	<h1 class="text-center">Nhập thông tin cần tra cứu</h1>
    <!-- search -->
    <div class="form mb-4" id="search-form">
		<form class="pd-3">
			<!-- Ma so BHXH -->
			<div class="form-outline mb-2">
				<input type="text" class="form-control" name='masobhxh' id='masobhxh' placeholder="Mã số BHXH" />
			</div>
			<div class="form-outline mb-2">
				<input type="text" class="form-control" name='hoten' id='hoten' placeholder="Họ tên" />
			</div>
			<div class="form-outline mb-2">
				<input type="text" class="form-control" name='diachilh' id='diachilh' placeholder="Địa chỉ liên hệ" />
			</div>
			<button type="submit" class="btn btn-primary btn-block bg-color">Tra cứu</button>
		</form>
	</div>
    <!-- table-->
    <table class="table table-bordered text-center" id="result-table">
		<thead class="thead-light">
			<tr>
				<th>STT</th>
				<th>Mã số BHXH</th>
				<th>Họ tên</th>
				<th>Địa chỉ liên hệ</th>
				<th>Số điện thoại</th>
				<th>Họ tên người liên hệ</th>
			</tr>
		</thead>
		<tbody id="result-table-body"></tbody>
	</table>
</div>

<script type="text/javascript">
    $(function () {
    	noRecord();
        $("#search-form").submit(function (event) {
            event.preventDefault();
            search();
        });
    });

    function search() {
    	let hoten = $("#hoten").val();
    	let masobhxh = $("#masobhxh").val();
    	let diachilh = $("#diachilh").val();
		
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "tracuu?masobhxh=" + masobhxh + "&hoten=" + hoten + "&diachilh=" + diachilh,
            success: function (data) {
            	console.log(data);
	            $("#result-table-body").empty();
            	if (data != null && data.length > 0) {
	            	$.each(data, function (i, item) {
	                    $('<tr>').append(
	                    	$('<td>').text(i + 1),
	                    	$('<td>').text(item.masobhxh),
	                    	$('<td>').text(item.hoten),
	                    	$('<td>').text(item.diachilh),
	                    	$('<td>').text(item.sodienthoai),
	                    	$('<td>').text(item.hotennguoilh)
	                    ).appendTo('#result-table');
	                });
            	} else {
            		noRecord();
            	}
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }
    
    function noRecord() {
    	$('<tr>').append(
			$('<td class="text-danger" colspan="6">').text("Không có kết quả!")
		).appendTo('#result-table');
	}
</script>
