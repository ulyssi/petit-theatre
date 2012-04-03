package modele; 
import java.util.ArrayList;
/*
 *Cette classe permet de lister tout les representations disponible pour un spectacle donne
 */



public class ProgrammeListe{
    public Spectacle spectacle;
    public ArrayList <Representation> representations;
    public ProgrammeListe(Spectacle s){
	spectacle=s;
	representations = new ArrayList();
    }
}
