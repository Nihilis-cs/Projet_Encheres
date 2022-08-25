package servlets.article;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticlesManager;
import bll.BLLException;

/**
 * Servlet implementation class SupprimerArticleServlet
 */
@WebServlet("/article/suppression")
public class SupprimerArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerArticleServlet() {
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
		ArticlesManager am = ArticlesManager.getInstance();
		String strIdArticle = request.getParameter("idarticle");
		int idArticle = Integer.parseInt(strIdArticle);
		try {
			am.delete(idArticle);
			request.setAttribute("messageSucces", "Article supprimé!");
		} catch (BLLException e) {
			request.setAttribute("messageErreur", e.getMessage());
			e.printStackTrace();
		}
		RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
		rs.forward(request, response);
	}

}
