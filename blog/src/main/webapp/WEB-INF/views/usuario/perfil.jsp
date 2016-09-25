<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil</title>

<style type="text/css">
.campo {
	margin-bottom: 1em;
}

.campo input:FOCUS, .campo select:FOCUS {
	background: #f8f8f8;
}

.table {
	width: 640px;
	text-align: center;
}

fieldset {
	width: 640px;
	margin: 0 auto;
}

fieldset.group .campo {
	float: left;
	margin-right: 2em;
}

.master {
	width: 960px;
	margin: 0 auto;
}
</style>

</head>

<body>
	<fieldset>
		<legend>Perfil</legend>
		<table class="table">
			<tr>
				<th>Nome do Usuário</th>
				<th>Email</th>
				<th>Data de Cadasrro</th>
				<th>Perfil</th>
				<th>Ação</th>
			</tr>
			<tr>
				<td>${usuario.nome }</td>
				<td>${usuario.email }</td>
				<td>${usuario.dataCadastro }</td>
				<td>${usuario.perfil}</td>
				<td>
					<a href="#" title="Editar">&#9445</a>
					<a href="#" title="Excluir">&#9447</a>
				</td>
			</tr>
			
		</table>
	</fieldset>


</body>
</html>