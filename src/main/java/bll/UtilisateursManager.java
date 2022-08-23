package bll;

import java.util.List;

import bo.Utilisateurs;
import dal.DALException;
import dal.DAOFactory;
import dal.UtilisateursDao;


public class UtilisateursManager {
	private static UtilisateursManager usrMngr;
	private UtilisateursDao utilisateurDao;	
	
	private UtilisateursManager() {
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
			throw new BLLException(e.getMessage());
			//Documenter BLLException
		}
		return u;
	}

	public Utilisateurs insertUtilisateur(Utilisateurs utilisateur) throws BLLException {
		Utilisateurs u=null;
		try {
			u =  this.utilisateurDao.insertUtilisateur(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return u;
	}

	public void validerUtilisateur(Utilisateurs u) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(u==null){
			throw new BLLException("Article null");
		}
		//Les attributs des articles sont obligatoires
		if(u.getPseudo()==null || u.getPseudo().trim().length()==0){
			sb.append("Le pseudo utilisateur est obligatoire.\n");
			valide = false;
		}
		if(u.getNom()==null || u.getNom().trim().length()==0){
			sb.append("Le nom utilisateur  est obligatoire.\n");
			valide = false;
		}
		if(u.getPrenom()==null || u.getPrenom().trim().length()==0){
			sb.append("Le prénom utilisateur  est obligatoire.\n");
			valide = false;
		}
		if(u.getEmail()==null || u.getEmail().trim().length()==0){
			sb.append("L'email de l'utilisateur est obligatoire.\n");
			valide = false;
		}
		if(u.getRue()==null || u.getRue().trim().length()==0){
			sb.append("La rue de l'utilisateur  est obligatoire.\n");
			valide = false;
		}
		if(u.getCodePostal()==null || u.getCodePostal().trim().length()==0){
			sb.append("Le code postal de l'utilisateur est obligatoire.\n");
			valide = false;
		}
		if(u.getMotDePasse()==null || u.getMotDePasse().trim().length()==0){
			sb.append("Le mot de passe de l'utilisateur  est obligatoire.\n");
			valide = false;
		}
		if(u.getRue()==null || u.getRue().trim().length()==0){
			sb.append("La rue de l'utilisateur  est obligatoire.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
	
	public Utilisateurs selectByPseudo(String user) throws BLLException{
		Utilisateurs u=null;
		
		try {
			u = this.utilisateurDao.selectByPseudo(user);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
			//Documenter BLLException
		}
		return u;
	}
	
	public Utilisateurs deleteUtilisateur(String pseudo) throws BLLException {
		Utilisateurs u=null;
		try {
			u =  this.utilisateurDao.deleteUtilisateur(pseudo);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public Utilisateurs updateUtilisateur(Utilisateurs utilisateur) throws BLLException {
		try {
			utilisateur =  this.utilisateurDao.updateUtilisateur(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
//	public Utilisateurs updateCreditUtilisateur(int credit, String pseudo) throws BLLException {
//		Utilisateurs u=null;
//		try {
//			u =  this.utilisateurDao.updateCreditUtilisateur(credit, pseudo);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
//		return u;
//	}

	public Utilisateurs updateCreditUtilisateur(Utilisateurs utilisateur) throws BLLException {
		try {
			utilisateur =  this.utilisateurDao.updateCreditUtilisateur(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
		return utilisateur;
	}

	public boolean utilisateurAEnchere(int id) throws BLLException {
		boolean reponse = true;
		try {
			reponse = this.utilisateurDao.utilisateurAEnchere(id);
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
		return reponse;
	}

	public boolean utilisateurAArticle(int id) throws BLLException  {
		boolean reponse = true;
		try {
			reponse = this.utilisateurDao.utilisateurAArticle(id);
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
		return reponse;
	}
	
	public List<Utilisateurs> selectAll() throws BLLException{
		List<Utilisateurs> u=null;
		
		try {
			u = this.utilisateurDao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
			//Documenter BLLException
		}
		return u;
	}

}