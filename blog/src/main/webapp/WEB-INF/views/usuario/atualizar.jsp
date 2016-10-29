<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Atualiza cadastro</title>

<link type="text/css" rel="stylesheet"
	href="<c:url value= "/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
	<fieldset class="grupo">
		<legend>Editar dados do perfil</legend>
		
		<c:url var="save" value="/usuario/update/senha" />
		<form:form modelAttribute="usuario" action="${save}" method="POST">
			<form:hidden path="id" />
			<fieldset class="grupo">
				<legend>Editar Senha</legend>
				<div class="campo">
					<form:label path="senha">Senha</form:label>
					<form:password path="senha"/>
					<form:errors path="senha" cssClass="error"> </form:errors>
				</div>
				<div>
					<input type="submit" value="Salvar" /> <input type="reset"
						value="limpar" />

				</div>


			</fieldset>
		</form:form>
		
		<c:url var="save" value="/usuario/update" />
		<form:form modelAttribute="usuario" action="${save}" method="POST">
			<form:hidden path="id" />
			<fieldset class="grupo">
				<legend>Editar Nome e Email</legend>

				<div class="campo">
					<form:label path="nome">Nome do Usuário</form:label>
					<br> 
					<form:input path="nome" title="Inserir Nome" type="text" value = "${nome}" />
					<form:errors path="nome" cssClass="error"> </form:errors>
				</div>
				<br>

				<div class="campo">
					<form:label path="email">E-mail</form:label>
					<br>
					<form:input path="email" type="email" value = "${email}" />
					<form:errors path="email" cssClass="error"> </form:errors>
				</div>
				<br>

				<div>
					<input type="submit" value="Salvar" /> <input type="reset"
						value="Limpar" />
				</div>
			
			</fieldset>


		</form:form>
	</fieldset>
</body>
</html>