package modele;
import java.util.Calendar;
import java.util.Date;
import java.sql.Time;
/*
 * Cette classe permet de representer une represenation elle est Composée d'une date d'un numero et un calendrier
 */
public class Representation {

    private Date date;
    private int num ;
    public Calendar calendar;
    /*
     * @param n le numero de la representation 
     * @param d la date de la representation 
     */
    public Representation (int n, Date d) {
		this.date = d;
		calendar= Calendar.getInstance();
		calendar.setTime(d);
		num=n;
    }
    /*
     * @param n le numero de la representation 
     * @param d la date de la representation 
     * @paral t le format de la date
     */
    public Representation (int n, Date d,Time t) {
		this.date = d;
		calendar= Calendar.getInstance();
		Calendar tmp=Calendar.getInstance();
		calendar.setTime(d);
		tmp.setTime(t);
		calendar.set(Calendar.HOUR_OF_DAY,tmp.get(Calendar.HOUR_OF_DAY));
		num=n;
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

    public String toString(){
	String rslt="";
	rslt=calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR)+"  "+calendar.get(Calendar.HOUR_OF_DAY)+"h"; 
	return rslt;
    }
    
    
}
