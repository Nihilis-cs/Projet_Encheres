package servlets.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		//MODIFICATION UTILISATEUR
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String inputUser= request.getParameter("ident");
		int noUser = Integer.parseInt(inputUser);
		UtilisateursManager um =UtilisateursManager.getInstance();
		try {
			Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, phone, rue, codePostal, ville, mdp, noUser);
			um.updateUtilisateur(utilisateur);
			System.out.println("/////////////////////");
			System.out.println(utilisateur);
			request.setAttribute("messageSucces", "Modifications bien prises en compte!");
		} catch (BLLException e) {
			request.setAttribute("messageErreur", "Modifications invalides.");
			e.printStackTrace();
		}
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/adminUser.jsp");
		rs.forward(request, response);
		
	}

}
