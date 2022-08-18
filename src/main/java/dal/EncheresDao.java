package dal;

import java.util.List;

import bo.Encheres;

public interface EncheresDao {
	public Encheres insert(Encheres e);
	public List<Encheres> selectAll();
	public Encheres selectByNoArticle();
}
