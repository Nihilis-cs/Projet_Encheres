package servlets;

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
@WebServlet("/creer")
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
		//Envoyer l'user à la page de création de compte lorsqu'il appuie sur le bouton
		
		String bouttonCreer = request.getParameter("create");
		System.out.println(bouttonCreer);
		if (bouttonCreer != null) {
			RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/CreerCompte.jsp");
			rs.forward(request, response);
		}
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
		System.out.println(pseudo+ nom+ prenom+ email+ phone+ rue+ codePostal+ ville+ mdp);
		//String confirmMdp = request.getParameter("confirm_mdp");
		UtilisateursManager um =UtilisateursManager.getInstance();
		
		try {
			
			Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, phone, rue, codePostal, ville, mdp, 0, (byte)0);
			System.out.println(utilisateur.toString());
			um.validerUtilisateur(utilisateur);
			um.insertUtilisateur(utilisateur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/login.jsp");
		rs.forward(request, response);
	
		
	}

}
