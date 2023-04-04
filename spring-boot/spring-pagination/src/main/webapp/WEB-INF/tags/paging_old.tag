<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageUrl" required="true" type="java.lang.String"%>
<%@ attribute name="pagedList" required="true" type="org.springframework.beans.support.PagedListHolder" %>

<%
	int currentPage = pagedList.getPage();
	int pageSize = pagedList.getPageSize();
%>

<p>pageSize: <%=pageSize%></p>
<p>pageUrl: <c:out value="${pageUrl}" /></p>

<c:if test="${pagedList.pageCount > 1}">
	<ul class="pagination">
		<c:if test="${!pagedList.firstPage}">
			<li class="previous">
			</li>
			<a href='<%=pageUrl.replace("page_number", String.valueOf(currentPage - 1))%>'>&lt;</a>
		</c:if>
		<c:if test="${pagedList.firstLinkedPage > 0}">
			<li><a href="<%=pageUrl.replace("page_number", "0")%>">1</a></li>
		</c:if>
		<c:if test="${pagedList.firstLinkedPage > 1}">
			<li><span class="pagingDots">...</span><li>
		</c:if>
		<c:forEach begin="${pagedList.firstLinkedPage}" end="${pagedList.lastLinkedPage}" var="i">
			<c:choose>
				<c:when test="${pagedList.page == i}">
					<li class="active"><a href="#">${i+1}</a></li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="<%=pageUrl.replace("page_number", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pagedList.lastLinkedPage < pagedList.pageCount - 2}">
			<li><span class="pagingDots">...</span></li>
		</c:if>
		<c:if test="${pagedList.lastLinkedPage < pagedList.pageCount - 1}">
			<li>
				<a href="<%=pageUrl.replace("page_number", String.valueOf(pagedList.getPageCount() - 1))%>">${pagedList.pageCount}</a>
			</li>
		</c:if>
		<c:if test="${!pagedList.lastPage}">
			<li class="next">
				<a href="<%=pageUrl.replace("page_number", String.valueOf(currentPage + 1))%>">&#707;</a>
			</li>
		</c:if>
	</ul>
</c:if>
