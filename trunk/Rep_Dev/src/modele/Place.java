package modele;

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
}
