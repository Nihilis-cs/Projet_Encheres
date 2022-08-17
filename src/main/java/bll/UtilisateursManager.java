package bll;

import bo.Utilisateurs;
import dal.DALException;
import dal.DAOFactory;
import dal.UtilisateursDao;

public class UtilisateursManager {
	private static UtilisateursManager usrMngr;
	private UtilisateursDao utilisateurDao;	
	
	public UtilisateursManager() {
		this.utilisateurDao = DAOFactory.getUtilisateurDAO();
	}
	
	public static UtilisateursManager getInstance() {
		if(usrMngr == null) {
			usrMngr = new UtilisateursManager();
		}
		return usrMngr;
	}

	public Utilisateurs getUtilisateurByMailMdp(String pseudo, String mdp) throws BLLException {		
		Utilisateurs u=null;
		
		try {
			u = this.utilisateurDao.getUtilisateurByMailMDP(pseudo, mdp);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
			//Documenter BLLException
		}
		return u;
	}

	public Utilisateurs insertUtilisateur(Utilisateurs utilisateur) throws BLLException {
		Utilisateurs u = null;
		
		try {
			 u = this.utilisateurDao.insertUtilisateur(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return u;
	}






}