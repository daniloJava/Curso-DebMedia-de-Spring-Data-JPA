<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Usuário</title>

<link type="text/css" rel="stylesheet" href="<c:url value= "/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
	<c:url var="save" value="/usuario/save" />
	<form:form modelAttribute="usuario" action="${save}" method="POST"
		enctype="multipart/form-data">
		
		<form:hidden path="id"/>
		<fieldset>
			<legend>Cadastro de Usuário</legend>
			
			<div class="campo">
				<form:label path="nome">Nome do Usuário</form:label><br>
				<form:input path="nome" required="true" title="Inserir Nome" type="text"/>
			</div><br>
			
			<div class="campo">
				<form:label path="email">E-mail</form:label><br>
				<form:input path="email" required="true" type="email"/>
			</div><br>
			
			<div class="campo">
				<form:label path="senha">Senha</form:label><br>
				<form:password path="senha" required="true" />
			</div><br>
			
			<div>
				<label for="file">Avatar</label><br>
				<input type="file" name="file" required = "true"/>
				
			</div><br>
			
			<div  class="campo">
				<form:label path="perfil">Avatar</form:label><br>
				<form:select path="perfil" required="true">
				
					<form:option value="ADIMIN" label="ADIMIN" />
					<form:option value="AUTOR" label="AUTOR" />
					<form:option value="LEITOR" label="LEITOR" />
				
				</form:select>
			</div><br>
			<div>
				<input type="submit" value="Salvar" />
				<input type="reset" value ="Limpar" />
			</div>
			
		</fieldset>
		
		
	</form:form>

</body>
</html>