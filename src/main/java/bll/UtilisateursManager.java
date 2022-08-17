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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
}