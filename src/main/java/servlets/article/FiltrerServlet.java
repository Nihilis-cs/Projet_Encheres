package servlets.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bll.ArticlesManager;
import bll.UtilisateursManager;
import bo.Articles;
import bo.Utilisateurs;

/**
 * Servlet implementation class FiltrerServlet
 */
@WebServlet("/article/filtrer")
public class FiltrerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrerServlet() {
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
		Utilisateurs u = (Utilisateurs) session.getAttribute("utilisateurActif");
		int idUser = u.getId();
		String achatVente = request.getParameter("type-encheres");
		String articleContenant = request.getParameter("article-contenant");
		System.out.println(articleContenant);
		//String categorie = request.getParameter("categorie");
		String requete = null;
		//System.out.println(achatVente);
		ArticlesManager am =ArticlesManager.getInstance();
		
				
		if(achatVente.equals("achats")) {
			String enchere1 = request.getParameter("encheres1");
			String enchere2 = request.getParameter("encheres2");
			String enchere3 = request.getParameter("encheres3");
			requete = achatVente + enchere1 + enchere2 + enchere3;
			//System.out.println(requete);
		} else if (achatVente.equals("ventes")) {
			String vente1 = request.getParameter("ventes1");
			String vente2 = request.getParameter("ventes2");
			String vente3 = request.getParameter("ventes3");
			requete = achatVente + vente1 + vente2 + vente3;
			//System.out.println(requete);
		}

			String idCategorieStr=request.getParameter("categorie");
			System.out.println(idCategorieStr);
			int idCategorie = Integer.parseInt(idCategorieStr);
			
		System.out.println(requete+idCategorie+articleContenant);
		//System.out.println(requete+idUser);
		try {
			List<Articles> liste = am.selectAllFilter(requete, idUser, idCategorie, articleContenant);
			request.setAttribute("liste", liste);
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rs.forward(request, response);
	}

}
