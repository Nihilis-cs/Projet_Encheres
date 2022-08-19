package bll;

import dal.ArticlesDao;
import dal.DAOFactory;

public class ArticlesManager {
	private static ArticlesManager artmngr;
	private ArticlesDao articleDao;
	
	private ArticlesManager() {
		this.articleDao = DAOFactory.getArticleDao();
	}
	
	public static ArticlesManager getInstance() {
		if(artmngr == null) {
			artmngr = new ArticlesManager();
		}
		return artmngr;
	}
	
	
	
	
}
