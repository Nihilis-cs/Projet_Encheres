package servlets.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateurs;

/**
 * Servlet implementation class GestionProfil
 */
@WebServlet("/utilisateur/gestion")
public class GestionProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GestionProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateurActif");
		System.out.println(utilisateur.toString());
	}

}
