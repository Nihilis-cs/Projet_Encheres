package dal;


public abstract class DAOFactory {
	public static UtilisateursDao getUtilisateurDAO()
	{
		return new UtilisateursDAOJdbcImp();
	}
}
