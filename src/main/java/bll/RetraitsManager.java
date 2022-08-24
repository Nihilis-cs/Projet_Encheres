package bll;

import bo.Retraits;
import dal.DALException;
import dal.DAOFactory;
import dal.RetraitsDAO;

public class RetraitsManager {
	private static RetraitsManager retmngr;
	private RetraitsDAO retraitsDao;
	
	private RetraitsManager() {
		this.retraitsDao = DAOFactory.getRetraitsDao();
	}
	
	public static RetraitsManager getInstance() {
		if(retmngr==null) {
			retmngr = new RetraitsManager();
		}
		return retmngr;
	}
	
	public Retraits insertRetrait(Retraits retrait) throws BLLException{
		Retraits r = null;
		
		try {
			r = this.retraitsDao.insertRetrait(r);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public Retraits selectByNoArticle(int no) throws BLLException{
		Retraits r = null;
		
		try {
			r = this.retraitsDao.selectByNoArticle(no);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return r;
	}
	
}
