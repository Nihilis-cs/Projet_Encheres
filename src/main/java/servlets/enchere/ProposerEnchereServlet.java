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
					//System.out.println(vendeur);
					//System.out.println("id user qui bet"+utilisateurActif.getId()); //RECUPERATION DE L'ID DE L'USER QUI BET
					//System.out.println("id du last user qui a bet" + ancienUserIdEnch);

					if (ancienUserIdEnch == utilisateurActif.getId() ) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR UNE ENCHERE OU IL EST DEJA PLACE
						request.setAttribute("messageErreur", "Vous avez déjà enchéri sur  cet article");
						throw new BLLException("Vous avez déjà enchéri sur  cet article");
					} else if( vendeur == utilisateurActif.getId()) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR SON ENCHERE
						request.setAttribute("messageErreur", "Cette enchère vous appartient vous ne pouvez pas enchérir dessus !");
						throw new BLLException("Cette enchère vous appartient vous ne pouvez pas enchérir dessus !");
					} 
					//Encheres ancienEnchere = em.selectByNoArticle(article.getNoArticle());
					Encheres enchere = new Encheres(utilisateurActif, noArticle, LocalDateTime.now(), nouvelEnchere);
					em.updateEnchere(enchere);
				} else { //Sinon insert
					Encheres enchere = new Encheres(utilisateurActif, noArticle, LocalDateTime.now(), nouvelEnchere);
					em.insertEnchere(enchere);
				}
				//Calculer les nouveaux crédits de l'ancien encherisseur et du nouveau
				//int enchMontantCredit = (em.selectByNoArticle(utilisateurActif.getId())).getMontantEnchere(); //RECUPERATION DU MONTANT DE lENCHERE

				//CALCUL POUR LE REMBOURSEMENT
				//System.out.println("Avant calcul credit actuel de l'encherisseur :"+creditNouvelEncherisseur);		
				int ancienEnchere = article.getEnchere().getMontantEnchere();
				Utilisateurs ancienEncherisseur = um.selectByID(ancienUserIdEnch);
				System.out.println("Ancien encherisseur qui va recevoir ses crédits"+ancienEncherisseur);
				int creditAncEnch = ancienEncherisseur.getCredit()+ancienEnchere;
				//System.out.println("Credit de l'ancien encherisseur" + creditAncEnch);
				ancienEncherisseur.setCredit(creditAncEnch);
				//System.out.println("ancien encherisseur qui va être recrediter : "+ancienEncherisseur);
				um.updateCreditUtilisateur(ancienEncherisseur);

				creditNouvelEncherisseur -= nouvelEnchere;
				//System.out.println("Après calcul credit de l'encherisseur :"+creditNouvelEncherisseur);
				//System.out.println("Credit user après l'enchere qu'il vient d'effectuer :" +creditUtilisateurInt);
				//System.out.println(article.getEnchere().toString());
				try {
					utilisateurActif.setCredit(creditNouvelEncherisseur);
					um.updateCreditUtilisateur(utilisateurActif);
					//System.out.println(utilisateurActif.toString());
					request.setAttribute("messageSucces", "Enchere effectuée !");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (BLLException e) {
			request.setAttribute("messageErreur", "");
			e.printStackTrace();
		}

		RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
		rs.forward(request, response);

		//		try { 
		//
		//			if (em.selectByNoArticle(noArticle)!=null) {
		//				//int ancienUserIdEnch = (em.selectByNoArticle(noArticle)).getEncherisseur().getId();//RECUPERATION DE L'ID DU DERNIER USER QUI A BET
		//				//System.out.println(ancienUserIdEnch);
		//				//int vendeur = (am.selectById(noArticle)).getVendeur().getId(); 
		//				//							System.out.println(vendeur);
		//				//							System.out.println("id user qui bet"+utilisateurActif.getId()); //RECUPERATION DE L'ID DE L'USER QUI BET
		//				//							System.out.println("id du last user qui a bet" + ancienUserIdEnch);
		//
		//				if (ancienUserIdEnch == utilisateurActif.getId() ) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR UNE ENCHERE OU IL EST DEJA PLACE
		//					request.setAttribute("messageErreur", "Vous avez déjà enchéri sur  cet article");
		//					throw new BLLException("Un utilisateur ne peut pas enchèrir sur un article qu'il a lui même mis en vente");
		//				} else if( vendeur == utilisateurActif.getId()) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR SON ENCHERE
		//					request.setAttribute("messageErreur", "Cette enchère vous appartient vous ne pouvez pas enchérir dessus !");
		//					throw new BLLException("Cette enchère vous appartient vous ne pouvez pas enchérir dessus !");
		//				} else {  
		//					int enchMontantCredit = (em.selectByNoArticle(utilisateurActif.getId())).getMontantEnchere(); //RECUPERATION DU MONTANT DE lENCHERE
		//
		//					//CALCUL POUR LE REMBOURSEMENT
		//					creditNouvelEncherisseur = creditNouvelEncherisseur + enchMontantCredit; 
		//					creditNouvelEncherisseur = creditNouvelEncherisseur - nouvelEnchere;
		//					//System.out.println("Credit user après l'enchere qu'il vient d'effectuer :" +creditUtilisateurInt);
		//
		//					System.out.println(noArticle);
		//					try {
		//						Encheres enchere = new Encheres(utilisateurActif, noArticle, LocalDateTime.now(), nouvelEnchere);
		//						//System.out.println(enchere);
		//						em.updateEnchere(enchere);
		//						utilisateurActif.setCredit(creditNouvelEncherisseur);
		//						um.updateCreditUtilisateur(utilisateurActif);
		//						//System.out.println(utilisateurActif.toString());
		//						request.setAttribute("messageSucces", "Enchere effectuée !");
		//					} catch (BLLException e) {
		//						// TODO Auto-generated catch block
		//						e.printStackTrace();
		//					}
		//				}
		//			} else { //SI IL N'Y A PAS D'ENCHERE QUE FAIRE : 
		//
		//			}	
		//
		//
		//
		//		} catch (BLLException e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}
		//
		//		RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
		//		rs.forward(request, response);
	}
}

