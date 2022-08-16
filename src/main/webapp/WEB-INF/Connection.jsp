<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tentative Connection</title>
</head>

<body>
<p>${requestScope.estConnecte}</p>
<c:if test=" ${requestScope.estConnecte == 'true'}"> <p>Etat connection : vous êtes connecté ! </p> </c:if>

	<c:if test=" ${estConnecte == 'false'}"> <p>Etat connection : vous n'êtes pas connecté ! </p></c:if>
</body>
</html>