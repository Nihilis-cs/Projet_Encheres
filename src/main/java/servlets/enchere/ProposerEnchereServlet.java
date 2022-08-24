package servlets.enchere;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticlesManager;
import bll.BLLException;
import bll.EncheresManager;
import bll.UtilisateursManager;
import bo.Articles;
import bo.Encheres;
import bo.Utilisateurs;

/**
 * Servlet implementation class ProposerEnchereServlet
 */
@WebServlet("/enchere/proposer")
public class ProposerEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProposerEnchereServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EncheresManager em = EncheresManager.getInstance();
		UtilisateursManager um = UtilisateursManager.getInstance();
		ArticlesManager am =ArticlesManager.getInstance();

		Utilisateurs utilisateurActif = (Utilisateurs) session.getAttribute("utilisateurActif");
		int	creditNouvelEncherisseur = utilisateurActif.getCredit();

		String nouvelEnchereStr = request.getParameter("enchere"); //RECUPERATION DU MONTANT DE L'ENCHER EFFECTUE PAR LUSER ACTUELLEMENT CO 
		int nouvelEnchere = Integer.parseInt(nouvelEnchereStr);//RECUPERATION DU MONTANT DE L'ENCHER EFFECTUE PAR LUSER ACTUELLEMENT CO 

		String noArticleStr = request.getParameter("noArticle");// JE RECUPERE LE NO ARTICLE DE LENCHERE LORSQUE Q'UN USER BET
		int noArticle = Integer.parseInt(noArticleStr); // JE RECUPERE LE NO ARTICLE DE LENCHERE LORSQUE Q'UN USER BET

		int ancienUserIdEnch = 0; 
		Articles article;

		try {
			article = am.selectById(noArticle);
			if (nouvelEnchere >  creditNouvelEncherisseur   ) { //MESSAGE D'ERREUR SI L'USER N'A PAS ASSEZ DE CREDIT
				request.setAttribute("messageErreur", "Vous n'avez pas assez de crédit !");
				RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
				rs.forward(request, response);
			} else { //SINON L'ENCHERE S'EFFECTUE CORRECTEMENT
				if (article.getEnchere()!= null) { //Si l'enchere existe deja pour cet article alors update;
					ancienUserIdEnch = (em.selectByNoArticle(noArticle)).getEncherisseur().getId();//RECUPERATION DE L'ID DU DERNIER USER QUI A BET
					System.out.println("Id du dernier user qui a bet : "+ancienUserIdEnch);
					int vendeur = article.getVendeur().getId();

					if (ancienUserIdEnch == utilisateurActif.getId() ) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR UNE ENCHERE OU IL EST DEJA PLACE
						request.setAttribute("messageErreur", "Vous avez déjà enchéri sur  cet article");
						throw new BLLException("Vous avez déjà enchéri sur  cet article");
					} else if( vendeur == utilisateurActif.getId()) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR SON ENCHERE
						request.setAttribute("messageErreur", "Cette enchère vous appartient vous ne pouvez pas enchérir dessus !");
						throw new BLLException("Cette enchère vous appartient vous ne pouvez pas enchérir dessus !");
					}
					
					Encheres enchere = new Encheres(utilisateurActif, noArticle, LocalDateTime.now(), nouvelEnchere);
					em.updateEnchere(enchere);
					int ancienEnchere = article.getEnchere().getMontantEnchere(); //
					Utilisateurs ancienEncherisseur = um.selectByID(ancienUserIdEnch);
					System.out.println("Ancien encherisseur qui va recevoir ses crédits"+ancienEncherisseur);
					int creditAncEnch = ancienEncherisseur.getCredit()+ancienEnchere;
					ancienEncherisseur.setCredit(creditAncEnch);
					um.updateCreditUtilisateur(ancienEncherisseur);
				} else { //Sinon insert
					Encheres enchere = new Encheres(utilisateurActif, noArticle, LocalDateTime.now(), nouvelEnchere);
					em.insertEnchere(enchere);
				}
				creditNouvelEncherisseur -= nouvelEnchere;

				try {
					utilisateurActif.setCredit(creditNouvelEncherisseur);
					um.updateCreditUtilisateur(utilisateurActif);
					request.setAttribute("messageSucces", "Enchere effectuée !");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (BLLException e) {

			e.printStackTrace();
		}

		RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
		rs.forward(request, response);

	}
}

