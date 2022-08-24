package servlets.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.BLLException;
import bll.UtilisateursManager;
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

		Utilisateurs utilisateurActif = (Utilisateurs) session.getAttribute("utilisateurActif");
		System.out.println(utilisateurActif.toString());
		//SUPPRESSION UTILISATEUR
		String boutonSupprimer = request.getParameter("boutonSupprimer");
		System.out.println(boutonSupprimer);

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
		int id = utilisateurActif.getId();
		UtilisateursManager um =UtilisateursManager.getInstance();
		System.out.println("Données saisi par l'user pour modification dans la BDD = "+pseudo+ nom+ prenom+ email+ phone+ rue+ codePostal+ ville+ mdp+id);
		try {
			Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, phone, rue, codePostal, ville, mdp, id);
			System.out.println(utilisateur);
			um.updateUtilisateur(utilisateur);
			session.setAttribute("utilisateurActif", utilisateur);
			request.setAttribute("messageSucces", "Modifications bien prises en compte!");
		} catch (BLLException e) {
			request.setAttribute("messageErreur", "Modifications invalides.");
			e.printStackTrace();
		}
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/GestionProfil.jsp");
		rs.forward(request, response);



		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateurActif");
		System.out.println(utilisateur.toString());

	}

}
