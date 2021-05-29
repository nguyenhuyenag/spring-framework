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
	<h1 class="mb-3 text-center">Vocabulary</h1>
	<div id="feedback"></div>
	<c:set var="PAGE_INCR" value="5" />
	<!-- pagination -->
	<nav aria-label="Search results pages">
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
							<td>${vocab.pronounce}</td>
							<td>${vocab.translate}</td>
							<td>
								<button onclick="editV('${vocab.word}', '${vocab.pronounce}', '${vocab.translate}')" class="btn btn-success" data-toggle="modal"
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
	<div class="modal fade" id="editModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit Data</h5>
					<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				</div>
				<div class="modal-body" id="attachment-body-content">
					<form id="edit-form">
						<div class="form-group">
							<label class="col-form-label" for="form-word"><strong>Word</strong></label>
							<input name="form-word" class="form-control" id="form-word" disabled>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="form-pronounce"><strong>Pronounce</strong></label>
							<input name="form-pronounce" class="form-control" id="form-pronounce" required>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="form-translate"><strong>Translate</strong></label>
							<input name="form-translate" class="form-control" id="form-translate" required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	var total = parseInt('${TOTAL}');
	var currentPage = parseInt('${CURRENT_PAGE}');

	$(function () {
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

	document.onkeydown = function (e) {
		switch (e.key) {
			case 'ArrowLeft':
				gotoPage(currentPage - 1);
				break;
			case 'ArrowRight':
				gotoPage(currentPage + 1);
				break;
			default:
			// console.log(e.key);
		}
	}

	function editV(word, pronounce, translate) {
		var form = document.forms['edit-form'];
		form['form-word'].value = word;
		form['form-pronounce'].value = pronounce;
		form['form-translate'].value = translate;
	}
</script>
