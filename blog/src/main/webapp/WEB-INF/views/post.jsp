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
					<h2>
						<a href="<c:url value = "/${p.permalink}"/>">
							${postagens.titulo}
						</a>
					</h2>
					
					<p> Autor: 
					<a href="<c:url value="/autor/${postagens.autor.nome }" />"> 
						${postagens.autor.nome} 
					</a> 
					
					|<fmt:parseDate var="date" va{p.dataPostagem}" pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ" /> 
						Data: <fmt:formatDate value="${date }" type="both" /> 
					</p>
					
				</div>
				
				<!-- Espaço do texto -->
				<div>
					<p >
						<c:forTokens var="resumo" items="${p.texto}" delims=" " begin="0"
							end="40">
							${resumo}
						</c:forTokens>
						<a href="<c:url value = "/${p.permalink}"/>">
							[Leia Mais] </a>
					</p>

				</div>

				<!-- Espaço da categoria -->
				<div>
					<p class="post-categ">
					<span>Categorias: </span>
					<c:forEach var="c" items="${p.categorias }">
						<a href="<c:url value = "/categoria/"${c.descricao} />" title="${c.descricao}">">
						| ${c.descricao} </a>
					</c:forEach>
					</p>
				</div>
				
			</div>
		
		</c:forEach>
		
	</fieldset>

	

</body>
</html>