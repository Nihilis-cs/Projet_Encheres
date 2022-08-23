package servlets.navigation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticlesManager;
import bll.BLLException;
import bll.UtilisateursManager;
import bo.Utilisateurs;


@WebServlet("/navigation/accueil")
public class AccueillirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccueillirServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateurActif") != null) {
			UtilisateursManager um = UtilisateursManager.getInstance();
			Utilisateurs utilisateurActif = (Utilisateurs) session.getAttribute("utilisateurActif");
			try {
				session.setAttribute("utilisateurActif", um.selectByID(utilisateurActif.getId()) );
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			request.setAttribute("liste", ArticlesManager.getInstance().selectAll());

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}