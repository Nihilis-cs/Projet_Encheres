package bll;

import java.util.List;

import bo.Articles;
import dal.ArticlesDao;
import dal.DALException;
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
	
	public Articles insert(Articles article) throws BLLException{
		Articles a =null;
		try {
			a = this.articleDao.insert(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public List<Articles> selectAll() throws BLLException{
		List<Articles> liste = null;
		try {
			liste = this.articleDao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return liste;
	}
	
	
}
