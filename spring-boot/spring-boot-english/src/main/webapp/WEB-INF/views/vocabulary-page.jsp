<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.page-item{
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
	<nav aria-label="Search results pages">
		<ul class="pagination pagination-sm">
			<li class="page-item">
				<a class="page-link" href="#">Prev</a>
			</li>
			<li class="page-item active">
				<a class="page-link" href="#">${page + 1}</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">${page + 2}</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">${page + 3}</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">${page + 4}</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">${page + 5}</a>
			</li>
			<li class="page-item">
				<a class="page-link three-dot" href="#">...</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">${total}</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">Next</a>
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
