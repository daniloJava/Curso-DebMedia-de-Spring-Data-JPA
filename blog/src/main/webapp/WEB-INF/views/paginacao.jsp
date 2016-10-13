<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div align="center">
	[
	<c:forEach var="p" begin="1" end="${page.totalPages }">
		<c:choose>
			<c:when test="${p - 1 eq page.number }">
				<label style="font-size: 18pt;"> ${p} </label>
			</c:when>
			<c:otherwise>
				<label> <a href="<c:url value="${urlPagination}/${p }" />"
					title="Go to ${p }"> ${p} </a>
				</label>
			</c:otherwise>
		</c:choose>

	</c:forEach>
	]
</div>