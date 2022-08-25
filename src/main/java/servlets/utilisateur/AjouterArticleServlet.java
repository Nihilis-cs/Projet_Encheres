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
		// CREDIT A 0 QUAND ON UPDATE LE PROFIL
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

		////////////////////////// DEBUT IMAGE
//		try {
//			String name = request.getParameter("name");
//			System.out.println("name: " + name);
//			Images image= null;
//
//			// Gets absolute path to root directory of web app.
//			String appPath = request.getServletContext().getRealPath("");
//
//			// Gets image informations
//			Part part = request.getPart("pictureFile");
//
//			//Save image File and get fileName
//			String fileName = saveFile(appPath, part);
//
//			// save Truc in database
//			image = new Images(name, fileName);
//			ImagesDAOJdbcImp dao = new ImagesDAOJdbcImp();
//			dao.insert(image);
//
//			//redirection
//			request.setAttribute("truc", image);
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/uploadresult.jsp");
//			rd.forward(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("errorMessage", "Error: " + e.getMessage());
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Vente.jsp");
//			dispatcher.forward(request, response);
//		}
		///////////////////////////////////////////FIN IMAGE

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

	
	/**
	* Sauvegarder le fichier image
	* @param appPath
	* @param part
	* @return
	* @throws IOException
	*/
//	private String saveFile(String appPath, Part part) throws IOException {
//		appPath = appPath.replace('\\', '/');
//		// The directory to save uploaded file
//		String fullSavePath = null;
//		if (appPath.endsWith("/")) {
//			fullSavePath = appPath + SAVE_DIRECTORY;
//		} else {
//			fullSavePath = appPath + "/" + SAVE_DIRECTORY;
//		}
//		// Creates the save directory if it does not exists
//		File fileSaveDir = new File(fullSavePath);
//		if (!fileSaveDir.exists()) {
//			fileSaveDir.mkdir();
//		}
//		String filePath=null;
//		String fileName = extractFileName(part);
//		System.out.println(fileName);
//		String[] fn = fileName.split("(\\.)"); fileName = fn[0];
//		String ext = fn[(fn.length-1)];
//		if(!ext.isEmpty()) {
//			//generate a unique file name
//			UUID uuid = UUID.randomUUID(); fileName = fileName + "_" + uuid.toString() + "." + ext ;
//			if (fileName != null && fileName.length() > 0) {
//				filePath = fullSavePath + File.separator + fileName;
//				System.out.println("Write attachment to file: " + filePath);
//				// Write to file
//				part.write(filePath);
//			}
//		}
//		return fileName;
//	}
//
//	/**
//	* extraire le nom du fichier provenant du client
//	* @param part
//	* @return
//	*/
//	private String extractFileName(Part part) {
//		String contentDisp = part.getHeader("content-disposition");
//		String[] items = contentDisp.split(";");
//		for (String s : items) {
//			if (s.trim().startsWith("filename")) {
//				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
//				clientFileName = clientFileName.replace("\\", "/");
//				int i = clientFileName.lastIndexOf('/');
//				return clientFileName.substring(i + 1);
//			}
//		}
//		return null;
//	}

}