package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import bo.Articles;
import bo.Encheres;
import bo.EtatsVente;
import bo.Utilisateurs;

public class ArticlesDaoJdbcImpl implements ArticlesDao {
	
	private final String INSERT_Article= "insert into ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	private final String SELECT_EC= " SELECT  a.no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,\r\n"
			+ "			a.no_utilisateur as no_vendeur,a.no_categorie,etat_vente,image,r.rue as arue,r.code_postal as acp,r.ville as aville, c.libelle, u.*,\r\n"
			+ "			e.no_utilisateur as no_encherisseur, e.date_enchere, e.montant_enchere \r\n"
			+ "			FROM  (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) \r\n"
			+ "			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)\r\n"
			+ "			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))\r\n"
			+ "			WHERE (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)";
	
	
	public Articles insert(Articles a) throws DALException {
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT_Article, Statement.RETURN_GENERATED_KEYS)){
			try {
				con.setAutoCommit(false);
				
				stmt.setString(1, a.getNomArticle());
				stmt.setString(2, a.getDescription());
				stmt.setTimestamp(3, java.sql.Timestamp.valueOf(a.getDateDebutEnchere()));
				stmt.setTimestamp(4, java.sql.Timestamp.valueOf(a.getDateFinEnchere()));
				stmt.setInt(5, a.getPrixInitial());
				stmt.setInt(6, a.getPrixVente());
				stmt.setInt(7, a.getVendeur().getId());
				stmt.setInt(8, a.getCategorie().getNoCategorie());
				stmt.setString(9, a.getEtatVente().toString());
		
			}catch(SQLException e){
				e.printStackTrace();
				con.rollback();	
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert de l'article : " + e.getMessage());
		}
		
		return a;
	}

	@Override
	public List<Articles> selectAll() throws DALException{
		// TODO Auto-generated method stub
		return null;
	}
	//CallableStatement cstmt = con.prepareCall("updateArticle");

	@Override
	public List<Articles> selectAllEC() throws DALException {
		List<Articles> listeArticlesEC = null; 
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_EC, Statement.RETURN_GENERATED_KEYS)){
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Articles article = articleBuilder(rs);
				listeArticlesEC.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème lors de l'accès à la BDD");
		}
		
		
		
		
		return listeArticlesEC;
	}

	private Articles articleBuilder(ResultSet rs) throws DALException {
		Articles art = new Articles();
		UtilisateursDao uDao = new UtilisateursDAOJdbcImp();
		try {
			art.setNoArticle(rs.getInt("no_article"));
			art.setNomArticle(rs.getString("nom_article"));
			art.setDescription(rs.getString("description"));
			art.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime())); 
			art.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime()));
			art.setPrixInitial(rs.getInt("prix_initial"));
			art.setPrixVente(rs.getInt("prix_vente"));
			Utilisateurs u = new Utilisateurs();
				u.setId(rs.getInt("no_vendeur"));
				u.setPseudo(rs.getString("pseudo"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setTelephone(rs.getString("telephone"));
				u.setRue(rs.getString("rue"));
				u.setCodePostal(rs.getString("code_postal"));
				u.setVille(rs.getString("ville"));
			art.setVendeur(u);
			art.setCategorie(null); //A modifier quand les catégories seront gérées
			switch(rs.getString("etat_vente")) {
				case "CR" : art.setEtatVente(EtatsVente.CR);
					break;
				case "EC" : art.setEtatVente(EtatsVente.EC);
					break;
				case "VD" : art.setEtatVente(EtatsVente.VD);
					break;
				case "RT" : art.setEtatVente(EtatsVente.RT);
					break;
				default : art.setEtatVente(null); 
					break;
			}
			Encheres e = new Encheres();
				e.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()));
				Utilisateurs encherisseur = uDao.selectByID(rs.getInt("no_encherisseur"));	
				e.setEncherisseur(encherisseur);
				e.setMontantEnchere(rs.getInt("montant_enchere"));
				e.setNoArticle(rs.getInt("no_article"));
			art.setEnchere(e);
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert : " + e.getMessage());
		}
		return art;
	}
}
