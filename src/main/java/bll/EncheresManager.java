package bll;

import dal.DAOFactory;
import dal.EncheresDao;

public class EncheresManager {
	private static EncheresManager enchMngr;
	private EncheresDao encheresDao;	
	
	private EncheresManager() {
		this.encheresDao = DAOFactory.getEnchereDao();
	}
	
	public static EncheresManager getInstance() {
		if(enchMngr == null) {
			enchMngr = new EncheresManager();
		}
		return enchMngr;
	}

}
