package dal;


public abstract class DAOFactory {
	public static UtilisateursDao getUtilisateurDAO()
	{
		return new UtilisateursDAOJdbcImp();
	}
	
	public static EncheresDao getEnchereDao() {
		return new EncheresDAOJdbcImp();
	}
	
	public static ArticlesDao getArticleDao() {
		return new ArticlesDaoJdbcImpl();
		
	}
	
	public static RetraitsDAO getRetraitsDao() {
		return new RetraitsDaoJdbcImp();
	}
}
