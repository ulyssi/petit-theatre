package modele;
/*
 * Cette classe permet de representer  un spectacle au format interne
 */

public class Spectacle {

    private String categorie;
    private int prix;
    /*
     * @param c le nom du spectacle 
     * @param p le prix du spectacle
     */

    
    public Spectacle (String c, int p) {
	this.categorie = c;
	this.prix = p;
    }

    public String getNom () {
	return this.categorie;
    }
	
    public int getNum () {
	return this.prix;
    }
	
    public void setNom (String c) {
	this.categorie = c;
    }
	
    public void setNum (int p) {
	this.prix = p;
    }
}
