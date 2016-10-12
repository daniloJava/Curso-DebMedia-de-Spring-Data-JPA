<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar os usuários</title>

<link type="text/css" rel="stylesheet" href="<c:url value= "/css/style.css" />">	

</head>

<body>
	<c:import url="../menu.jsp" />
	<fieldset>
		<legend>Lista de usuários</legend>
		<table class="table">
			<tr>
				<th>Avatar</th>
				<th>Nome do Usuário</th>
				<th>Email</th>
				<th>Data de Cadasrro</th>
				<th>Perfil</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="usuario" items="${page.content}" varStatus="i">
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
			</c:forEach>
		</table>
		<div align="center">
				[
				<c:forEach var="p" begin="1" end="${page.totalPages }">
					<c:choose>
						<c:when test="${p - 1 eq page.number }">
							<label style="font-size: 18pt;"> ${p} </label>
						</c:when>
						<c:otherwise>
							<label>
								<a href="<c:url value="/usuario/page/${p }" />" 
									title="Go to ${p }"> 
									${p}
								</a>
							</label>
						</c:otherwise>
					</c:choose>
									
				</c:forEach>
				]				
			</div>
		
	</fieldset>


</body>
</html>