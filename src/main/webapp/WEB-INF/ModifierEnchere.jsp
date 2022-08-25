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

<title>ENI-Encheres : Modifier article</title>

</head>
<body>
	<%@ include file="fragments/header_fragment.jsp"%>
	<div class="container-fluid">
		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Modifier un article</h1>
				<img class="mb-4 large-icon rounded-circle" src="images/user.svg"
					alt="">
			</div>

			<!--formulaire-->
			<form action="${pageContext.request.contextPath}/article/modifier"
				method="post" class="form-register needs-validation" enctype="multipart/form-data" novalidate>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="nomArticle">Nom de l'article</label> <input
							type="text" class="form-control" id="nomArticle"
							name="nomArticle" placeholder="" maxlength="30" required value="${article.nomArticle}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<div class="form-group">
							<label for="categories-select">Catégories</label> <select
								class="form-control" id="categories-select" name="categorie">
								<option selected>Toutes</option>
								<option value="Informatique">Informatique</option>
								<option value="Ameublement">Ameublement</option>
								<option value="Vetement">Vêtement</option>
								<option value="Sport & Loisir">Sport & Loisirs</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<label for="description">Description</label>
					<textarea rows="4" cols="50" class="form-control" id="description"
						name="description" >${article.description}</textarea>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="miseEnVente">Date mise en vente</label><input
							type="datetime-local" class="form-control" id="dateDebut"
							name="dateDebut" value="${article.dateDebutEnchere}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="finDeVente">Date fin de vente</label> <input
							type="datetime-local" class="form-control" id="dateFin"
							name="dateFin" value="${article.dateFinEnchere}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="prixInit">Prix initial</label> <input type="number"
							class="form-control" id="prixInit" name="prixInit" placeholder=""
							value="${article.prixInitial}" maxlength="15">
					</div>
					<div class="col-md-6 mb-3">
						<fieldset>
							<legend>Adresse de retrait</legend>
							<label for="rue"> Rue</label> <br> <input
								value="${utilisateurActif.rue}" size="50" type="text" id="rue"
								name="rue"><br /> <label for="ville"> Ville</label><br>
							<input value="${utilisateurActif.ville}" type="text" size="20"
								id="ville" name="ville"> <br /> <label for="codePostal">
								Code Postal</label> <br> <input
								value="${utilisateurActif.codePostal}" size="10" type="text"
								id="codePostal" name="codePostal"><br />
						</fieldset>
					</div>
				</div>

				<div class="text-center">
					<div class="row">
						<div class="col-md-12 mb-3">
							<label for="image">photo de l'article</label><input type="file"
								class="form-control" id="uploadPhoto" name="uploadPhoto"
								accept="image/png, image/jpg" onchange="PreviewImage();">
							<img id="uploadPreview" style="width: 100px; height: 100px;">
							<div class="invalid-feedback">Ce champ est invalide !</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="idarticle" value="${article.noArticle}">

				<hr class="mb-4">
				<div class="text-center">
					<button class="btn btn-primary btn-lg" type="submit">Modifier cet article</button>
				</div>
			</form>
			<form
				action="${pageContext.request.contextPath}/article/suppression"
				method="post">
				<input type="hidden" name="idarticle" value="${article.noArticle}">
				<div class="text-center">
					<button type="submit" name="bouttonSupprimer"
						value="boutonSupprimer">Supprimer ${article.nomArticle}</button>
				</div>
			</form>
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