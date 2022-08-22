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

import bo.Encheres;

public class EncheresDAOJdbcImp implements EncheresDao {
	
	private final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)VALUES (?, ?, ?, ?)";
	private final String UPDATE_ENCHERE = "UPDATE ENCHERES SET montant_enchere = ?, date_enchere = ?, no_utilisateur = ? WHERE no_article = ?";
	private final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
	
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
	public Encheres selectByNoArticle() {
		// TODO Auto-generated method stub
		return null;
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
