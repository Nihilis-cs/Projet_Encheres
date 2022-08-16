package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import bo.Utilisateurs;



public class UtilisateursDAOJdbcImp  {

	private final String INSERT = "insert into utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, "
			+ "mot_de_passe, credit, administrateur)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


	public Utilisateurs getUtilisateurByMailMDP(String pseudo, String mdp) throws DALException{
		Connection con = null;
		PreparedStatement stmt = null;
		Utilisateurs utilisateur=null;
		try {
			//Connexion à la base
			con = JdbcTools.getConnection();

			//Ecriture de la requête paramétrée
			String requete = "Select * From Utilisateurs where pseudo=? and mot_de_passe=?";

			stmt = con.prepareStatement(requete);
			stmt.setString(1, pseudo);
			stmt.setString(2, mdp);


			ResultSet rs = stmt.executeQuery();



			if(rs.next()) {
				int id = rs.getInt("no_utilisateur");
				String nom = rs.getString("pseudo");
				String motDePasse = rs.getString("mot_de_passe");
				utilisateur = new Utilisateurs(id, nom, motDePasse);
			}


		} catch(SQLException e) {
			throw new DALException("Erreur dans la selection par Mot de Passe et Pseudo : " + e.getMessage());
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("Erreur dans la DAL : " + e.getMessage());
			}
		}
		return utilisateur;
	}
	
	public Utilisateurs insert(Utilisateurs u) throws DALException  {
		try (Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT)){
			try {
				con.setAutoCommit(false);
				
				stmt.setString(1, u.getPseudo());
				stmt.setString(2, u.getNom());
				stmt.setString(3, u.getPrenom());
				stmt.setString(4, u.getEmail());
				stmt.setString(5, u.getTelephone());
				stmt.setString(6, u.getRue());
				stmt.setString(7, u.getCodePostal());
				stmt.setString(8, u.getVille());
				stmt.setString(9, u.getMotDePasse());
				stmt.setInt(10, u.getCredit());
				stmt.setByte(11, u.getAdmin());
				
				stmt.executeUpdate();
				
				con.commit();
			}catch(SQLException e){
				con.rollback();	
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert : " + e.getMessage());
		}
		return u;
	}







}


