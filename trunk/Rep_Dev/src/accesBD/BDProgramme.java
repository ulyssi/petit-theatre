package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.Date;


import exceptions.CategorieException;
import exceptions.ExceptionConnexion;

import modele.Representation;
import modele.Spectacle;
import modele.Utilisateur;
import modele.Place;
import modele.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class BDProgramme {
    public BDProgramme () {
    }
    /**
     * retourne la liste des spectacles définies dans la bd
     * @param Utilisateur
     * @return Vector<Categorie>
     * @throws CategorieException
     * @throws ExceptionConnexion
     */
    public static Vector<Spectacle> getSpectacle (Utilisateur user)
	throws CategorieException, ExceptionConnexion {
	Vector<Spectacle> res = new Vector<Spectacle>();
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
	requete = "select numS, nomS from LesSpectacles";
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    while (rs.next()) {
		res.addElement(new Spectacle (rs.getString(2), rs.getInt(1)));
	    }
	} catch (SQLException e) {
	    throw new CategorieException (" Problème dans l'interrogation des spectacles.."
					  + "Code Oracle " + e.getErrorCode()
					  + "Message " + e.getMessage());
	}
	BDConnexion.FermerTout(conn, stmt, rs);
	return res;
    }
 

    public static void addRepresentation(Utilisateur user,Representation R)throws CategorieException, ExceptionConnexion {
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	//	insert into LESREPRESENTATIONS values ('101', TO_DATE('06/11/2009 20h','DD/MM/YYYY HH24"h"'));	
	requete = "insert into LESREPRESENTATIONS values ('"+R.getNum()+"', TO_DATE('"+R+"','DD/MM/YYYY HH24\"h\"'))";
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    
	} catch (SQLException e) {
	    throw new CategorieException (" Problème dans l'interrogation des spectacles.."
					  + "Code Oracle " + e.getErrorCode()
					  + "Message " + e.getMessage());
	}
	BDConnexion.FermerTout(conn, stmt, rs);	
	
    }

    public static Vector<Representation>  getRepresentation(Utilisateur user,int numS)throws CategorieException, ExceptionConnexion {
	Vector<Representation> res= new Vector<Representation>();
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

	requete = "Select dateRep from LESREPRESENTATIONS R where R.numS="+numS;
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	 
	    while (rs.next()) {
		res.addElement(new Representation(numS, rs.getDate(1),rs.getTime(1)));
	
	    }

	 
	} catch (SQLException e) {
	    throw new CategorieException (" Problème dans l'interrogation des representation.."
					  + "Code Oracle " + e.getErrorCode()
					  + "Message " + e.getMessage());
	}
	BDConnexion.FermerTout(conn, stmt, rs);	
	return res;
    }

    public static Vector<Place> getPlacesDispo(Utilisateur user,int numS,String date)throws CategorieException, ExceptionConnexion {
	Vector<Place> res= new Vector<Place>();
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	//	insert into LESREPRESENTATIONS values ('101', TO_DATE('06/11/2009 20h','DD/MM/YYYY HH24"h"'));	
	requete = "Select P.noPlace, P.noRang from LESPLACES P, LESREPRESENTATIONS R where ";
	requete=requete+"R.DateRep=TO_DATE('"+date+"','DD/MM/YYYY HH24\"h\"') and ";
	requete=requete+"not exists(Select * from LESTICKETS T where T.DateRep=TO_DATE('"+date+"','DD/MM/YYYY HH24\"h\"')";
	requete=requete+ " and T.noPlace = P.noPlace and T.noRang=P.noRang)";
	System.out.println(requete);
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    while (rs.next()) {
		res.addElement(new Place(rs.getInt(2),rs.getInt(1))); 
	    }
	} catch (SQLException e) {
	    throw new CategorieException (" Problème dans l'interrogation des Places disponibles..<br>"+requete+
					  "<br> Code Oracle " + e.getErrorCode()
					  + "Message " + e.getMessage());
	}
	BDConnexion.FermerTout(conn, stmt, rs);	
	return res;
    }

    public static String valide(Utilisateur user,PanierListe  p ){
	String res = "";
 	String requete="" ;
	Statement stmt ;
	ResultSet rs ;
	try{
	    Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	    //verification de donnees
	    requete+= "Select T.noPlace, T.noRang,T.numS,T.dateRep from LESTICKETS T where ";
	    boolean first=true;
	    for(int i= 0; i<p.Liste.size();i++)
	    	for(int j=0; j<p.Liste.get(i).lesPlaces.size();j++){
	    	    if (i==p.Liste.size()-1&&j==p.Liste.get(i).lesPlaces.size()-1)
	    		requete+="T.noPlace="+p.Liste.get(i).lesPlaces.get(j).getNoPlace()+" and "+p.Liste.get(i).lesPlaces.get(j).getNoRang()+"=T.noRang  and T.DateRep=TO_DATE('"+p.Liste.get(i).representation+"','DD/MM/YYYY HH24\"h\"')"+" and T.numS="+p.Liste.get(i).representation.getNum();
	    	    else
	    		requete+="T.noPlace="+p.Liste.get(i).lesPlaces.get(j).getNoPlace()+" and "+p.Liste.get(i).lesPlaces.get(j).getNoRang()+"=T.noRang  and T.DateRep=TO_DATE('"+p.Liste.get(i).representation+"','DD/MM/YYYY HH24\"h\"')"+" and T.numS="+p.Liste.get(i).representation.getNum()+"OR \n";
	    	}
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    boolean err=false; 
	    while(rs.next()) {
	    	res+="la place num "+rs.getInt(1)+" de rang "+rs.getInt(2)+" de spectale "+rs.getInt(3)+" de date "+rs.getDate(1)+" est non disponible";
	    	err=true;
	    }
	    if (err)
	    	return res;
	    else {
		

		
		// on fait la reservation 
		requete=""; 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//recuperer le dernier numero de serie
		requete ="Select noserie from LESTICKETS order by noSerie DESC" ;
		rs = stmt.executeQuery(requete);
		rs.next();
		int lastnoSerie=(rs.getInt(1))+1;
	
		for(int i= 0; i<p.Liste.size();i++)
		    for(int j=0; j<p.Liste.get(i).lesPlaces.size();j++){
			requete="insert into LESTICKETS values (";
			requete+="\'"+lastnoSerie+"\',\'"+p.Liste.get(i).representation.getNum()+"\', TO_DATE(\'"+p.Liste.get(i).representation;
			requete+="\',\'DD/MM/YYYY HH24\"h\"\'),"+"\'"+p.Liste.get(i).lesPlaces.get(j).getNoPlace();
			requete+="\',\'"+p.Liste.get(i).lesPlaces.get(j).getNoRang()+"\',TO_DATE(\'"+dateFormat.format(date)+ "\',\'DD/MM/YYYY HH24\"h\"\'),\'104\')";
			lastnoSerie++;
			rs = stmt.executeQuery(requete);
			rs.close();
		    }
		
	    }
	    
	    BDConnexion.FermerTout(conn, stmt, rs);
	}
	
	catch (SQLException e){
	    res="<br> Code Oracle " + e.getErrorCode();
	    res+= "erreur"+e.getMessage();
	    res+=e.getSQLState();
	}
	catch(Exception e ){
	    return e.getMessage();
	}

	
	return res;
    }


    public static Place  getPlaceZone(Utilisateur user,Representation R,int zone){
	
	String requete ;
	Statement stmt=null ;
	ResultSet rs=null ;
	Connection conn=null;
	Place rslt=null;
	
	
	
	
	requete = "Select P.noPlace, P.noRang from LESPLACES P, LESREPRESENTATIONS R where ";
	requete=requete+"R.DateRep=TO_DATE('"+R+"','DD/MM/YYYY HH24\"h\"') and ";
	requete=requete+"P.numZ="+zone +"not exists(Select * from LESTICKETS T where T.DateRep=TO_DATE('"+R+"','DD/MM/YYYY HH24\"h\"')";
	requete=requete+ " and T.noPlace = P.noPlace and T.noRang=P.noRang)";
	try{
	    conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    if(rs.next())
		rslt=new Place(rs.getInt(2),rs.getInt(1));
	}
	catch(Exception e){
	    rslt= null;
	}
	BDConnexion.FermerTout(conn, stmt, rs);
	return rslt;
    }
    
    
    public static Vector<ProgrammeListe> getProgramListes(Utilisateur user)
	throws CategorieException, ExceptionConnexion {
	Vector<ProgrammeListe> res = new Vector<ProgrammeListe>();
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	requete = "select S.numS ,S.nomS ,dateRep FROM LesSpectacles S, LesRepresentations R where S.numS=R.numS ORDER BY nomS";
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    Spectacle sCourant = null;
	    ProgrammeListe pc = null;
	    while (rs.next()) {
		int num = rs.getInt(1);
		sCourant = new Spectacle(rs.getString(2),num);
		Representation r = new Representation(num, rs.getDate(3),rs.getTime(3));
		if ( pc==null){
		    pc = new ProgrammeListe(sCourant);
		    pc.representations.add(r);
		    res.addElement(pc);
		}
		else 
		    if (pc.spectacle.getNom().equals(sCourant.getNom()))
			pc.representations.add(r);
		    else{
			pc = new ProgrammeListe(sCourant);
			pc.representations.add(r);
			res.addElement(pc);
		    }
		
	    }
	} catch (SQLException e) {
	    throw new CategorieException (" Problème dans l'interrogation des spectacles.."
					  + "Code Oracle " + e.getErrorCode()
					  + "Message " + e.getMessage());
	}
	BDConnexion.FermerTout(conn, stmt, rs);
	return res;
    }

}
