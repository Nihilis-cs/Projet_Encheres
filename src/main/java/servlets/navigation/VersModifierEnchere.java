package servlets.navigation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bll.ArticlesManager;
import bll.BLLException;

import bo.Articles;

/**
 * Servlet implementation class VersDetailsEnchere
 */
@WebServlet("/navigation/modifierEnchere")
public class VersModifierEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VersModifierEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noArticle= Integer.parseInt(request.getParameter("article")); 		
		Articles art;
		
		try {
			art = ArticlesManager.getInstance().selectById(noArticle);
			request.setAttribute("article", art);
			System.out.println(art);
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/ModifierEnchere.jsp");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
