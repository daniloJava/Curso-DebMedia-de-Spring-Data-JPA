<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home </title>

<link type="text/css" rel="stylesheet"
	href="<c:url value= "/css/style.css" />">

</head>
<body>
	<fieldset class="heder">
		<h1>Blogo do curso de Spring-Data JPA</h1>
	</fieldset>
	<c:import url="menu.jsp" />
	
	<fieldset>
		<c:forEach var="p" items="${postagens }">
			<div>
				<!-- Espaço do Titulo -->
				<div>
					<h2>${p.titulo}	</h2>
					<p> Autor: <a href="<c:url value="/autor/${p.autor.nome }" />"> ${p.autor.nome} </a> 
					| Data: ${p.postagem }</p>
				</div>
				
				<!-- Espaço do texto -->
				<div>
					<p>${p.texto}</p>
				
				</div>
				
				<!-- Espaço da categoria -->
				<div>
					<c:forEach var="c" items="${p.categorias }">
						<a href="<c:url value = "/categoria/"${c.descricao} />" title="${c.descricao}">">
						| ${c.descricao} </a>
					</c:forEach>
				</div>
				
			</div>
		
		</c:forEach>
		
	</fieldset>

	

</body>
</html>