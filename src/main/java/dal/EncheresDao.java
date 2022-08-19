package dal;


import bo.Encheres;

public interface EncheresDao {
	public Encheres insertEnchere(Encheres ench) throws DALException;
	public Encheres selectByNoArticle(int id) throws DALException;
}
