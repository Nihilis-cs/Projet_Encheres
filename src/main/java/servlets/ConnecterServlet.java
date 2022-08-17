package servlets;

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
import dal.DALException;
import dal.UtilisateursDAOJdbcImp;

/**
 * Servlet implementation class ConnecterServlet
 */
@WebServlet("/connection")
public class ConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ConnecterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		UtilisateursManager um = UtilisateursManager.getInstance();
		try {
			Utilisateurs utilisateur = um.getUtilisateurByMailMdp(pseudo, mdp);
			if (utilisateur == null) {
				request.setAttribute("estConnecte", "0");

			}else {
				request.setAttribute("estConnecte", "1");
				HttpSession session = request.getSession();
				session.setAttribute("utilisateurActif", utilisateur);
			}
		} catch (BLLException e) {
			//Gerer BLLException
			e.printStackTrace();
		}

		RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/Connection.jsp");
		rs.forward(request, response);
	}

}
