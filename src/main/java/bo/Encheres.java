package bo;

import java.time.LocalDateTime;

public class Encheres {
	private Utilisateurs encherisseur;
	private int noArticle;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	
	public Encheres(Utilisateurs encherisseur, int noArticle, LocalDateTime dateEnchere, int montantEnchere) {
		this.encherisseur = encherisseur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	public Encheres(int montantEnchere, int noArticle) {
		this.noArticle = noArticle;
		this.montantEnchere = montantEnchere;
	}
	
	public Encheres() {

	}
	
	public Utilisateurs getEncherisseur() {
		return encherisseur;
	}
	public void setEncherisseur(Utilisateurs encherisseur) {
		this.encherisseur = encherisseur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	@Override
	public String toString() {
		return "Encheres [encherisseur=" + encherisseur + ", noArticle=" + noArticle + ", dateEnchere=" + dateEnchere
				+ ", montantEnchere=" + montantEnchere + "]";
	}
	
	
}
