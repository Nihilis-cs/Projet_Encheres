package bll;

import java.util.List;

import bo.Articles;
import bo.Retraits;
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
	
	public Articles insert(Articles article, Retraits retrait) throws BLLException{
		Articles a =null;
		try {
			a = this.articleDao.insert(article, retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("insert de merde ta capté");
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
	
	public List<Articles> selectAllFilter(String filter, int idUser, int idCategorie, String nomArticle) throws BLLException{
		List<Articles> liste = null;
		try {
			liste = this.articleDao.selectAllFilter(filter, idUser, idCategorie, nomArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return liste;
	}
	
	public Articles selectById(int id) throws BLLException{
		Articles a = null;
		try {
			a= this.articleDao.SelectById(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return a;
		
	}
	
	public void update(Articles a, Retraits r) throws BLLException{
		try {
			this.articleDao.update(a, r);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
	}
	
	
}
