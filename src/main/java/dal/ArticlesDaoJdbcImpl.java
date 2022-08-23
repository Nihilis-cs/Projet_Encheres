package dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.Articles;
import bo.Encheres;
import bo.EtatsVente;
import bo.Utilisateurs;

public class ArticlesDaoJdbcImpl implements ArticlesDao {
	private final String INSERT_Article= "insert into ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)"
			+ " VALUES (?,?,?,?,?,?,?,?,?)" ;
	private final String SELECT_EC= " SELECT  a.no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,\r\n"
			+ "			a.no_utilisateur as no_vendeur,a.no_categorie,etat_vente,image,r.rue as arue,r.code_postal as acp,r.ville as aville, c.libelle, u.*,\r\n"
			+ "			e.no_utilisateur as no_encherisseur, e.date_enchere, e.montant_enchere \r\n"
			+ "			FROM  (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) \r\n"
			+ "			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)\r\n"
			+ "			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))\r\n"
			+ "			WHERE (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)";

	private final String SELECT_FILTER= " SELECT  a.no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,\r\n"
			+ "			a.no_utilisateur as no_vendeur,a.no_categorie,etat_vente,image,r.rue as arue,r.code_postal as acp,r.ville as aville, c.libelle, u.*,\r\n"
			+ "			e.no_utilisateur as no_encherisseur, e.date_enchere, e.montant_enchere \r\n"
			+ "			FROM  (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) \r\n"
			+ "			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)\r\n"
			+ "			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))";

	private final String SELECT_BY_ID = "Select *, a.no_article as ref_article, a.no_utilisateur as no_vendeur from Articles_vendus a\r\n"			
			+ "inner Join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur\r\n"
			+ "inner join ENCHERES e on e.no_article = a.no_article \r\n"
			+ "Where a.no_article = ?";

	private final String enchereEC = " (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)";
	//private final String enchereUser = " etat_vente = 'EC'  AND e.no_utilisateur = ?" ; //? VA ÊTRE REMPLACE PAR LID DE LUSER CONNECTE 
	//	private final String enchereWin = " etat_vente = 'VD' AND e.no_utilisateur = ?"; //? VA ÊTRE REMPLACE PAR LID DE LUSER CONNECTE 
	//	
	//	private final String venteUserEC = " a.no_utilisateur = ? AND  (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)"; //--NO_UTILISATEUR DYNAMIQUE CEST LE ? de L'ID USER ACTUELLEMENT CO
	//	private final String venteUserCR = " a.no_utilisateur = ? AND etat_vente = 'CR'";
	//	private final String venteUserVD = " a.no_utilisateur = ? AND etat_vente = 'VD'";
	//private final String UPDATE_ETAT_VENTE

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

				stmt.executeUpdate();
				con.commit();

			}catch(SQLException e){
				e.printStackTrace();
				con.rollback();	
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert de l'article : " + e.getMessage());
		}

		return a;
	}

	@Override
	public List<Articles> selectAll() throws DALException{
		List<Articles> liste = new ArrayList<Articles>();
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_EC, PreparedStatement.RETURN_GENERATED_KEYS)){

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Articles article = articleBuilder(rs);
				liste.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème lors de l'accès à la BDD");
		}

		return liste;
	}
	//CallableStatement cstmt = con.prepareCall("updateArticle");

	@Override
	public List<Articles> selectAllFilter(String filter, int idUser) throws DALException {
		List<Articles> listeArticlesFilter = new ArrayList<Articles>(); 
		int cpt = 0;
<<<<<<< HEAD
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_FILTER)){
			
			String enchereUser = " etat_vente = 'EC'  AND e.no_utilisateur = " + idUser;
			String enchereWin = " etat_vente = 'VD' AND e.no_utilisateur = "+idUser; 
			String venteUserEC = " a.no_utilisateur = "+ idUser+" AND  (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)"; 
			String venteUserCR = " a.no_utilisateur = "+ idUser+" AND etat_vente = 'CR'";
			String venteUserVD = " a.no_utilisateur = "+ idUser+" AND etat_vente = 'VD'";
			
			
			String requete = " ";
			
			if (filter.contains("achats")) {
				if (filter.contains("ouvertes")) {
					requete += " WHERE" +  enchereEC;
					cpt ++;
				} 
				if (filter.contains("encours")) {
					if (cpt != 0) {
						requete += " AND " + enchereUser;
					} else {
						requete += " WHERE" + enchereUser;
					}			
					cpt ++;
				}
				if (filter.contains("remportees")) {
					if (cpt != 0) {
						requete += " AND " + enchereWin;
					} else {
						requete += " WHERE" +  enchereWin;
					}	
				}
			} else if (filter.contains("ventes")) {
				if (filter.contains("venteencours")) {	
					requete += " WHERE" +  venteUserEC;
					cpt ++;
				} 
				if (filter.contains("nondebutees")) {
					if (cpt != 0) {
						requete += " AND " + venteUserCR;
					} else {
						requete += " WHERE" +  venteUserCR;
					}
					cpt ++;
				}
				if (filter.contains("terminees")) {
					if (cpt != 0) {
						requete += " AND " + venteUserVD;
					} else {
						requete += " WHERE" +  venteUserVD;
					}
				}
				//System.out.println(requete);
=======
		String enchereUser = " etat_vente = 'EC'  AND e.no_utilisateur = " + idUser;
		String enchereWin = " etat_vente = 'VD' AND e.no_utilisateur = "+idUser; //? VA ÊTRE REMPLACE PAR LID DE LUSER CONNECTE 
		String venteUserEC = " a.no_utilisateur = "+ idUser+" AND  (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)"; //--NO_UTILISATEUR DYNAMIQUE CEST LE ? de L'ID USER ACTUELLEMENT CO
		String venteUserCR = " a.no_utilisateur = "+ idUser+" AND etat_vente = 'CR'";
		String venteUserVD = " a.no_utilisateur = "+ idUser+" AND etat_vente = 'VD'";


		String requete = "";

		if (filter.contains("achats")) {
			if (filter.contains("ouvertes")) {
				requete += " WHERE" +  enchereEC;
				cpt ++;
			} 
			if (filter.contains("encours")) {
				if (cpt != 0) {
					requete += " AND " + enchereUser;
				} else {
					requete += " WHERE" + enchereUser;
				}			
				cpt ++;
>>>>>>> branch 'main' of https://github.com/Nihilix420/ENI_ENCHERES.git
			}
			if (filter.contains("remportees")) {
				if (cpt != 0) {
					requete += " AND " + enchereWin;
				} else {
					requete += " WHERE" +  enchereWin;
				}	
			}
		} else if (filter.contains("ventes")) {
			if (filter.contains("venteencours")) {	
				requete += " WHERE" +  venteUserEC;
				cpt ++;
			} 
			if (filter.contains("nondebutees")) {
				if (cpt != 0) {
					requete += " AND " + venteUserCR;
				} else {
					requete += " WHERE" +  venteUserCR;
				}
				cpt ++;
			}
			if (filter.contains("terminees")) {
				if (cpt != 0) {
					requete += " AND " + venteUserVD;
				} else {
					requete += " WHERE" +  venteUserVD;
				}
			}
			System.out.println(requete);
		}		try(Connection con = JdbcTools.getConnection();
				){
			String requeteCompl=SELECT_FILTER + requete;
			PreparedStatement stmt = con.prepareStatement(requeteCompl);

			

			System.out.println(requeteCompl);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Articles article = articleBuilder(rs);
				listeArticlesFilter.add(article);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème lors de l'accès à la BDD");
		}

		return listeArticlesFilter;
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
			if (rs.getDate("date_enchere") != null) {
				e.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()));

				Utilisateurs encherisseur = uDao.selectByID(rs.getInt("no_encherisseur"));	
				e.setEncherisseur(encherisseur);
				e.setMontantEnchere(rs.getInt("montant_enchere"));
				e.setNoArticle(rs.getInt("no_article"));
			}
			art.setEnchere(e);


		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insert : " + e.getMessage());
		}
		return art;
	}

	public void updateEtatVente() throws DALException {
		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareCall("{call updateArticle()}")){
			stmt.execute();
		}catch(SQLException e) {
			throw new DALException("la procédure stockée a merdé");
		}
	}

	public Articles SelectById(int id) throws DALException{
		Articles article = new Articles();

		try(Connection con = JdbcTools.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID)){
			UtilisateursDao uDao = new UtilisateursDAOJdbcImp();
			stmt.setInt(1, id);			
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				article.setNoArticle(id);
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()));
				article.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime()));
				article.setPrixInitial(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
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
				u.setCredit(rs.getInt("credit"));
				u.setAdmin(rs.getByte("administrateur"));
				article.setVendeur(u);
				article.setCategorie(null); //A modifier quand les catégories seront gérées
				switch(rs.getString("etat_vente")) {
				case "CR" : article.setEtatVente(EtatsVente.CR);
				break;
				case "EC" : article.setEtatVente(EtatsVente.EC);
				break;
				case "VD" : article.setEtatVente(EtatsVente.VD);
				break;
				case "RT" : article.setEtatVente(EtatsVente.RT);
				break;
				default : article.setEtatVente(null); 
				break;
				}

				Encheres e = new Encheres();
				if (rs.getDate("date_enchere") != null) {
					e.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()));

					Utilisateurs encherisseur = uDao.selectByID(rs.getInt("no_vendeur"));	
					e.setEncherisseur(encherisseur);
					e.setMontantEnchere(rs.getInt("montant_enchere"));
					e.setNoArticle(rs.getInt("ref_article"));				
					article.setEnchere(e);

				}
				else {
					throw new DALException("Id introuvable ");
				}
			}
		} catch (SQLException e) {
			throw new DALException("Erreur dans la selection par l'Id : " + e.getMessage());
		}
		return article;

	}
}