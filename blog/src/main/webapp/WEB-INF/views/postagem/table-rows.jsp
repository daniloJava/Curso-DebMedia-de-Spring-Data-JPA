<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<thead>
	<tr>
		<th>Código</th>
		<th>Titulo do Post</th>
		<th>PermaLink</th>
		<th>Data de Postagem</th>
		<th>Autor</th>
		<th>Categorias</th>
		<th>Ação</th>
	</tr>
</thead>
<tbody>
<c:forEach var="postagem" items="${page.content}" varStatus="i">
	<tr bgcolor="${i.count % 2 !=0 ? '#f1f1f1' : 'white'}">

		<td>${postagem.id }</td>
		<td>${postagem.titulo }</td>
		<td>${postagem.permaLink }</td>
		<td><fmt:parseDate var="date" value="${postagem.dataPostagem }"
				pattern="yyyy-MM-dd'T'HH:mm" /> 
			<fmt:formatDate value="${date}"	type="both" /></td>
		<td>${postagem.autor.nome}</td>
		<td><c:forEach var="c" items="${postagem.categorias }">
						[${c.descricao}]
					</c:forEach></td>
		<td><c:url var="update" value="/postagem/update/${postagem.id }"></c:url>
			<a href="${update}" title="Editar">&#9445</a> <c:url var="delete"
				value="/postagem/delete/${postagem.id }"></c:url> <a
			href="${delete}" title="Excluir">&#9447</a></td>
	</tr>
</c:forEach>
</tbody>
<tfoot>
	<tr>
		<th colspan="7">
		<c:forEach var="p" begin="1" end="${page.totalPages}">
				<c:choose>
					<c:when test="${ (p - 1) eq page.number }">
						<button id="button_${p}" disabled="disabled" value="${p}">${p}</button>
					</c:when>
					<c:otherwise>
						<button id="button_${p}" value="${p}">${p}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach></th>

	</tr>

</tfoot>