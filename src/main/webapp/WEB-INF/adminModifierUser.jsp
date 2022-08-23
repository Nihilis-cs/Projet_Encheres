<!DOCTYPE html>
<html lang="fr" xmlns:mso="urn:schemas-microsoft-com:office:office"
	xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
<head>
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

<title>ENI-Encheres : Gestion Profil</title>

</head>
<body>
	<%@ include file="fragments/header_fragment.jsp"%>
	<div class="container-fluid">
		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Gestion Profil</h1>
				<img class="mb-4 large-icon rounded-circle" src="images/user.svg"
					alt="">
			</div>

			<!--formulaire-->
			<form
				action="${pageContext.request.contextPath}/utilisateur/modierProfilAdmin"
				method="post" class="form-register needs-validation" novalidate>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="pseudo">Pseudo</label> <input type="text"
							class="form-control" id="pseudo" name="pseudo" placeholder=""
							maxlength="30" required value="${user.pseudo}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="lastname">Nom</label> <input type="text"
							class="form-control" id="nom" name="nom" placeholder=""
							value="${user.nom}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstname">Prénom</label> <input type="text"
							class="form-control" id="prenom" name="prenom" placeholder=""
							value="${user.prenom}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="you@example.com" value="${user.email}"
							maxlength="40" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="phone">Téléphone <span class="text-muted">(Optional)</span></label>
						<input type="text" class="form-control" id="phone" name="phone"
							placeholder="" value="${user.telephone}" maxlength="15">
					</div>
					<div class="col-md-8 mb-3">
						<label for="street">Rue</label> <input type="text"
							class="form-control" id="rue" name="rue" placeholder=""
							value="${utilisateurActif.rue}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="zipcode">Code postal</label> <input type="number"
							class="form-control" id="codepostal" name="codepostal"
							placeholder="" min="01000" max="99999" value="${user.codePostal}"
							required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
					<div class="col-md-8 mb-3">
						<label for="city">Ville</label> <input type="text"
							class="form-control" id="ville" name="ville" placeholder=""
							maxlength="30" value="${user.ville}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<hr class="mb-4">
				<div class="text-center">
					<button class="btn btn-primary btn-lg" type="submit">Modifier
						le compte</button>
				</div>
			</form>
			<form
				action="${pageContext.request.contextPath}/utilisateur/suppression"
				method="post">
				<div class="text-center">
					<button type="submit" name="bouttonSupprimer"
						value="boutonSupprimer">Supprimer le compte</button>
				</div>
			</form>
		</main>
		<!--footer-->
		<footer class="border-top text-center align-bottom">
			<div class="mt-3">
				<small class="d-block text-muted">&copy; ENI Ecole 2022</small>
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
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';

			window
					.addEventListener(
							'load',
							function() {
								// Fetch all the forms we want to apply custom Bootstrap validation styles to
								var forms = document
										.getElementsByClassName('needs-validation');

								// Loop over them and prevent submission
								var validation = Array.prototype.filter
										.call(
												forms,
												function(form) {
													form
															.addEventListener(
																	'submit',
																	function(
																			event) {
																		//validation du mot de passe
																		var password = document
																				.getElementById("password"), confirm_password = document
																				.getElementById("confirm_password");
																		if (password.value != confirm_password.value) {
																			confirm_password
																					.setCustomValidity("Les mots de passe sont différents");
																			event
																					.preventDefault();
																			event
																					.stopPropagation();
																		} else {
																			confirm_password
																					.setCustomValidity('');
																		}
																		//validations des saisies obligatoires
																		if (form
																				.checkValidity() === false) {
																			event
																					.preventDefault();
																			event
																					.stopPropagation();
																		}
																		form.classList
																				.add('was-validated');
																	}, false);
												});
							}, false);
		})();
	</script>
</body>

</html>