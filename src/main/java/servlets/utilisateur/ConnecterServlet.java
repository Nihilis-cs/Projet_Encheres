package servlets.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import bll.BLLException;
import bll.UtilisateursManager;
import bo.Utilisateurs;
import dal.DALException;
import dal.UtilisateursDAOJdbcImp;

/**
 * Servlet implementation class ConnecterServlet
 */
@WebServlet("/utilisateur/login")
public class ConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConnecterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("utilisateurActif", null);
		request.setAttribute("messageSucces", "Vous avez été déconnecté. Bonne journée!");

		RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
		rs.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		String sauvegarderLogin = request.getParameter("remember");

		UtilisateursManager um = UtilisateursManager.getInstance();

		try {
			Utilisateurs utilisateur = um.getUtilisateurByMailMdp(pseudo, mdp);
			if (utilisateur == null) {
				request.setAttribute("messageErreur", "Pseudo ou mot de passe invalide");
				RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/login.jsp");
				rs.forward(request, response);

			}else {
				request.setAttribute("messageSucces", "Bienvenue " + utilisateur.getPseudo() + "!" );
				HttpSession session = request.getSession();
				session.setAttribute("utilisateurActif", utilisateur);
				session.setMaxInactiveInterval(300);

				if (sauvegarderLogin != null) { // SI L'USER SOUHAITE SE SOUVENIR DES LOGINS CREATIONS DES COOKIES CONTENANT LES LOGS

					Cookie cookiePseudo = new Cookie("pseudo", pseudo );
					Cookie cookieMdp = new Cookie("mdp", mdp );
					Cookie cookieSaveLogin = new Cookie("remember", sauvegarderLogin );

					cookiePseudo.setMaxAge(Integer.MAX_VALUE);
					cookieMdp.setMaxAge(Integer.MAX_VALUE);
					cookieSaveLogin.setMaxAge(Integer.MAX_VALUE);

					cookiePseudo.setPath("/Projet_Encheres");
					cookieMdp.setPath("/Projet_Encheres");
					cookieSaveLogin.setPath("/Projet_Encheres");

					response.addCookie(cookiePseudo);
					response.addCookie(cookieMdp);
					response.addCookie(cookieSaveLogin);
				} else { // SI LA CHECKBOX N'EST PAS CHECK SUPPRRESION DES COOKIES 
					Cookie[] cookies = request.getCookies();
					if(cookies != null ) {
						for(Cookie cookie : cookies) {
							if(cookie.getName().equals("pseudo")) {
								cookie.setMaxAge(0);
								cookie.setPath("/Projet_Encheres");
								response.addCookie(cookie);
							}
							if(cookie.getName().equals("mdp")) {
								cookie.setMaxAge(0);
								cookie.setPath("/Projet_Encheres");
								response.addCookie(cookie);
							}
							if(cookie.getName().equals("remember")) {
								cookie.setMaxAge(0);
								cookie.setPath("/Projet_Encheres");
								response.addCookie(cookie);
							}
						}
					}
				}



				RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
				rs.forward(request, response);



			}
		} catch (BLLException e) {
			//Gerer BLLException
			e.printStackTrace();
		}



	}

}
