<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<fieldset>
	<legend>Menu</legend>
	
	<nav>
		<a href="<c:url value="/"/>"> HOME </a>
		 
	</nav>
	
	<nav>
		
		<security:authorize access="hasAnyAuthority('ADIMIN', 'AUTOR', 'LEITOR')">
		|	<a href="<c:url value="/usuario/perfil/${logado.id }"/>"> Perfil do usuario </a>
		</security:authorize>
		
		<security:authorize access="hasAuthority('ADIMIN')">
		|	<a href="<c:url value="/usuario/list"/>"> Lista de usuario </a>
		</security:authorize>
		 
	</nav>
	<nav>
		<security:authorize access="hasAuthority('AUTOR')">
		|	<a href="<c:url value="/autor/add"/>"> Dados do  autor </a>
		</security:authorize>
		
		<security:authorize access="hasAuthority('ADIMIN')">
		|	<a href="<c:url value="/autor/list"/>"> Lista de Autores </a>
		</security:authorize>
		
	</nav>
	<nav>
		<security:authorize access="hasAuthority('AUTOR')">
		|	<a href="<c:url value="/postagem/add"/>"> Nova Postagem</a>
		|	<a href="<c:url value="/postagem/ajax/add"/>"> Nova Postagem Ajax</a>
		|   <a href="<c:url value="/postagem/list/${logado.id }"/>"> Lista de Postagens </a>
		</security:authorize>
		<security:authorize access="hasAuthority('ADIMIN')">
		|	<a href="<c:url value="/postagem/list"/>"> Lista de Postagens </a>
		</security:authorize>
	</nav>
	<nav>
		<security:authorize access="hasAnyAuthority('ADIMIN', 'AUTOR')">
		|	<a href="<c:url value="/categoria/add"/>"> Categorias</a>
		</security:authorize>
	</nav>
	
	<nav class= "login">
		
		<c:if test="${logado == null }">
		|	<a href="<c:url value="/auth/form"/>"> Entrar</a>
			
		|	<a href="<c:url value="/usuario/add"/>"> Cadastro-se </a>
		</c:if>
		
		<security:authorize access="hasAnyAuthority('ADIMIN', 'AUTOR', 'LEITOR')">
			<form action="<c:url value="/logout"/>" method="post">
				<security:csrfInput/> <!-- Adiciona os hiddne loco de configuração do Spring -->
				<button type="submit">Sair </button>
			</form>
		</security:authorize>
	</nav>
	
	

</fieldset>

