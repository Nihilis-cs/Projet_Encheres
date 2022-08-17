package dal;

import bo.Utilisateurs;

public interface UtilisateursDao {
	public Utilisateurs getUtilisateurByMailMDP(String pseudo, String mdp) throws DALException;
	public Utilisateurs insertUtilisateur(Utilisateurs utilisateur) throws DALException;
}
