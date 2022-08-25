package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Retraits;

public class RetraitsDaoJdbcImp implements  RetraitsDAO {
	private final String INSERT_RETRAIT = "insert into RETRAITS (no_article, rue, code_postal, ville)"
			+ "values(?,?,?,?)";
	private final String SELECT_BY_NOARTICLE="select * from RETRAITS\r\n"
			+ "where no_article = ?";
	
	private final String UPDATE_R = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?;";
	
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
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retrait;
		
	}
	
	public void update(Retraits r)throws DALException{
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(UPDATE_R)){
			try {
				con.setAutoCommit(false);
				stmt.setString(1, r.getRue());
				stmt.setString(2, r.getCode_postal());
				stmt.setString(3, r.getVille());
				stmt.setInt(4, r.getNoArticle());
				System.out.println("update retraits avec : " + r.getNoArticle() + " " + r.getRue() + " " + r.getCode_postal() + " " + r.getVille() );
				stmt.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'update du retrait : " + e.getMessage());
		}

	}
	
	
	
}
