<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.page-item {
		padding: 0 3px 0 3px;
	}
	.three-dot {
		border: 0;
	}
</style>

<div class="container">
	<!-- <h1 class="mb-3 text-center">Vocabulary</h1> -->
	<div id="feedback"></div>
	<c:set var="PAGE_INCR" value="5" />
	<div class="clearfix">
		<!-- pagination -->
		<nav class="float-left">
			<ul class="pagination pagination-sm">
				<!--  (previous) -->
				<li class="page-item prev">
					<a class="page-link" onclick="gotoPage('${CURRENT_PAGE - 1}'); return false;" href="#">Prev</a>
				</li>
				<!-- (1) -->
				<li class="page-item page-1">
					<a class="page-link" onclick="gotoPage('1'); return false;" href="#">${1}</a>
				</li>
				<!-- không quá 6 trang-->
				<c:if test="${TOTAL <= PAGE_INCR + 1}">
					<c:forEach var="i" begin="2" end="${TOTAL - 1}">
						<li class="page-item page-${i}">
							<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
						</li>
					</c:forEach>
				</c:if>
				<!-- nhiều hơn 6 trang-->
				<c:if test="${TOTAL > PAGE_INCR + 1}">
					<c:choose>
						<c:when test="${CURRENT_PAGE < PAGE_INCR}">
							<c:set var="BEGIN_VALUE" value="2" />
							<c:set var="END_VALUE" value="${PAGE_INCR}" />
						</c:when>
						<c:when test="${TOTAL - PAGE_INCR + 1 < CURRENT_PAGE}">
							<c:set var="BEGIN_VALUE" value="${TOTAL - PAGE_INCR + 1}" />
							<c:set var="END_VALUE" value="${TOTAL - 1}" />
						</c:when>
						<c:otherwise>
							<c:set var="BEGIN_VALUE" value="${CURRENT_PAGE - 2}" />
							<c:set var="END_VALUE" value="${CURRENT_PAGE + 2}" />
						</c:otherwise>
					</c:choose>
					<c:if test="${CURRENT_PAGE >= PAGE_INCR || CURRENT_PAGE >= TOTAL - PAGE_INCR}">
						<li class="page-item">
							<span class="page-link three-dot">...</span>
						</li>
					</c:if>
					<c:forEach var="i" begin="${BEGIN_VALUE}" end="${END_VALUE}">
						<li class="page-item page-${i}">
							<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
						</li>
					</c:forEach>
					<c:if test="${CURRENT_PAGE <= PAGE_INCR || CURRENT_PAGE <= TOTAL - PAGE_INCR + 1}">
						<li class="page-item">
							<span class="page-link three-dot">...</span>
						</li>
					</c:if>
				</c:if>
				<!--  (end) -->
				<li class="page-item page-${TOTAL}">
					<a class="page-link" onclick="gotoPage('${TOTAL}'); return false;" href="#">${TOTAL}</a>
				</li>
				<!--  (next) -->
				<li class="page-item next">
					<a class="page-link" onclick="gotoPage('${CURRENT_PAGE + 1}'); return false;" href="#">Next</a>
				</li>
			</ul>
		</nav>

		<div class="float-right">
			<form class="form-inline mt-2 mt-md-0 float-right" id="search-form">
				<input type="text" id="word" required class="form-control mr-sm-2" placeholder="Search">
				<button id="bth-search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</div>
	<!-- table content -->
	<table class="table table-bordered text-center">
		<thead class="thead-light">
			<tr>
				<th style="width: 20%">No</th>
				<th style="width: 20%">Word</th>
				<th style="width: 20%">Pronounce</th>
				<th style="width: 20%">Translate</th>
				<th style="width: 20%">Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty listData}">
					<c:forEach items="${listData}" var="vocab" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${vocab.word}</td>
							<td id="tb-pronounce-${vocab.id}">${vocab.pronounce}</td>
							<td id="tb-translate-${vocab.id}">${vocab.translate}</td>
							<td>
								<button onclick="openModal('${vocab.word}', '${vocab.pronounce}', '${vocab.translate}')" class="btn btn-success" data-toggle="modal"
									data-target="#editModal">
									Edit <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4"><span class="text-danger">No result</span></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<!-- Edit Modal -->
	<div class="modal fade" data-backdrop="static" data-keyboard="true" tabindex="-1" id="editModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-light">
					<h5 class="modal-title">Edit Data</h5>
					<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				</div>
				<div class="modal-body" id="attachment-body-content">
					<form id="editForm">
						<div class="form-group">
							<label class="col-form-label" for="word"><strong>Word</strong></label>
							<input name="word" class="form-control" id="word" disabled>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="pronounce"><strong>Pronounce</strong></label>
							<input name="pronounce" class="form-control" id="pronounce" required>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="translate"><strong>Translate</strong></label>
							<input name="translate" class="form-control" id="translate" required>
						</div>
					</form>
				</div>
				<div class="modal-footer bg-light">
					<button id="btn-save" type="button" onclick="save();" class="btn btn-primary">
						Save <span id="icon-loading" class="spinner-border spinner-border-sm"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	var total = parseInt('${TOTAL}');
	var currentPage = parseInt('${CURRENT_PAGE}');

	$(function () {
		handleRequiredMessage('Nhập từ khóa cần tìm');
		$('.page-' + currentPage).addClass('active');

		switch (currentPage) {
			case 1:
				$('.prev').addClass('disabled');
				break;
			case total:
				$('.next').addClass('disabled');
				break;
			default:
			// console.log('do something!');
		}
	});

	function gotoPage(page) {
		window.location.replace("http://localhost:8080/vocabulary?page=" + page);
	}

	// document.onkeydown = function (e) {
	// 	switch (e.key) {
	// 		case 'ArrowLeft':
	// 			gotoPage(currentPage - 1);
	// 			break;
	// 		case 'ArrowRight':
	// 			gotoPage(currentPage + 1);
	// 			break;
	// 		default:
	// 		// console.log(e.key);
	// 	}
	// }

	function openModal(word, pronounce, translate) {
		$('#icon-loading').hide();
		$('#btn-save').removeClass('disabled');
		var form = document.forms['editForm'];
		form['word'].value = word;
		form['pronounce'].value = pronounce;
		form['translate'].value = translate;
	}

	function save() {
		$('#icon-loading').show();
		$('#btn-save').addClass('disabled');
		
		var obj = {};
		var elements = document.getElementById("editForm").elements;
		for (var i = 0; i < elements.length; i++) {
			var item = elements.item(i);
			obj[item.name] = item.value;
		}
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "update",
			dataType : 'json',
			data : JSON.stringify(obj),
			success : function(data) {
				if (StringUtils.isNotEmpty(data)) {
					$('#tb-pronounce-' + data.id).text(data.pronounce);
					$('#tb-translate-' + data.id).text(data.translate);
				}
				setTimeout(function() {
					$('#editModal').modal('hide');
				}, 500);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
</script>
