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
import dal.DALException;
import dal.UtilisateursDAOJdbcImp;

/**
 * Servlet implementation class CreeCompteServlet
 */
@WebServlet("/utilisateur/inscription")
public class CreeCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreeCompteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean test = false; //BOOLEAN POUR LA REDIRECTION VALIDER USER 
		//Récuperation des données saisie par l'utilisateur et création d'un nouveau user dans la BDD
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String confirmMdp = request.getParameter("confirm_mdp");
		UtilisateursManager um =UtilisateursManager.getInstance();
		Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, phone, rue, codePostal, ville, mdp, 100, (byte)0);
		if(mdp.equals(confirmMdp)) {
			try {
				test = um.validerUtilisateur(utilisateur); //Validation et Récuperation d'un boolean pour la validation 
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.getMessage());			
			}
			if(test==true) {
				try {
					um.insertUtilisateur(utilisateur);
					request.setAttribute("messageSucces", "Compte créé avec succes! Bienvenue!");
				} catch (BLLException e) {
					request.setAttribute("messageErreur", e.getMessage());
					e.printStackTrace();
				}
				RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
				rs.forward(request, response);
			} else {
				RequestDispatcher rs = request.getRequestDispatcher("/navigation/inscription");
				rs.forward(request, response);
			}
		} else {
			request.setAttribute("messageErreur", "Les mots de passes ne correspondent pas ! ");
			RequestDispatcher rs = request.getRequestDispatcher("/navigation/inscription");
			rs.forward(request, response);
		}
	}
}
