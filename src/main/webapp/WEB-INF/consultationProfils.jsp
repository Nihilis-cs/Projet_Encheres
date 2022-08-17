<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="fr" xmlns:mso="urn:schemas-microsoft-com:office:office"
	xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Personnal CSS-->
<link rel="stylesheet" href="css/style.css">

<!--icons-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<title>ENI-Encheres : Rechercher Utilisateur</title>

</head>
<body>
	<div class="container-fluid">
		<!--emptyHeader-->
		<%@ include file="fragments/header_fragment.jsp"%>

		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Recherche utilisateur</h1>
					<img class="mb-4 large-icon rounded-circle" src="images/user.svg"
					alt="">
			</div>
			<!--formulaire-->
			<form class=""
				action="${pageContext.request.contextPath}/utilisateur/rechercheUtilisateur" method="post">
				<label for="inputIdentifiant" class="sr-only"></label><input
					type="text" id="inputRecherchePseudo" class="form-control"
					name="Recherche" placeholder="Pseudo utilisateur?" required autofocus>
				<div class="text-center">
					<button class="btn btn-lg btn-primary " type="submit"
						title="Me connecter">Me connecter</button>
					<div class="text-center">
					  <!-- <input type="button" name="create" value="Crée un compte" /> -->
					 <%--  <input type="button" action="${pageContext.request.contextPath}/connection" value="Crée un compte" /> --%>
						<a href="#">Mot de passe oublié</a>
					</div>
				</div>
			</form>
			<form action="${pageContext.request.contextPath}/navigation/inscription" method="post">
			 <button type="submit" name="create" value="create">Crée un compte</button>
			 </form>
		</main>
		<!--footer-->
		<footer class="border-top text-center align-bottom">
			<div class="mt-3">
				<img class="small-icon" src="images/ateni.svg" alt="Eni Ecole">
				<small class="d-block text-muted">&copy; ENI Ecole 2020</small>
			</div>
		</footer>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>
</html>