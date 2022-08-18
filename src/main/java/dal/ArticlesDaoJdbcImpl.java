package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import bo.Articles;

public class ArticlesDaoJdbcImpl implements ArticlesDao {
	
	private final String INSERT_Article= "insert into ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	
	public Articles insert(Articles a) throws DALException {
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT_Article, Statement.RETURN_GENERATED_KEYS)){
			try {
				con.setAutoCommit(false);
				
				stmt.setString(1, a.getNomArticle());
				stmt.setString(2, a.getDescription());
				stmt.setTimestamp(3, java.sql.Timestamp.valueOf(a.getDateDebutEnchere()));
				stmt.setTimestamp(4, java.sql.Timestamp.valueOf(a.getDateFinEnchere()));
				stmt.setInt(5, a.getPrixInitial());
				stmt.setInt(6, a.getPrixVente());
				stmt.setInt(7, a.getVendeur().getId());
				stmt.setInt(8, a.getCategorie().getNoCategorie());
				stmt.setString(9, a.getEtatVente().toString());
			}catch(SQLException e){
				e.printStackTrace();
				con.rollback();	
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert de l'article : " + e.getMessage());
		}
		
		return a;
	}

}
