package servlets.navigation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VersLoginServlet
 */
@WebServlet("/navigation/login")
public class VersLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public VersLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null ) { // RECUPERATION DES COOKIES AFIN DES LES AFFICHERS SUR LES LOGS
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("pseudo")) {
					request.setAttribute("pseudo", cookie.getValue());
				}
				if(cookie.getName().equals("mdp")) {
					request.setAttribute("mdp", cookie.getValue());
				}
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
