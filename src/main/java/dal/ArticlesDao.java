package dal;

import java.util.List;

import bo.Articles;
import bo.Retraits;


public interface ArticlesDao {
	public Articles insert(Articles a,Retraits r)throws DALException;
	public List<Articles> selectAll() throws DALException;
	public List<Articles> selectAllFilter(String filter, int idUser, int idCategorie, String nomArticle) throws DALException;
	public void updateEtatVente() throws DALException;
	public Articles SelectById(int id) throws DALException;
	public void update(Articles a, Retraits r) throws DALException;
//	public Articles selectByNom();
//	public Articles selectByUtilisateur();
	public void delete(int id)throws DALException;
	
}
