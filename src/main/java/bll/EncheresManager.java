package bll;

import bo.Encheres;
import bo.Utilisateurs;
import dal.DALException;
import dal.DAOFactory;
import dal.EncheresDao;

public class EncheresManager {
	private static EncheresManager enchMngr;
	private EncheresDao EncheresDao;	
	
	private EncheresManager() {
		this.EncheresDao = DAOFactory.getEnchereDao();
	}
	
	public static EncheresManager getInstance() {
		if(enchMngr == null) {
			enchMngr = new EncheresManager();
		}
		return enchMngr;
	}

	
	public Encheres insertEnchere(Encheres enchere) throws BLLException {
		Encheres ench=null;
		try {
			ench =  this.EncheresDao.insertEnchere(enchere);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return ench;
	}
	
	public Encheres updateEnchere(Encheres enchere) throws BLLException {
		Encheres ench=null;
		try {
			ench =  this.EncheresDao.updateEnchere(enchere);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return ench;
	}
	
}
