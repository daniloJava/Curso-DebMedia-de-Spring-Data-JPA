<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>inserir Postagem</title>

<link type="text/css" rel="stylesheet"
	href="<c:url value= "/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
	<c:url var="save" value="/postagem/save" />
	<form:form modelAttribute="postagem" action="${save}" method="POST"
		enctype="multipart/form-data">

		<form:hidden path="id" />
		<fieldset>
			<legend> Cadastro de Postagem</legend>
			<div class="campo">
				<form:label path="titulo">Titulo do Post</form:label>
				<br>
				<form:input path="titulo" type="text" required="true" size="60" />
			</div>
			<div class="campo">
				<form:label path="texto">Texto do Post</form:label>
				<br>
				<form:textarea path="texto" rows="15" cols="80" />
			</div>
			<div class="campo">
				<form:label path="categorias">Selecione a(s) Categorias</form:label><br>
				<form:select multiple="true" path="categorias"> 
					<form:options items="${categorias }" itemValue="id" itemLabel="descricao" />
				</form:select>
			</div>
			
			<div>
				<input type="submit" value="Salvar"> <input type="reset"
					value="Limpar">
			</div>
		</fieldset>


	</form:form>

</body>
</html>