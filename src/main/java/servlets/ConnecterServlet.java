package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		UtilisateursDAOJdbcImp dao = new UtilisateursDAOJdbcImp();
		try {
			Utilisateurs utilisateur = dao.getUtilisateurByMailMDP(pseudo, mdp);
			if (utilisateur == null) {
				request.setAttribute("estConnecte", "0");
				
				//response.getWriter().append("Erreur ");
			}else {
				request.setAttribute("estConnecte", "1");
				//response.getWriter().append("Connecté ");
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/Connection.jsp");
		rs.forward(request, response);
	}

}
