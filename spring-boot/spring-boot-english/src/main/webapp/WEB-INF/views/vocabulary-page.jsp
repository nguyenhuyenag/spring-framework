<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.page-item {
		padding: 0 3px 0 3px;
	}

	.three-dot {
		border: 0;
	}
</style>

<div class="container text-center">
	<h1 class="mb-3">Vocabulary</h1>
	<div id="feedback"></div>
	<pre>
	current page: ${page}
	total page: ${total}
</pre>
	<c:set var="MIN_SIZE" value="5" />
	<nav aria-label="Search results pages">
		<ul class="pagination pagination-sm">
			<li class="page-item">
				<a class="page-link" onclick="gotoPage('${page - 1}'); return false;" href="#">Prev</a>
			</li>
			<!-- chỉ có 5 trang-->
			<!-- <c:if test="${total <= MIN_SIZE}">
				<c:forEach var="i" begin="1" end="${total}">
					<li class="page-item page-${i}">
						<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
					</li>
				</c:forEach>
			</c:if> -->
			
			<!-- nhiều hơn 5 trang-->
			<c:if test="${total > MIN_SIZE}">
				<!-- (1) -->
				<li class="page-item page-1">
					<a class="page-link" onclick="gotoPage(1); return false;" href="#">${1}</a>
				</li>
				<!-- (1 -> 5) -->
				<c:if test="${page < MIN_SIZE}">
					<c:forEach var="i" begin="2" end="${MIN_SIZE}">
						<li class="page-item page-${i}">
							<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
						</li>
					</c:forEach>
					<li class="page-item">
						<a class="page-link three-dot" href="#">...</a>
					</li>
				</c:if>
				<!--  -->
				<c:if test="${MIN_SIZE <= page && page <= total - MIN_SIZE}">
					<li class="page-item">
						<a class="page-link three-dot" href="#">...</a>
					</li>
					<c:forEach var="i" begin="${page - 2}" end="${page + 2}">
						<li class="page-item page-${i}">
							<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
						</li>
					</c:forEach>
					<li class="page-item">
						<a class="page-link three-dot" href="#">...</a>
					</li>
				</c:if>
				<!-- middle -->
				<c:if test="${page > total - MIN_SIZE}">
					<li class="page-item">
						<a class="page-link three-dot" href="#">...</a>
					</li>
					<c:forEach var="i" begin="${total - MIN_SIZE}" end="${total - 1}">
						<li class="page-item page-${i}">
							<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
						</li>
					</c:forEach>
				</c:if>
				<!--  (total) -->
				<li class="page-item page-${total}">
					<a class="page-link" onclick="gotoPage('${total}'); return false;" href="#">${total}</a>
				</li>
			</c:if>
			<li class="page-item">
				<a class="page-link" onclick="gotoPage('${page + 1}'); return false;" href="#">Next</a>
			</li>
		</ul>
	</nav>
	<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th style="width: 10%">No</th>
				<th style="width: 30%">Word</th>
				<th style="width: 30%">Pronounce</th>
				<th style="width: 30%">Translate</th>
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
</div>

<script>
	$(function () {
		// current page & active page
		$('.page-' + '${page}').addClass("active");
	});

	function gotoPage(page) {
		window.location.replace("http://localhost:8080/vocabulary?page=" + page);
	}
</script>