package dal;

import bo.Utilisateurs;

public interface UtilisateursDao {
	public Utilisateurs getUtilisateurByMailMDP(String pseudo, String mdp) throws DALException;
	public Utilisateurs insertUtilisateur(Utilisateurs utilisateur) throws DALException;
	public Utilisateurs deleteUtilisateur(String pseudo) throws DALException;
	public Utilisateurs updateUtilisateur(Utilisateurs utilisateur) throws DALException;
	public Utilisateurs updateCreditUtilisateur(Utilisateurs utilisateur) throws DALException;
	public Utilisateurs selectByPseudo(String user) throws DALException;
	public Utilisateurs selectByID(int id) throws DALException;
	public boolean utilisateurAEnchere(int id)throws DALException;
}
