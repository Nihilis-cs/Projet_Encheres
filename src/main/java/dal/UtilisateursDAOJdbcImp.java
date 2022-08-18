package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bo.Utilisateurs;





public class UtilisateursDAOJdbcImp implements UtilisateursDao  {

	private final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, "
			+ "mot_de_passe, credit, administrateur)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ? ";
	private final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE pseudo = ?";
	private final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, "
			+ "rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";

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
				utilisateur = utilisateurBuilder(rs);
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
				throw new DALException("Erreur lors de la fermeture ou du statement : "+ e.getMessage());
			}
		}
		return utilisateur;
	}

	public Utilisateurs insertUtilisateur(Utilisateurs u) throws DALException  {
		try (Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
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

				ResultSet rsId = stmt.getGeneratedKeys();
				if(rsId.next()){
					u.setId(rsId.getInt(1));
				}

				con.commit();
			}catch(SQLException e){
				e.printStackTrace();
				con.rollback();	
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert : " + e.getMessage());
		}
		return u;
	}

	private Utilisateurs utilisateurBuilder(ResultSet rs) throws DALException {
		Utilisateurs user = new Utilisateurs();
		try {
			user.setId(rs.getInt("no_utilisateur"));
			user.setPseudo(rs.getString("pseudo"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setEmail(rs.getString("email"));
			user.setTelephone(rs.getString("telephone"));
			user.setRue(rs.getString("rue"));
			user.setCodePostal(rs.getString("code_postal"));
			user.setVille(rs.getString("ville"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			user.setCredit(rs.getInt("credit"));
			user.setAdmin(rs.getByte("administrateur"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert : " + e.getMessage());
		}
		return user;
	}

	public Utilisateurs selectByPseudo(String user) throws DALException{
		Utilisateurs utilisateur = null;

		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_BY_PSEUDO)){
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				utilisateur = utilisateurBuilder(rs);
			}else {
				throw new DALException("Pseudo introuvable ");
			}

		} catch (SQLException e) {
			throw new DALException("Erreur dans la selection par le pseudo : " + e.getMessage());
		}

		return utilisateur;
	}


	public Utilisateurs deleteUtilisateur(String pseudo) throws DALException {

		try (
				Connection  con = JdbcTools.getConnection();	
				PreparedStatement stmt = con.prepareStatement(DELETE_USER);
				){

			stmt.setString(1, pseudo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete user failed - pseudo=" + pseudo, e);
		}
		return null;
	}

	public Utilisateurs updateUtilisateur(Utilisateurs utilisateur) throws DALException {

		try (
				Connection  con = JdbcTools.getConnection();	
				PreparedStatement stmt = con.prepareStatement(UPDATE_USER);
				){
			try {
				con.setAutoCommit(false);

				stmt.setString(1, utilisateur.getPseudo());
				stmt.setString(2, utilisateur.getNom());
				stmt.setString(3, utilisateur.getPrenom());
				stmt.setString(4, utilisateur.getEmail());
				stmt.setString(5, utilisateur.getTelephone());
				stmt.setString(6, utilisateur.getRue());
				stmt.setString(7, utilisateur.getCodePostal());
				stmt.setString(8, utilisateur.getVille());
				stmt.setString(9, utilisateur.getMotDePasse());
				stmt.setInt(10, utilisateur.getId());
				stmt.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				new DALException("Données invalide =" +  e); }
		} catch (SQLException e) {
			new DALException("Connection Update user failed =" +  e);
		}
		return utilisateur;
	}






}