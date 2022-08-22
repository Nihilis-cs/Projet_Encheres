package dal;

import java.util.List;

import bo.Articles;


public interface ArticlesDao {
	public Articles insert(Articles a)throws DALException;
	public List<Articles> selectAll() throws DALException;
	public List<Articles> selectAllEC() throws DALException;
	public void updateEtatVente() throws DALException;
//	public Articles selectByNom();
//	public Articles selectByUtilisateur();
	
}
