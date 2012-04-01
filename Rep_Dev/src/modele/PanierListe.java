package modele;
import java.util.ArrayList;
import modele.*;
public class PanierListe {
    public ArrayList<Item> Liste;
    
    public PanierListe(){
	Liste=new <Item>ArrayList();
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