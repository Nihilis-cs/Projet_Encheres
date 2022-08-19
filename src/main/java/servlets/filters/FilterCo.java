package servlets.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import bo.Utilisateurs;

/**
 * Servlet Filter implementation class FilterCo
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE
		}
					, urlPatterns = { "/navigation/vente", "/enchere/proposer" })
public class FilterCo extends HttpFilter implements Filter {
       

    public FilterCo() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Utilisateurs u = (Utilisateurs) httpRequest.getSession().getAttribute("utilisateurActif");
		if (u != null) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(httpRequest, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
