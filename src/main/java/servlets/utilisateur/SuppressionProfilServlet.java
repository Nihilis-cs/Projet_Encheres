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
 * Servlet implementation class SuppressionProfilServlet
 */
@WebServlet("/utilisateur/suppression")
public class SuppressionProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuppressionProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateurs utilisateurActif = (Utilisateurs) session.getAttribute("utilisateurActif");
		System.out.println(utilisateurActif.toString());
		//SUPPRESSION UTILISATEUR
		String boutonSupprimer = request.getParameter("boutonSupprimer");
		System.out.println(boutonSupprimer);
		//Récuperation du pseudo et supression du compte dans la base de donnée
		String pseudo = utilisateurActif.getPseudo();
		int id = utilisateurActif.getId();
		System.out.println(pseudo);
		UtilisateursManager um =UtilisateursManager.getInstance();
		try {
			if(!um.utilisateurAEnchere(utilisateurActif.getId())) {
				um.deleteUtilisateurId(id);
				session.setAttribute("utilisateurActif", null);
				RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/login.jsp");
				request.setAttribute("messageSucces", "Suppression réussie. Adieu, " + pseudo + "." );
				rs.forward(request, response);
			} else {
				RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/GestionProfil.jsp");
				request.setAttribute("messageErreur", "Suppression impossible" );
				rs.forward(request, response);
			}
		} catch (BLLException e) {
			RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/GestionProfil.jsp");
			request.setAttribute("messageErreur", "Suppression impossible" );
			rs.forward(request, response);
			e.printStackTrace();
		}
		



	}

}
