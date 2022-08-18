package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import bo.Articles;
import bo.EtatsVente;
import bo.Utilisateurs;

public class ArticlesDaoJdbcImpl implements ArticlesDao {
	
	private final String INSERT_Article= "insert into ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	private final String SELECT_EC= "Select * from ARTICLES_VENDUS where etat_vente = 'EC' ";
	
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

	@Override
	public List<Articles> selectAll() throws DALException{
		// TODO Auto-generated method stub
		return null;
	}
	//CallableStatement cstmt = con.prepareCall("updateArticle");

	@Override
	public List<Articles> selectAllEC() throws DALException {
		List<Articles> listeArticlesEC = null; 
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_EC, Statement.RETURN_GENERATED_KEYS)){
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Articles article = articleBuilder(rs);
				listeArticlesEC.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème lors de l'accès à la BDD");
		}
		
		
		
		
		return listeArticlesEC;
	}

	private Articles articleBuilder(ResultSet rs) throws DALException {
		Articles art = new Articles();
		UtilisateursDao uDao = new UtilisateursDAOJdbcImp(); 
		try {
			art.setNoArticle(rs.getInt("no_article"));
			art.setNomArticle(rs.getString("nom_article"));
			art.setDescription(rs.getString("description"));
			art.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime())); 
			art.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()));
			art.setPrixInitial(rs.getInt("prix_initial"));
			art.setPrixVente(rs.getInt("prix_vente"));
			art.setVendeur(uDao.selectByID(rs.getInt("no_utilisateur")));
			art.setCategorie(null); //A modifier quand les catégories seront gérées
			switch(rs.getString("etat_vente")) {
				case "CR" : art.setEtatVente(EtatsVente.CR);
					break;
				case "EC" : art.setEtatVente(EtatsVente.EC);
					break;
				case "VD" : art.setEtatVente(EtatsVente.VD);
					break;
				case "RT" : art.setEtatVente(EtatsVente.RT);
					break;
				default : art.setEtatVente(null); 
					break;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert : " + e.getMessage());
		}
		return art;
	}
}
