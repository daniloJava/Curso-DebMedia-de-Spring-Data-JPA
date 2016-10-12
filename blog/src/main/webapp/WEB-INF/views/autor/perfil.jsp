<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil do Autor</title>
<link type="text/css" rel="stylesheet" href="<c:url value= "/css/style.css" />">

</head>

<body>
	<c:import url="../menu.jsp" />
	<fieldset>
		<legend>Perfil do Autor</legend>
		<table class="table">
			<tr>
				
				<th>Nome do Autor</th>
				<th>Email</th>
				<th>Data de Cadasrro</th>
				<th>Biografia</th>
				<th>Ação</th>
			</tr>
			<!-- o ternario é uma veriiação se existe uma paginação ou não vindo do Controller -->
			<c:forEach var="autor" 
				items="${page.content != null ? page.content : autores}" varStatus="i">
			<tr bgcolor="${i.count % 2 != 0 ? '#f1f1f1' : 'white'}">
				<td>${autor.nome}</td>
				<td>${autor.usuario.email}</td>
				<td>${autor.usuario.dataCadastro}</td>
				<td>${autor.biografia}</td>
				<td>
					<c:url var="update" value="/autor/update/${autor.id}"/>
					<a href="${update}" title="Editar">&#9445</a>
					<c:url var="delete" value="/autor/delete/${autor.id}"/>
					<a href="${delete}" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<c:if test="${page != null}">
		<div align="center">
		[
			<c:forEach var="p" begin="1" end="${page.totalPages }">
				<c:choose>
					<c:when test="${ (p-1) eq page.number}">
						<label style="font-size: 18pt;">${p }</label>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/autor/page/${p}"/>" >${p}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		]
		</div>
		</c:if>
		
	</fieldset>


</body>
</html>