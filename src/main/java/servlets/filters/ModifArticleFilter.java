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
import javax.servlet.http.HttpSession;

import bll.ArticlesManager;
import bll.BLLException;
import bo.Articles;
import bo.EtatsVente;
import bo.Utilisateurs;

/**
 * Servlet Filter implementation class ModifArticleFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE
}
, urlPatterns = { "/navigation/modifierEnchere" })
public class ModifArticleFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 8642908749823260770L;

	public ModifArticleFilter() {
		super();
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		String strNoArticle = httpRequest.getParameter("article");
		int noArticle = Integer.parseInt(strNoArticle);
		ArticlesManager am = ArticlesManager.getInstance();
		
		try {
			Articles a = am.selectById(noArticle) ;
			if(a.getEtatVente() == EtatsVente.CR) {	
				chain.doFilter(request, response); //Go modifier article
			}
			else {
				RequestDispatcher rd = httpRequest.getRequestDispatcher("/navigation/detailsEnchere");
				rd.forward(httpRequest, response);

			}
		} catch (BLLException e) {
			httpRequest.setAttribute("messageErreur", "Si ce message s'affiche c'est qu'on n'a aucune "
					+ "idée de ce qui a planté. Bon chance.");
			e.printStackTrace();
		}
		RequestDispatcher rd = httpRequest.getRequestDispatcher("/navigation/detailsEnchere");
		rd.forward(httpRequest, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
