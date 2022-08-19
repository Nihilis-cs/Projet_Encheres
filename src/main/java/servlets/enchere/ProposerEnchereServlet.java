package servlets.enchere;

import java.io.IOException;
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
		System.out.println(utilisateurActif.toString());
		System.out.println(utilisateurActif.getCredit());
		
	int	creditUtilisateurI = utilisateurActif.getCredit();
		
		
		System.out.println("Je suis dans la servlet proposer une enchere");
		String enchereUtilisateurS = request.getParameter("enchere");
		System.out.println("Enchere de l'user : " + enchereUtilisateurS);
		int enchereUtilisateurI = Integer.parseInt(enchereUtilisateurS);
		 
		if (enchereUtilisateurI >  creditUtilisateurI   ) {
			request.setAttribute("creditErreur", "Vous n'avez pas assez de crédit/le montant de l'enchère est infèrieur à l'enchère actuel !");
			RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
			rs.forward(request, response);
		 } else {
			 EncheresManager em =EncheresManager.getInstance();
			 UtilisateursManager um =UtilisateursManager.getInstance();
			 creditUtilisateurI = creditUtilisateurI - enchereUtilisateurI;
			 System.out.println("Credit user après l'enchere qu'il vient d'effectuer :" +creditUtilisateurI);
				try {
					Encheres enchere = new Encheres(enchereUtilisateurI, 2);
					System.out.println(enchere);
					em.updateEnchere(enchere);
					utilisateurActif.setCredit(creditUtilisateurI);
					um.updateCreditUtilisateur(utilisateurActif);
					System.out.println(utilisateurActif.toString());
					request.setAttribute("creditErreur", "Enchere effectué !");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rs = request.getRequestDispatcher("/navigation/accueil");
				rs.forward(request, response);
			}
		 }
		 
		 
		 
		 
		

}
