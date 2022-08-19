package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bo.Encheres;

public class EncheresDAOJdbcImp implements EncheresDao {
	
	private final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)VALUES (?, ?, ?, ?)";
	private final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
//
// PAS AU BON ENDROIT MAIS DELETE PAS LOL 
//PAS AU BON ENDROIT MAIS DELETE PAS LOL 
//PAS AU BON ENDROIT MAIS DELETE PAS LOL 
	public Encheres insertEnchere(Encheres ench) throws DALException {
		try (Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
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
	public Encheres selectByNoArticle(int id) throws DALException {
		Encheres ench = null;
		try (Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_BY_ARTICLE, Statement.RETURN_GENERATED_KEYS)){
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					ench = new Encheres();
					ench.setDateEnchere(null);
					ench.setEncherisseur(null);
					ench.setMontantEnchere(0);
					ench.setNoArticle(id);
				}else {
					throw new DALException ("Il n'y a pas d'enchère correspondant à cet article.");
				}
		} catch(SQLException e){
			e.printStackTrace();
			throw new DALException("Erreur lors du select de l'enchere : " + e.getMessage());
		}
		return ench;
	}

}
