<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<fieldset>
	<legend>Menu</legend>
	
	<nav>
		<a href="<c:url value="/"/>"> HOME </a>
		 
	</nav>
	
	<nav>
		<a href="<c:url value="/usuario/add"/>"> Add Usuario </a>
		<a href="<c:url value="/usuario/list"/>"> Lista usuario </a>
		 
	</nav>
	<nav>
		<a href="<c:url value="/autor/add"/>"> Add autor </a>
		<a href="<c:url value="/autor/list"/>"> Lista Autores </a>
		 
	</nav>
	<nav>
		<a href="<c:url value="/postagem/add"/>"> Add Postagem</a>
		<a href="<c:url value="/postagem/list"/>"> Lista postagens </a>
		<a href="<c:url value="/postagem/ajax/add"/>"> Add Postagem Ajax</a>
	</nav>
	<nav>
		<a href="<c:url value="/categoria/add"/>"> Categorias</a>
	</nav>
	
	<nav>
		<a href="<c:url value="/auth/form"/>"> Entrar</a>
		<a href=""> Cadastro-se</a>
		<form action="<c:url value="/logout"/>" method="post">
			<security:csrfInput/> <!-- Adiciona os hiddne loco de configuração do Spring -->
			<button type="submit">Sair </button>
		</form>
	</nav>
	
	

</fieldset>

