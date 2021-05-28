<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container text-center">
	<h1 class="mb-3">Incomplete</h1>
	<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th style="width: 10%">No</th>
				<th style="width: 30%">Word</th>
				<!-- <th style="width: 30%">Pronounce</th> -->
				<th style="width: 30%">Translate</th>
				<th style="width: 30%">Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty listData}">
					<c:forEach items="${listData}" var="vocab" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${vocab.word}</td>
							<!-- <td>${vocab.pronounce}</td> -->
							<td>${vocab.translate}</td>
							<td><button onclick="deleteWord('${vocab.word}')" class="btn btn-danger">Delete</button></td>
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
	function deleteWord(word) {
		$.ajax({
			type: "DELETE",
			url: "delete?word=" + word,
			//dataType: 'json',
			//contentType: "application/json",
			success: function (data) {
				console.log(data);
				location.reload();
			},
			error: function (e) {
				console.log("ERROR : ", e);
			}
		});
	}
</script>