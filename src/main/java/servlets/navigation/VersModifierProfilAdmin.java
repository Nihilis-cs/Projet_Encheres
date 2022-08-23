package servlets.navigation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.UtilisateursManager;
import bo.Utilisateurs;

/**
 * Servlet implementation class VersModifierProfilAdmin
 */
@WebServlet("/navigation/modifierProfilAdmin")
public class VersModifierProfilAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VersModifierProfilAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noUser = Integer.parseInt(request.getParameter("user"));
		Utilisateurs user;
		
		try {
			user = UtilisateursManager.getInstance().selectByID(noUser);
			request.setAttribute("user", user);
			System.out.println(user);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adminModifierUser.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
