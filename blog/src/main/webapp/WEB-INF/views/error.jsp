<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Error Page</h1>
	<p>${mensagem}</p>
	<div>
	<!--javascript:history.back(); volta para a pagina anterior  -->
		<button onclick="javascript:history.back();"> Voltar</button>
	</div>

	<!-- 
		Falha ao acessar a URL: ${url} 
		Exceção : ${excecao}
	
	 -->

</body>
</html>