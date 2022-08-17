<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<header>
	<nav
		class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="index.html"> <strong>ENI-Encheres</strong>
		</a> <a class="navbar-brand" href="#" alt="Gérer mon profil"
			title="Gérer mon profil"> <span class="align-middle text-muted">
				<!-- Connecté ou pas --> <c:if test="${not empty utilisateurActif}">
					<p>${utilisateurActif.nom}${utilisateurActif.prenom}
						${utilisateurActif.credit} crédits</p>
				</c:if> <c:if test="${empty utilisateurActif}">
					<p>Visiteur</p>
				</c:if>

		</span>
		</a>

		<ul class="navbar-nav ml-auto">
			<!-- Dropdown for small screen -->
			<!-- <li class="nav-item dropdown d-lg-none"><a
						class="nav-link dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown"> <img class="small-icon"
							src="images/menu.svg" alt="Menu ENI-Encheres">
					</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#" alt="Administrer le site">Administrer</a>
							<a class="dropdown-item" href="#" alt="Vendre un article">Vendre
								un article</a> <a class="dropdown-item" href="#"
								alt="Me déconnecter">Me déconnecter</a> <a class="dropdown-item"
								href="register.html" alt="S'inscrire à ENI-Encheres">M'inscrire</a>
							<a class="dropdown-item" href="login.html"
								alt="Se connecter à ENI-Encheres">Me connecter</a>
						</div></li> -->
			<!-- Links for medium screen-->

			<c:if test="${not empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/navigation/gestionProfil"
					alt="Mon profil">Mon profil</a></li>
			</c:if>

			<c:if test="${utilisateurActif.admin == '1'}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="#" alt="Administrer le site">Administrer</a></li>
			</c:if>

			<li class="nav-item d-none d-lg-block"><a class="nav-link"
				href="#" alt="Vendre un article">Vendre un article</a></li>

			<li class="nav-item d-none d-lg-block"><a class="nav-link"
				href="${pageContext.request.contextPath}/navigation/inscription"
				alt="S'inscrire à ENI-Encheres">M'inscrire</a></li>
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
</header>
