<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="comentarios">

	<security:authorize access="hasAnyAuthority('ADIMIN', 'AUTOR', 'LEITOR')">
	<c:url var="save" value="/comentario/save" />
	<form:form action="${save }" modelAttribute="comentario" method="POST">
		<input type="hidden" value="${postagem.permaLink }" name= "permaLink">
		<div>
			<form:label path="texto">Digitee seu comentario</form:label>
			<form:textarea path="texto" rows="5" cols="80"/>
			<form:errors path="texto" cssClass="error"/>

		</div>
		<div>
				<input type="submit" value="Salvar" />
				<input type="reset" value ="Limpar" />
			</div>
	</form:form>
	</security:authorize>
	<hr>
	<c:forEach var="c" items="${postagem.comentarios }">
		<div class="comentarios">
			<img class="comentarios-avatar" src="<c:url value="/avatar/load/${c.usuario.avatar.id}"/>"/>
			<em>
				${c.usuario.nome } - 
				<fmt:parseDate var="date" value="${c.dataComentario}" pattern="yyyy-MM-dd'T'HH:mm" />
					Data: <fmt:formatDate value="${date }" type="both" />
			</em>
			<p>${c.texto}</p>
		</div>
	</c:forEach>
	
</div>