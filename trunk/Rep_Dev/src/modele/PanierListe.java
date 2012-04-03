package modele;
import java.util.ArrayList;
import modele.*;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

 /*
 * Cette servlet permet de virtualiser un panier 
 */
public class PanierListe {
    public ArrayList<Item> Liste;
    
    public PanierListe(){
	Liste=new ArrayList<Item>();
    }
    public void addPlace(String num,String date, String place , String rang){
	try {SimpleDateFormat s= new SimpleDateFormat("dd/MM/yyyy HH");
	Date d=s.parse(date);
		Calendar calendar;
	calendar= Calendar.getInstance();
	calendar.setTime(d);
	//&& item.representation.calendar.compareto(calendar)) 
	for(Item item:Liste)
	    if (item.spectacle.getNum()==new Integer(num))
		item.lesPlaces.add(new Place(new Integer(rang),new Integer(place)));
   
	}
	catch (Exception e){}
    }
    
    public boolean contientPlace(){
	for(Item item:Liste)
	    if (item.lesPlaces.size()>0)
		return true;
	return false;
    }
    public int getSize(){
	return Liste.size();
    }
    public String toString(){
	String res="";
	for(Item item : Liste)
	    res+=item.toString();
	return res;
    }
    
}