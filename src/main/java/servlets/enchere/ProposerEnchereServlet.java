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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateurs utilisateurActif = (Utilisateurs) session.getAttribute("utilisateurActif");
//		System.out.println(utilisateurActif.toString());
//		System.out.println(utilisateurActif.getCredit());

		int	creditNouvelEncherisseur = utilisateurActif.getCredit();

		//System.out.println("Je suis dans la servlet proposer une enchere");
		String nouvelEnchereStr = request.getParameter("enchere");
		//System.out.println("Enchere de l'user : " + enchereUtilisateurStr);
		int nouvelEnchere = Integer.parseInt(nouvelEnchereStr);

		if (nouvelEnchere >  creditNouvelEncherisseur   ) { //MESSAGE D'ERREUR SI L'USER N'A PAS ASSEZ DE CREDIT
			request.setAttribute("messageErreur", "Vous n'avez pas assez de crédit !");
			RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
			rs.forward(request, response);
		} else { //SINON L'ENCHERE S'EFFECTUE CORRECTEMENT
			EncheresManager em =EncheresManager.getInstance();
			UtilisateursManager um =UtilisateursManager.getInstance();
			try { 
				String noArticleStr = request.getParameter("noArticle");
				int noArticle = Integer.parseInt(noArticleStr); // JE RECUPERE LE NO ARTICLE DE LENCHERE LORSQUE Q'UN USER BET
				
				int ancienUserIdEnch = (em.selectByNoArticle(noArticle)).getEncherisseur().getId();//RECUPERATION DE L'ID DU DERNIER USER QUI A BET
				System.out.println("id user qui bet"+utilisateurActif.getId()); //RECUPERATION DE L'ID DE L'USER QUI BET
				System.out.println("id du last user qui a bet" + ancienUserIdEnch);
				
				if (ancienUserIdEnch == utilisateurActif.getId() ) { // CONDITION AFIN D'EMPECHER UN USER DE BET SUR UNE ENCHERE OU IL EST DEJA PLACE
					request.setAttribute("messageErreur", "Un utilisateur ne peut pas enchèrir sur un article qu'il a lui même mis en vente");
					throw new BLLException("Un utilisateur ne peut pas enchèrir sur un article qu'il a lui même mis en vente");
				} else {  
					int enchMontantCredit = (em.selectByNoArticle(utilisateurActif.getId())).getMontantEnchere(); //RECUPERATION DU MONTANT DE lENCHERE
					
					//CALCUL POUR LE REMBOURSEMENT
					creditNouvelEncherisseur = creditNouvelEncherisseur + enchMontantCredit; 
					creditNouvelEncherisseur = creditNouvelEncherisseur - nouvelEnchere;
					//System.out.println("Credit user après l'enchere qu'il vient d'effectuer :" +creditUtilisateurInt);
					
					System.out.println(noArticle);
					try {
						Encheres enchere = new Encheres(utilisateurActif, noArticle, LocalDateTime.now(), nouvelEnchere);
						//System.out.println(enchere);
						em.updateEnchere(enchere);
						utilisateurActif.setCredit(creditNouvelEncherisseur);
						um.updateCreditUtilisateur(utilisateurActif);
						//System.out.println(utilisateurActif.toString());
						request.setAttribute("messageSucces", "Enchere effectuée !");
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
			rs.forward(request, response);
		}
	}






}
