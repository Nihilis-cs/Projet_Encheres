package servlets.utilisateur;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UtilisateursManager;
import bo.Articles;

/**
 * Servlet implementation class AjouterArticleServlet
 */
@WebServlet("/article/ajout")
public class AjouterArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjouterArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recuperation des données de l'article
		String nomArticle = request.getParameter("nomArticle");
		String categorie = request.getParameter("categorie");
		String description = request.getParameter("description");
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		String prixInit = request.getParameter("prixInit");
		System.out.println(nomArticle + categorie + description + dateDebut + dateFin + prixInit);
		
		UtilisateursManager um =UtilisateursManager.getInstance();
		
//		Articles article = new Articles(nomArticle, categorie, description, dateDebut, dateFin, prixInit);
	}

}
