package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import bo.Utilisateurs;



public class UtilisateursDAOJdbcImp  {




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







}


