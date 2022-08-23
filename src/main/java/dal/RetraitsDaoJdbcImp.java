package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Retraits;

public class RetraitsDaoJdbcImp {
	private final String INSERT_RETRAIT = "insert into RETRAITS (no_article, rue, code_postal, ville)"
			+ "values(?,?,?,?)";
	private final String SELECT_BY_NOARTICLE="select * from RETRAITS\r\n"
			+ "where no_article = ?";
	
	public Retraits insertRetrait(Retraits r) throws DALException {
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT_RETRAIT)){
			try {
				con.setAutoCommit(false);
				stmt.setInt(1, r.getNoArticle());
				stmt.setString(2, r.getRue());
				stmt.setString(3, r.getCode_postal());
				stmt.setString(4, r.getVille());
				stmt.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert du retrait : " + e.getMessage());
		}
		return r;
		
	}
	
	public Retraits selectByNoArticle(int no) throws DALException{
		Retraits retrait=null;
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_BY_NOARTICLE)){
			
			stmt.setInt(1, no);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				retrait = new Retraits();
				retrait.setNoArticle(no);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retrait;
		
	}
	
}
