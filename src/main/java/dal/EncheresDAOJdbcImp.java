package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import bo.Articles;
import bo.Encheres;

public class EncheresDAOJdbcImp implements EncheresDao {
	
	private final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)VALUES (?, ?, ?, ?)";
	private final String UPDATE_ENCHERE = "UPDATE ENCHERES SET montant_enchere = ?, date_enchere = ?, no_utilisateur = ? WHERE no_article = ?";
	private final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES e INNER JOIN ARTICLES_VENDUS a ON a.no_article = e.no_article WHERE e.no_article = ?";
	
	public Encheres insertEnchere(Encheres ench) throws DALException {
		try (Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS)){
			try {
				con.setAutoCommit(false);

				stmt.setInt(1, ench.getEncherisseur().getId());
				stmt.setInt(2, ench.getNoArticle());
				//stmt.setDate(3, ench.getDateEnchere());
				stmt.setTimestamp(3, java.sql.Timestamp.valueOf(ench.getDateEnchere()));
				stmt.setInt(4, ench.getMontantEnchere());

				stmt.executeUpdate();
				

				con.commit();
			}catch(SQLException e){
				e.printStackTrace();
				con.rollback();	
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert de l'enchere : " + e.getMessage());
		}
		return ench;
	}


	@Override
	public Encheres selectByNoArticle(int id)throws DALException {
		Encheres enchere = null;
		try (Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_BY_ARTICLE)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				enchere = new Encheres();
				enchere.setNoArticle(id);
				enchere.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime())); 
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				UtilisateursDao uDao = new UtilisateursDAOJdbcImp();
				enchere.setEncherisseur(uDao.selectByID(rs.getInt("no_utilisateur"))); //Remplacer par encherisseur pouet
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors du select de l'enchere : " + e.getMessage());
		}
		return enchere;
	}


	@Override
	public Encheres updateEnchere(Encheres enchere) throws DALException {
		try (
				Connection  con = JdbcTools.getConnection();	
				PreparedStatement stmt = con.prepareStatement(UPDATE_ENCHERE);
				){
			try {
				con.setAutoCommit(false);

				stmt.setInt(1, enchere.getMontantEnchere());
				stmt.setTimestamp(2, java.sql.Timestamp.valueOf(enchere.getDateEnchere()));
				stmt.setInt(3, enchere.getEncherisseur().getId());
				stmt.setInt(4, enchere.getNoArticle());
				
				stmt.executeUpdate();
				
//				if (nbLignes == 0) {
//					insertEnchere(enchere);
//				}
				
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				new DALException("Données invalide =" +  e); }
		} catch (SQLException e) {
			new DALException("Connection Update enchere failed =" +  e);
		}
		return enchere;
	}

}
