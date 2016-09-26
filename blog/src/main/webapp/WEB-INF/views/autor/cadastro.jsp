<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro Autor</title>

<link type="text/css" rel="stylesheet" href="<c:url value= "/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
	<c:url var="save" value="/autor/save" />
	<form:form modelAttribute="autor" action="${save}" method="POST">
		
		<form:hidden path="id"/>
		<fieldset>
			<legend>Cadastro de Autor</legend>
			
			<div class="campo">
				<form:label path="nome">Nome do Autor</form:label><br>
				<form:input path="nome" required="true" title="Inserir Nome" type="text"/>
			</div><br>
			
			<div class="campo">
				<form:label path="biografia">Biografia</form:label><br>
				<form:textarea path="biografia" required="true" cols="59" rows="10"/>
			</div><br>
			
			<div>
				<input type="submit" value="Salvar" />
				<input type="reset" value ="Limpar" />
			</div>
			
		</fieldset>
		
		
	</form:form>

</body>
</html>