package modele;
import java.util.ArrayList;

public class PanierListe {
    public ArrayList<Item> Liste;
    
    public PanierListe(){
	Liste=new <Item>ArrayList();
    }
    
    public String toString(){
	String res="";
	for(Item item : Liste)
	    res+=item.toString();
	return res;
    }
    
}