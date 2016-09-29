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
			<c:forEach var="autor" items="autores" varStatus="i">
			<tr bgcolor="">
				
				<td>${autor.nome }</td>
				<td>${autor.usuario.email }</td>
				<td>${autor.usuario.dataCadastro }</td>
				<td>${autor.biografia}</td>
				<td>
					<c:url var="update" value = "/autor/update/${autor.id}"></c:url>
					<a href="${update}" title="Editar">&#9445</a>
					<a href="#" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</fieldset>


</body>
</html>