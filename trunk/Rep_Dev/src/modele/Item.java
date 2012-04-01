package modele;
import java.util.ArrayList;

public class Item{
    public Spectacle spectacle;
    public Representation reprensentation;
    public ArrayList<Place> lesPlaces;
    
    
    public Item(Spectacle s , Representation r ){
	spectacle=s; 
	reprensentation=r;
	lesPlaces= new <Place>ArrayList();
    }
    public String toString(){
	String res=""+spectacle.getNom()+" "+ spectacle.getNum()+"\n";
	for (Place place : lesPlaces)
	    res+=" Rang :"+place.getNoRang()+"Place :"+place.getNoPlace();
	return res;
	
	
    }
    
}