package modele;


 /*
  * Cette classe permet de virtualiser un place elle contient un numero de rang et un numero de place 
 */
public class Place {
    
	private int noRang;
	private int noPlace;
	
	public Place (int c, int p) {
		this.noRang = c;
		this.noPlace = p;
	}

	public int getNoRang () {
		return this.noRang;
	}
	
	public int getNoPlace () {
		return this.noPlace;
	}
	
	public void setNoRang (int c) {
		this.noRang = c;
	}
	
	public void setNoPlace (int p) {
		this.noPlace = p;
	}

    public String toString(){
	String rslt;
	rslt="rang: "+ this.noRang+"  place: "+this.noPlace;
	return rslt;
    }
}
