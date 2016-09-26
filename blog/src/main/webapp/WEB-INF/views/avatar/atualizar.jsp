<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar avatar</title>

<link type="text/css" rel="stylesheet"
	href="<c:url value= "/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
		
		<c:url var="save" value="/avatar/update" />
		<form:form modelAttribute="avatar" action="${save}" method="POST" enctype="multipart/form-data">
			<form:hidden path="id" />
			<fieldset class="grupo">
				<legend>Editar Senha</legend>
				<div class="campo">
					<label>Avatar</label>
					<input type="file" name="file" required="true" />
				</div>
				<div>
					<input type="submit" value="Salvar" /> <input type="reset"
						value="limpar" />

				</div>


			</fieldset>
		</form:form>
		
</body>
</html>