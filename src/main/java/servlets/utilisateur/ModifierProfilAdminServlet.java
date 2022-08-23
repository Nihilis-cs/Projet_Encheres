package servlets.utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.UtilisateursManager;
import bo.Utilisateurs;

/**
 * Servlet implementation class ModifierProfilAdminServlet
 */
@WebServlet("/utilisateur/modierProfilAdmin")
public class ModifierProfilAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifierProfilAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MODIFICATION UTILISASTEUR
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		int noUser = Integer.parseInt(request.getParameter("user"));
		
		try {
			UtilisateursManager um =UtilisateursManager.getInstance();
			Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, phone, rue, codePostal, ville, mdp, noUser);

			um.updateUtilisateur(utilisateur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
