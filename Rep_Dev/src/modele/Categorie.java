package modele;
/**
 * Classe permettant de representer le modele des categories 
 * @author Cadour ulysse Penkler Alexandre
 * @version 1.0
 */

public class Categorie {

	private String categorie;
	private float prix;
    /**
     * Permet de creer une categorie
     */
    public Categorie (String c, float p) {
		this.categorie = c;
		this.prix = p;
	}

	public String getCategorie () {
		return this.categorie;
	}
	
	public float getPrix () {
		return this.prix;
	}
	
	public void setCategorie (String c) {
		this.categorie = c;
	}
	
	public void setPrix (float p) {
		this.prix = p;
	}
}
