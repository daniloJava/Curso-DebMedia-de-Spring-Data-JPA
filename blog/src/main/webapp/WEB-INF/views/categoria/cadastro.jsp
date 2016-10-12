<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>

<link type="text/css" rel="stylesheet"
	href="<c:url value= "/css/style.css" />">

</head>
<body>
	<fieldset>
		<c:import url="../menu.jsp" />
		<fieldset>
			<c:url var="save" value="/categoria/save" />
			<form:form modelAttribute="categoria" action="${save}" method="POST"
				enctype="multipart/form-data">

				<form:hidden path="id" />
				<fieldset>
					<legend> Cadastro de Categoria</legend>
					<div class="campo">
						<form:label path="descricao">Descrição da Categoria</form:label>
						<br>
						<form:input path="descricao" type="text" required="true" size="60" />
					</div>

					<div>
						<input type="submit" value="Salvar"> <input type="reset"
							value="Limpar">
					</div>
				</fieldset>

			</form:form>
		</fieldset>

		<fieldset class="grupo">
			<legend>Lista de Categorias</legend>
			<table class="table">
				<tr>
					<th>Código</th>
					<th>Descrição</th>
					<th>PermaLink</th>
					<th>Ação</th>
				</tr>
				<c:forEach var="categoria" items="${page.content}" varStatus="i">
					<tr bgcolor="${i.count % 2 !=0 ? '#f1f1f1' : 'white'}">

						<td>${categoria.id }</td>
						<td>${categoria.descricao }</td>
						<td>${categoria.permaLink }</td>
						<td><c:url var="update"
								value="/categoria/update/${categoria.id }"></c:url> <a
							href="${update}" title="Editar">&#9445</a> <c:url var="delete"
								value="/categoria/delete/${categoria.id }"></c:url> <a
							href="${delete}" title="Excluir">&#9447</a></td>
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
								<a href="<c:url value="/categoria/page/${p }" />" 
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
	</fieldset>
</body>
</html>