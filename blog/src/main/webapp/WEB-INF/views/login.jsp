<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN PAGE </title>

<link type="text/css" rel="stylesheet"
	href="<c:url value= "/css/style.css" />">

</head>
<body>
	<c:import url="menu.jsp" />
	
	<fieldset>
		<c:url value="/auth/login" var="loginUrl"/>
		<form action="${loginUrl}" method="post">       
			<c:if test="${error != null}">       
				<p class="error">
					${error}
				</p>
			</c:if>
			<c:if test="${logout != null}">       
				<p class="error">
					${logout}
				</p>
			</c:if>
			<p>
				<label for="username">Username</label>
				<input type="text" id="username" name="j_username"/>	
			</p>
			<p>
				<label for="password">Password</label>
				<input type="password" id="password" name="j_password"/>	
			</p>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button type="submit" class="btn">Log in</button>
		</form>
	</fieldset>
	

</body>
</html>