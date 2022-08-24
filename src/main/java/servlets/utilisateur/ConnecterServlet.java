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

		System.out.println("CheckBoxe se souvenir : "+ sauvegarderLogin);

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

				if (sauvegarderLogin != null) {

					Cookie cookiePseudo = new Cookie("pseudo", pseudo );
					Cookie cookieMdp = new Cookie("mdp", mdp );
					cookiePseudo.setMaxAge(Integer.MAX_VALUE);
					cookieMdp.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(cookiePseudo);
					response.addCookie(cookieMdp);
//					request.setAttribute("pseudo", cookiePseudo);
//					request.setAttribute("mdp", cookieMdp);

					Cookie[] cookies = request.getCookies();
					System.out.println(cookies);
					if(cookies != null ) {
						for(Cookie cookie : cookies) {
							if(cookie.getName().equals("pseudo")) {
								request.setAttribute("pseudo", cookie.getValue());
							}
							if(cookie.getName().equals("mdp")) {
								request.setAttribute("mdp", cookie.getValue());
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
