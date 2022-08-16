package bo;

public class Utilisateurs {	

	int id;
	String pseudo;
	String motDePasse;
	
	public Utilisateurs(int id, String pseudo, String motDePasse) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
	}
	
	public Utilisateurs(String pseudo, String motDePasse)  {
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
	}
	
	public Utilisateurs()  {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	
	
	
	
	
}
