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


@WebServlet("/utilisateur/rechercheUtilisateur")
public class RechecherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RechecherProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/consultationProfils.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudoSaisi = request.getParameter("Recherche");
		UtilisateursManager um = UtilisateursManager.getInstance();
		Utilisateurs userRech;
		
		try {
			userRech = um.selectByPseudo(pseudoSaisi);
			request.setAttribute("utilisateurRecherche", userRech);
		} catch (BLLException e) {
			// gerer l'exception
			e.printStackTrace();
		}
		
		doGet(request, response);
		
	}

}
