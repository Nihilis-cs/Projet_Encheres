<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<header>
	<nav
		class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="index.html"> <strong>ENI-Encheres</strong>
		</a> <a class="navbar-brand" href="#" alt="G�rer mon profil"
			title="G�rer mon profil"> <span class="align-middle text-muted">
				<!-- Connect� ou pas --> <c:if test="${not empty utilisateurActif}">
					<p>${utilisateurActif.nom}${utilisateurActif.prenom}
						${utilisateurActif.credit} cr�dits</p>
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
								alt="Me d�connecter">Me d�connecter</a> <a class="dropdown-item"
								href="register.html" alt="S'inscrire � ENI-Encheres">M'inscrire</a>
							<a class="dropdown-item" href="login.html"
								alt="Se connecter � ENI-Encheres">Me connecter</a>
						</div></li> -->
			<!-- Links for medium screen-->

			<c:if test="${not empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/to/gestion/profil"
					alt="Mon profil">Mon profil</a></li>
			</c:if>

			<c:if test="${utilisateurActif.admin == '1'}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="#" alt="Administrer le site">Administrer</a></li>
			</c:if>

			<li class="nav-item d-none d-lg-block"><a class="nav-link"
				href="#" alt="Vendre un article">Vendre un article</a></li>

			<li class="nav-item d-none d-lg-block"><a class="nav-link"
				href="${pageContext.request.contextPath}/Creer/Compte"
				alt="S'inscrire � ENI-Encheres">M'inscrire</a></li>
			<c:if test="${empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/to/login"
					alt="Se connecter � ENI-Encheres">Me connecter</a></li>
			</c:if>
			<c:if test="${not empty utilisateurActif}">
				<li class="nav-item d-none d-lg-block"><a class="nav-link"
					href="${pageContext.request.contextPath}/connection"
					alt="Me d�connecter">Me d�connecter</a></li>
			</c:if>


		</ul>
	</nav>
</header>
