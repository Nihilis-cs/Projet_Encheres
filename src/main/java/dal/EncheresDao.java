package dal;

import java.util.List;

import bo.Encheres;

public interface EncheresDao {
	public Encheres insert();
	public Encheres selectByNoArticle();
}
