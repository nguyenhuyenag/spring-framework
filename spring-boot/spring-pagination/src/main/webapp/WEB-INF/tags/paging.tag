<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageUrl" required="true" type="java.lang.String"%>
<%@ attribute name="pageList" required="true" type="org.springframework.beans.support.PagedListHolder" %>

<%
	int currentPage = pageList.getPage();
	int pageSize = pageList.getPageSize();
%>

<p>pageSize: <%=pageSize%></p>
<p>pageUrl: <c:out value="${pageUrl}" /></p>

<ul class="pagination">
	<c:if test="${!pageList.firstPage}">
		<li class="previous">
			<a href='<%=pageUrl.replace("page_number", String.valueOf(currentPage))%>'>&lt;</a>
		</li>
	</c:if>
	
	<c:if test="${pageList.firstLinkedPage > 0}">
		<li><a href='<%=pageUrl.replace("page_number", "0")%>'>1</a></li>
	</c:if>
	
	<c:if test="${pageList.firstLinkedPage > 1}">
		<li><span class="pagingDots">...</span><li>
	</c:if>
	
	<c:forEach begin="${pageList.firstLinkedPage}" end="${pageList.lastLinkedPage}" var="i">
		<c:choose>
			<c:when test="${pageList.page == i}">
				<li class="active"><a href="#">${i+1}</a></li>
			</c:when>
			<c:otherwise>
				<li>
					<a href="<%=pageUrl.replace("page_number", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${pageList.lastLinkedPage < pageList.pageCount - 2}">
		<li><span class="pagingDots">...</span></li>
	</c:if>
	
	<c:if test="${pageList.lastLinkedPage < pageList.pageCount - 1}">
		<li>
			<a href="<%=pageUrl.replace("page_number", String.valueOf(pageList.getPageCount() - 1))%>">${pageList.pageCount}</a>
		</li>
	</c:if>
	
	<c:if test="${!pageList.lastPage}">
		<li class="next">
			<a href="<%=pageUrl.replace("page_number", String.valueOf(currentPage + 1))%>">&#707;</a>
		</li>
	</c:if>
</ul>
