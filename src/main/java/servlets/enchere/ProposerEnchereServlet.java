package servlets.enchere;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.EncheresManager;
import bll.UtilisateursManager;
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
		System.out.println("Je suis dans la servlet proposer une enchere dans la page principale");
		String enchereUtilisateurS = request.getParameter("enchere");
		System.out.println("Enchere de l'user : " + enchereUtilisateurS);
		 int enchereUtilisateurI = Integer.parseInt(enchereUtilisateurS);
		EncheresManager em =EncheresManager.getInstance();
		
		try {
			Encheres enchere = new Encheres(enchereUtilisateurI, 2);
			System.out.println(enchere);
			em.updateEnchere(enchere);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rs.forward(request, response);
	}

}
