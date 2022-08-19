package servlets.utilisateur;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.UtilisateursManager;
import bo.Articles;
import bo.EtatsVente;

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
		HttpSession session  =request.getSession();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		//Recuperation des données de l'article
		String nomArticle = request.getParameter("nomArticle");
		String categorie = request.getParameter("categorie");
		String description = request.getParameter("description");
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		String prixInit = request.getParameter("prixInit");
		System.out.println(nomArticle + categorie + description + dateDebut + dateFin + prixInit);
		
		//convert String to LocalDate
		LocalDate dateDebutParse = LocalDate.parse(dateDebut, formatter);
		LocalDate dateFinParse = LocalDate.parse(dateFin, formatter);

			
		UtilisateursManager um =UtilisateursManager.getInstance();
		
//		Articles article = new Articles(nomArticle, description, dateDebutParse, dateFinParse, prixInit, null,  session.getAttribute("utilisateurActif"),null, EtatsVente.CR, null)   
	}

}