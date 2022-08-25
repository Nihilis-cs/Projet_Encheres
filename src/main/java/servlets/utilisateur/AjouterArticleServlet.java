package servlets.utilisateur;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.imageio.ImageIO;
import servlets.utilisateur.IHMException;
import bll.ArticlesManager;
import bll.BLLException;
import bll.UtilisateursManager;
import bo.Articles;
import bo.Categories;
import bo.Encheres;
import bo.EtatsVente;
import bo.Images;
import bo.Retraits;
import bo.Utilisateurs;
import dal.ImagesDAOJdbcImp;


@MultipartConfig
@WebServlet("/article/ajout")
public class AjouterArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "uploads";

	public AjouterArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		String rue 			= request.getParameter("rue");
		String codePostal 	= request.getParameter("codePostal");
		String ville 		= request.getParameter("ville");
		//		String image 		= request.getParameter("lienImage");

		System.out.println(nomArticle + categorie + description + dateDebut + dateFin + prixInit + rue + codePostal + ville );

		//Conversion dates
		String Debut[] = dateDebut.split("T");		
		LocalDateTime debutDate = LocalDateTime.of(LocalDate.parse(Debut[0]), LocalTime.parse(Debut[1]));

		String Fin[] = dateFin.split("T");
		LocalDateTime finDate = LocalDateTime.of(LocalDate.parse(Fin[0]), LocalTime.parse(Fin[1]));
		boolean isBefore = finDate.isBefore(debutDate);
		System.out.println(isBefore);


		if(isBefore) {
			request.setAttribute("messageErreur", "Date non valide");			
			RequestDispatcher rs = request.getRequestDispatcher("/navigation/vente");
			rs.forward(request, response);
			
		}else {

			int prixInitParse 		 	 = Integer.parseInt(prixInit);
			Utilisateurs user 		 	 = (Utilisateurs) session.getAttribute("utilisateurActif");
			Encheres ench 			 	 = null;
			int prixVente 			 	 = 0;
			Categories cat 				 = null;

			try {
				switch(categorie) {
				case "Informatique": 
					cat= new Categories(1, categorie);
					break;
				case "Ameublement":
					cat= new Categories(2, categorie);
					break;
				case "Vetement":
					cat= new Categories(3, categorie);
					break;
				case "Sport & Loisir":
					cat= new Categories(4, categorie);
					break;
				default: 
					request.setAttribute("messageErreur", "Catégorie non valide");
					throw new IHMException("Veuillez choisir une categorie");
				}

			} catch (Exception e) {
				RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/Vente.jsp");
				rs.forward(request, response);
			}


			Articles article = new Articles(nomArticle, description, debutDate, finDate, prixInitParse, prixVente,  user, cat, EtatsVente.CR, ench);   
			System.out.println(article.toString());
			Retraits retrait = new Retraits(rue, codePostal, ville);
			System.out.println(retrait.toString());

			try {
				artmngr.insert(article, retrait);
				request.setAttribute("messageSucces", "Article enregistré!");
			} catch (BLLException e) {
				request.setAttribute("messageErreur", "Article non valide!");
				e.printStackTrace();
				RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/Vente.jsp");
				rs.forward(request, response);
			}
			
			RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
			rs.forward(request, response);
		}

	}

}