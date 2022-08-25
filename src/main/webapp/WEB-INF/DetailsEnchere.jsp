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

<title>ENI-Encheres : Details article</title>

</head>
<body>
<%@ include file="fragments/header_fragment.jsp"%>
	<div class="container-fluid">		
		<!--main bloc-->
		<main>
			<div class="row justify-content-center border-top card-deck">
				<div class="col-12 p-2">
					<div class="card">
						<div class="card-header text-center">
							<h4 class="my-0 font-weight-normal">${article.nomArticle }</h4>
						</div>
						<div class="d-flex">
							<div class="col-3 p-2"></div>
							<ul class="col-9 list-unstyled p-2">
								<li>Description : ${article.description }</li>
								<c:if test="${empty article.enchere.montantEnchere }">
								<li>Prix : ${article.prixInitial}</li>
								</c:if>
								
								<c:if test="${not empty article.enchere.montantEnchere }">
								<li>Enchere en cours : ${article.enchere.montantEnchere }</li>
								</c:if>
								
								<li>Fin de l'enchere le : ${article.dateFinEnchereToString }</li>
								<li>Ajouté par : <a href="${pageContext.request.contextPath}/utilisateur/rechercheUtilisateur?pseudoVendeur=${article.vendeur.pseudo}">${article.vendeur.pseudo}</a></li>
								<li>Mis en vente le: ${article.dateDebutEnchereToString }</li>
							</ul>
						</div>
						<form action="${pageContext.request.contextPath}/enchere/proposer"
							method="post">
							<input type="number" class="form-control" id="enchere"
								name="enchere" placeholder="Votre enchère"
								min="${(article.enchere.montantEnchere) + 1 }" max="99999999"
								value="" required>
							<div class="text-center">
								<button class="btn btn-primary btn-lg" name="noArticle"
									value="${article.noArticle}" type="submit">Enchérir</button>
							</div>
						</form>
					</div>
				</div>
			</div>

<%--
 			<!--title-->
			<div class="mx-auto ">
				<div class="text-center">
					<h1>${article.nomArticle }</h1>
				</div>

				<p>Description : ${article.description }</p>
				<p>Ajouté par : ${article.vendeur.pseudo }</p>
				<p>Mis en vente le: ${article.dateDebutEnchere }</p>
				<p>Fin de l'enchere le : ${article.dateFinEnchere }</p>
				<p>Prix initial : ${article.prixInitial }</p>
				<p>Enchere en cours : ${article.enchere.montantEnchere }</p>

				<p>${requestScope.erreurRecherche }</p>
			</div>
			<!--formulaire-->
			<form action="${pageContext.request.contextPath}/enchere/proposer"
				method="post">
				<input type="number" class="form-control" id="enchere"
					name="enchere" placeholder="Votre enchère"
					min="${(article.enchere.montantEnchere) + 1 }" max="99999999"
					value="" required>
				<button class="btn btn-primary btn-lg" name="noArticle"
					value="${article.noArticle}" type="submit">
					Enchérir <img class="small-icon" src="images/bid.svg">
				</button>
			</form>
			 --%>

			<!--footer-->
			<footer class="border-top text-center align-bottom">
				<div class="mt-3">
					<small class="d-block text-muted">&copy; ENI Ecole 2022</small>
				</div>
			</footer>
		</main>
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