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
	current page: ${CURRENT_PAGE}
	TOTAL page: ${TOTAL}
</pre>
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
			<!-- three dots -->
			<c:if test="${CURRENT_PAGE >= PAGE_INCR || page >= TOTAL - PAGE_INCR}">
				<li class="page-item">
					<a class="page-link three-dot" href="#">...</a>
				</li>
			</c:if>
			
			<!-- không quá 5 trang-->
			<!-- <c:if test="${TOTAL <= PAGE_INCR}">
				<c:forEach var="i" begin="2" end="${TOTAL - 1}">
					<li class="page-item page-${i}">
						<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
					</li>
				</c:forEach>
			</c:if> -->
			
			<!-- nhiều hơn 5 trang-->
			<c:if test="${TOTAL > PAGE_INCR}">
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
				<c:forEach var="i" begin="${BEGIN_VALUE}" end="${END_VALUE}">
					<li class="page-item page-${i}">
						<a class="page-link" onclick="gotoPage('${i}'); return false;" href="#">${i}</a>
					</li>
				</c:forEach>
			</c:if>
			
			<!-- three dots -->
			<c:if test="${CURRENT_PAGE <= PAGE_INCR || CURRENT_PAGE <= TOTAL - PAGE_INCR + 1}">
				<li class="page-item">
					<a class="page-link three-dot" href="#">...</a>
				</li>
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
		var total = '${TOTAL}';
		var currentPage = '${CURRENT_PAGE}';
		
		$('.page-' + currentPage).addClass('active');
		
		switch(currentPage) {
			case '1':
				$('.prev').addClass('disabled');
				break;
			case total:
				$('.next').addClass('disabled');
				break;
			default:
				console.log('do something!');
		}
	});

	function gotoPage(page) {
		window.location.replace("http://localhost:8080/vocabulary?page=" + page);
	}
</script>
