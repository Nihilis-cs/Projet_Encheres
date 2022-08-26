<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head> --%>
<header>
	<nav
		class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
		<!-- Brand/logo -->
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/navigation/accueil"> <img alt="eni-logo"
			 src="https://cdn.freebiesupply.com/logos/large/2x/eni-1-logo-svg-vector.svg"
			  style="height: 80px; width: 80px; border-radius:50px;" >
		</a>
		<c:if test="${empty utilisateurActif}">
				 <a class="navbar-brand"
			href="${pageContext.request.contextPath}/navigation/login"
			title="Gérer mon profil"> <span
			class="align-middle align-top text-muted"></span></a>
			
		</c:if>
		 <a class="navbar-brand" 
			href="#"
			title="Gérer mon profil"> <span
			class="align-middle align-top text-muted"> <!-- Connecté ou pas -->
				<c:if test="${not empty utilisateurActif}">
					<p class="user-info">${utilisateurActif.nom} ${utilisateurActif.prenom}
						${utilisateurActif.credit} crédits</p>
				</c:if> <c:if test="${empty utilisateurActif}">
					<p class="user-info">Visiteur</p>
				</c:if>
		</span>
		</a>

		<ul class="navbar-nav ml-auto">
			<li class="nav-item dropdown d-lg-none"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown">
			</a>
				<div class="dropdown-menu">
					<c:if test="${not empty utilisateurActif}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/navigation/gestionProfil"
							alt="Mon profil">Mon profil</a>
					</c:if>

					<c:if test="${utilisateurActif.admin == '1'}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/navigation/accueilAdmin"
							alt="Administrer le site">Administrer</a>
					</c:if>

					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/utilisateur/rechercheUtilisateur"
						alt="Chercher un utilisateur">Recherche d'un utilisateur</a> <a
						class="dropdown-item"
						href="${pageContext.request.contextPath}/navigation/vente">Vendre
						un article</a>

					<c:if test="${empty utilisateurActif}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/navigation/inscription">M'inscrire</a>
					</c:if>

					<c:if test="${empty utilisateurActif}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/navigation/login">Me
							connecter</a>
					</c:if>

					<c:if test="${not empty utilisateurActif}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/utilisateur/login">Me
							déconnecter</a>
					</c:if>
				</div></li>

			<c:if test="${not empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/navigation/gestionProfil"
					alt="Mon profil">Mon profil</a></li>
			</c:if>

			<c:if test="${utilisateurActif.admin == '1'}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/navigation/accueilAdmin"
					alt="Administrer le site">Administrer</a></li>
			</c:if>

			<li class="nav-item d-none d-lg-block"><a class="nav-link"
				href="${pageContext.request.contextPath}/utilisateur/rechercheUtilisateur"
				alt="Chercher un utilisateur">Recherche d'un utilisateur</a></li>

			<li class="nav-item d-none d-lg-block"><a class="nav-link"
				href="${pageContext.request.contextPath}/navigation/vente"
				alt="Vendre un article">Vendre un article</a></li>
			<c:if test="${empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/navigation/inscription"
					alt="S'inscrire à ENI-Encheres">M'inscrire</a></li>
			</c:if>

			<c:if test="${empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/navigation/login"
					alt="Se connecter à ENI-Encheres">Me connecter</a></li>
			</c:if>

			<c:if test="${not empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/utilisateur/login"
					alt="Me déconnecter">Me déconnecter</a></li>
			</c:if>

		</ul>
	</nav>
	<c:if test="${not empty messageErreur}">
		<div class="d-flex alert-danger">
			<div class="col-3 p-2">!!!</div>
			<ul class="col-9 list-unstyled p-2">
				<li>${messageErreur}</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty messageSucces}">
		<div class="d-flex alert-success">
			<div class="col-3 p-2">!!!</div>
			<ul class="col-9 list-unstyled p-2">
				<li>${messageSucces}</li>
			</ul>
		</div>
	</c:if>
</header>
