<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar os Postagens</title>

<link type="text/css" rel="stylesheet" href="<c:url value= "/css/style.css" />">	

</head>

<body>
	<c:import url="../menu.jsp" />
	<fieldset>
		<legend>Lista de Postagens</legend>
		<table class="table">
			<tr>
				<th>Código</th>
				<th>Titulo do Post</th>
				<th>PermaLink</th>
				<th>Data de Postagem</th>
				<th>Autor</th>
				<th>Categorias</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="postagem" items="${page.content}" varStatus="i">
			<tr bgcolor="${i.count % 2 !=0 ? '#f1f1f1' : 'white'}">
				
				<td>${postagem.id }</td>
				<td>${postagem.titulo }</td>
				<td>${postagem.permaLink }</td>
				<td>
					<fmt:parseDate var = "date" value="${postagem.dataPostagem }" pattern="yyyy-MM-dd'T'HH:mm:ss" />
					<fmt:formatDate value="${date}" type="both"/>
				</td>
				<td>${postagem.autor.nome}</td>
				<td>
					<c:forEach var="c" items="${postagem.categorias }" >
						[${c.descricao}]
					</c:forEach>
				</td>
				<td>
					<c:url var="update" value = "/postagem/update/${postagem.id }"></c:url>
					<a href="${update}" title="Editar">&#9445</a>
					<c:url var="delete" value = "/postagem/delete/${postagem.id }"></c:url>
					<a href="${delete}" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div align="center">
			[
			<c:forEach var="p" begin="1" end="${page.totalPages}">
				<c:choose>
					<c:when test="${(p-1) eq page.number }">
						<label style="font-size: 18pt;">${p }</label>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/postagem/page/${p }"/>" title="Go to ${p }">
						${p }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			]
		</div>
		
	</fieldset>


</body>
</html>