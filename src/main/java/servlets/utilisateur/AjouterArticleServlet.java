package servlets.utilisateur;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
import bo.Articles;
import bo.Categories;
import bo.Encheres;
import bo.EtatsVente;
import bo.Utilisateurs;

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
		HttpSession session  		= request.getSession();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm");
		ArticlesManager artmngr 	= ArticlesManager.getInstance();
		
		//Recuperation des données de l'article
		String nomArticle	= request.getParameter("nomArticle");
		String description 	= request.getParameter("description");
		String categorie 	= request.getParameter("categorie");
		String dateDebut 	= request.getParameter("dateDebut");
		String dateFin 		= request.getParameter("dateFin");
		String prixInit 	= request.getParameter("prixInit");
		System.out.println(nomArticle + categorie + description + dateDebut + dateFin + prixInit);
		
		//Conversion des infos de l'article
		
		
		String Debut[] = dateDebut.split("T");		
		LocalDateTime debutDate = LocalDateTime.of(LocalDate.parse(Debut[0]), LocalTime.parse(Debut[1]));
		
		String Fin[] = dateFin.split("T");
		LocalDateTime finDate = LocalDateTime.of(LocalDate.parse(Fin[0]), LocalTime.parse(Fin[1]));
					
//		LocalDateTime dateDebutParse = LocalDateTime.(debutDate, formatter);
//		LocalDateTime dateFinParse	 = LocalDateTime.parse(dateFin, formatter);
		int prixInitParse 		 	 = Integer.parseInt(prixInit);
		Utilisateurs user 		 	 = (Utilisateurs) session.getAttribute("utilisateurActif");
		Encheres ench 			 	 = null;
		int prixVente 				 =0;
		Categories cat 				 = null;
		
		switch(categorie) {
		case "Informatique": 
			cat= new Categories(1, categorie);
			break;
		case "Ameublement":
			cat= new Categories(2, categorie);
			break;
		case "Vêtements":
			cat= new Categories(3, categorie);
			break;
		case "Sports & loisirs":
			cat= new Categories(4, categorie);
			break;
		default:
			cat= new Categories(1, "Informatique");
		}
		
		
		Articles article = new Articles(nomArticle, description, debutDate, finDate, prixInitParse, prixVente,  user, cat, EtatsVente.CR, ench);   
		System.out.println(article.toString());
		
		try {
			artmngr.insert(article);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rs.forward(request, response);
		
	}

}