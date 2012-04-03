package modele;
import java.util.ArrayList;



/**
 *  Cette classe Permet de representer un Item du panier
 * @author Penkler Cadour
 * @version 1.0
 */

public class Item{

    public Spectacle spectacle;
    public Representation representation;
    public ArrayList<Place> lesPlaces;
    
    /** Constructeur d'item un item est compose d'un ensemble de place d'un spectacle et une 
     * representation il permet de creer une reservation dans le panier pour une ou plusieur places
     * @param Spectacle s  le spectacle
     * @param Representation  la representation
     */
    
    public Item(Spectacle s , Representation r ){
	spectacle=s; 
	representation=r;
	lesPlaces= new  ArrayList<Place>();
    }
    
    /** Permet l'affichage d'un ensemble de place de la representation et du spectacle de l'item
     */
    
    public String toString(){
	String res=""+spectacle.getNom()+" "+ spectacle.getNum()+"   "+representation+"<br>";
	if (lesPlaces.size()<=0)
	    res+="Pas de places selectionnees";
	else
	    for (Place place : lesPlaces)
		res+=" Rang :"+place.getNoRang()+"Place :"+place.getNoPlace();
	return res;
	
	
    }
    /**
     * Permet de verifier qu'une place n'est pas deja presente dans le panier 
     */
    public boolean In(int numplace, int rang){
	for (Place place : lesPlaces)
	    if (place.getNoPlace()==numplace && rang==place.getNoRang())
		return true;
	return false;
		    
    }
    

}