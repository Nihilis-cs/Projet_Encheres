package servlets.utilisateur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateurs;

/**
 * Servlet implementation class ArticlesDetailsServlet
 */
@WebServlet("/articles/details")
public class ArticlesDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArticlesDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Idée Fonctionalité : celui qui a mit l'article en vente ne peux pas encherir dessus
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}