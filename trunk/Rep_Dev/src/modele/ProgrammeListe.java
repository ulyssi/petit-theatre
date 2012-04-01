package modele; 
import java.util.ArrayList;

public class ProgrammeListe{
    public Spectacle spectacle;
    public ArrayList <Representation> representations;
    public ProgrammeListe(Spectacle s){
	spectacle=s;
	representations = new ArrayList();
    }
}
