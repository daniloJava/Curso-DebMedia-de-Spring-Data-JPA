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
	<c:url var="save" value="/usuario/save?${_csrf.parameterName}=${_csrf.token}" />
	<form:form modelAttribute="usuario" action="${save}" method="POST"
		enctype="multipart/form-data">
		
		<form:hidden path="id"/>
		<fieldset>
			<legend>Cadastro de Usuário</legend>
			
			<div class="campo">
				<form:label path="nome">Nome do Usuário</form:label><br>
				<form:input path="nome" title="Inserir Nome" type="text"/>
				<form:errors path="nome" cssClass="error"> </form:errors>
			</div><br>
			
			<div class="campo">
				<form:label path="email">E-mail</form:label><br>
				<form:input path="email" type="email"/>
				<form:errors path="email" cssClass="error"> </form:errors>
			</div><br>
			
			<div class="campo">
				<form:label path="senha">Senha</form:label><br>
				<form:password path="senha" />
				<form:errors path="senha" cssClass="error"> </form:errors>
			</div><br>
			
			<div>
				<form:label path="file">Avatar</form:label><br>
				<form:input type="file" path="file" />
				<form:errors path="file" cssClass="error"> </form:errors>
				
			</div><br>
			
			<div>
				<input type="submit" value="Salvar" />
				<input type="reset" value ="Limpar" />
			</div>
			
		</fieldset>
		
		
	</form:form>

</body>
</html>