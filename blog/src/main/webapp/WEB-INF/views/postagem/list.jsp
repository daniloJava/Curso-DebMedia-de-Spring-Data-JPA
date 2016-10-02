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
				<th>Ação</th>
			</tr>
			<c:forEach var="postagem" items="${postagem}" varStatus="i">
			<tr bgcolor="${i.count % 2 !=0 ? '#f1f1f1' : 'white'}">
				
				<td>${postagem.id }</td>
				<td>${postagem.titulo }</td>
				<td>${postagem.permaLink }</td>
				<td>${postagem.dataPostagem }</td>
				<td>${postagem.autor.nome}</td>
				<td>
					<c:url var="update" value = "/postagem/update/${postagem.id }"></c:url>
					<a href="${update}" title="Editar">&#9445</a>
					<c:url var="delete" value = "/postagem/delete/${postagem.id }"></c:url>
					<a href="${delete}" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</fieldset>


</body>
</html>