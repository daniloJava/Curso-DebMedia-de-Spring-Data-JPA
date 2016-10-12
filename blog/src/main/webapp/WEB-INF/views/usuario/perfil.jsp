<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil</title>
<link type="text/css" rel="stylesheet" href="<c:url value= "/css/style.css" />">

</head>

<body>
	<c:import url="../menu.jsp" />
	<fieldset>
		<legend>Perfil</legend>
		<table class="table">
			<tr>
				<th>Avatar</th>
				<th>Nome do Usuário</th>
				<th>Email</th>
				<th>Data de Cadasrro</th>
				<th>Perfil</th>
				<th>Ação</th>
			</tr>
			<tr bgcolor="${i.count % 2 !=0 ? '#f1f1f1' : 'white'}">
				<td><a href='<c:url value="/avatar/update/${usuario.avatar.id}"></c:url>' title="Editar avatar">
					<img src='<c:url value= "/avatar/load/${usuario.avatar.id}" /> '
						style = "width: 40px; height: 40px;" /></a>
				</td>
				<td>${usuario.nome }</td>
				<td>${usuario.email }</td>
				<td>${usuario.dataCadastro }</td>
				<td>${usuario.perfil}</td>
				<td>
					<c:url var="update" value = "/usuario/update/${usuario.id}"></c:url>
					<a href="${update}" title="Editar">&#9445</a>
					<a href="#" title="Excluir">&#9447</a>
				</td>
			</tr>
			
		</table>
	</fieldset>


</body>
</html>