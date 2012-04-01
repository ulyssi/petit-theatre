package modele;

import java.util.Date;
public class Representation {

    private Date date;
    private int num ;
	
    public Representation (int n, Date d) {
		this.date = d;
		this.num = n;
	}

	public int  getNum () {
		return this.num;
	}
	
	public Date getDate () {
		return this.date;
	}
	
	public void setNum (int n) {
		this.num= n;
	}
	
	public void setDate (Date d) {
		this.date = d;
	}
}
