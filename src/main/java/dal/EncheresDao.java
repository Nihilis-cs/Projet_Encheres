package dal;

import java.util.List;

import bo.Encheres;

public interface EncheresDao {
	public Encheres insertEnchere(Encheres ench) throws DALException;
	public Encheres selectByNoArticle();
	public Encheres updateEnchere(Encheres enchere) throws DALException;
}
