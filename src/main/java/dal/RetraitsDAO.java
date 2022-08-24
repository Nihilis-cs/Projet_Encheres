package dal;

import bo.Retraits;

public interface RetraitsDAO {
	public Retraits insertRetrait(Retraits r) throws DALException;
	public Retraits selectByNoArticle(int no) throws DALException;
	
}
