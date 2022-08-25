package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Images;



public class ImagesDAOJdbcImp {
	private final static String SELECTBYID_QUERY = "SELECT * FROM TRUCS WHERE id = ?;";
	private final static String INSERT_QUERY = "INSERT INTO TRUCS(name, picture) VALUES (?,?);";

	public Images selectById(int id) throws SQLException {
		Images image = null;
		
		try(Connection cnx = JdbcTools.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECTBYID_QUERY);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				image = new Images  (rs.getInt("id"),
									rs.getString("name"), 
									rs.getString("picture"));			
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Echec de l'extraction", e);
		} 		
		
		return image;
	}
	
	public void insert(Images i) throws SQLException {
		
		try(Connection cnx = JdbcTools.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, i.getName());
			pstmt.setString(2, i.getPicture());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				i.setId(rs.getInt(1));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Echec de création de l'image", e);
		} 		

	}
	
	
}
