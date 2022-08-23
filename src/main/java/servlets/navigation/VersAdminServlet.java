package servlets.navigation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.UtilisateursManager;
import bo.Utilisateurs;


@WebServlet("/navigation/admin")
public class VersAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public VersAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Utilisateurs> listeUsers;
		
		try {
//			listeUsers = UtilisateursManager.getInstance().selectAll();
			request.setAttribute("liste", UtilisateursManager.getInstance().selectAll());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
