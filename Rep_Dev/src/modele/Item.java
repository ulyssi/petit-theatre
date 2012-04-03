package modele;
import java.util.ArrayList;
//import modele.*;

public class Item{
    public Spectacle spectacle;
    public Representation reprensentation;
    public ArrayList<Place> lesPlaces;
    
    
    public Item(Spectacle s , Representation r ){
	spectacle=s; 
	reprensentation=r;
	lesPlaces= new  ArrayList<Place>();
    }
    public String toString(){
	String res=""+spectacle.getNom()+" "+ spectacle.getNum()+"   "+reprensentation+"<br>";
	if (lesPlaces.size()<=0)
	    res+="Pas de places selectionnees";
	else
	    for (Place place : lesPlaces)
		res+=" Rang :"+place.getNoRang()+"Place :"+place.getNoPlace();
	return res;
	
	
    }
    
}