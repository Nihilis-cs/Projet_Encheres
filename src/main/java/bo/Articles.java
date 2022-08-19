package bo;

import java.time.LocalDateTime;

public class Articles {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEnchere;
	private LocalDateTime dateFinEnchere;
	private int prixInitial;
	private int prixVente;
	private Utilisateurs vendeur;
	private Categories categorie;
	private EtatsVente etatVente;
	private String image;
	
	public Articles() {
		super();
	}

	public Articles(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere,
			int prixInitial, int prixVente, Utilisateurs vendeur, Categories categorie, EtatsVente etatVente) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.etatVente = etatVente;
	}

	public Articles(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int prixInitial, int prixVente, Utilisateurs vendeur, Categories categorie,
			EtatsVente etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.etatVente = etatVente;
	}

	public Articles(String nomArticle, Categories categorie, String description, 
			LocalDateTime dateDebut, LocalDateTime dateFin, int prixInit) {
		this.nomArticle = nomArticle;
		this.categorie = categorie;
		this.description = description;
		this.dateDebutEnchere = dateDebut;
		this.dateFinEnchere = dateFin;
		this.prixInitial = prixInit;
	}



	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDateTime getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Utilisateurs getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateurs vendeur) {
		this.vendeur = vendeur;
	}

	public Categories getCategorie() {
		return categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

	public EtatsVente getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(EtatsVente etatVente) {
		this.etatVente = etatVente;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}