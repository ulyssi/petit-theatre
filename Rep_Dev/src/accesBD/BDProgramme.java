package accesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.Date;


import exceptions.CategorieException;
import exceptions.ExceptionConnexion;

import modele.Spectacle;
import modele.Utilisateur;
import modele.Place;

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

    public static void addRepresentation(Utilisateur user,int numS,String dateS)throws CategorieException, ExceptionConnexion {
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	//	insert into LESREPRESENTATIONS values ('101', TO_DATE('06/11/2009 20h','DD/MM/YYYY HH24"h"'));	
	requete = "insert into LESREPRESENTATIONS values ('"+numS+"', TO_DATE('"+dateS+"','DD/MM/YYYY HH24\"h\"'))";
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

    public static Vector<Date> getRepresentation(Utilisateur user,int numS)throws CategorieException, ExceptionConnexion {
	Vector<Date> res= new Vector<Date>();
	String requete ;
	Statement stmt ;
	ResultSet rs ;
	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	//	insert into LESREPRESENTATIONS values ('101', TO_DATE('06/11/2009 20h','DD/MM/YYYY HH24"h"'));	
	requete = "Select dateRep from LESREPRESENTATIONS R where R.numS="+numS;
	try {
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(requete);
	    while (rs.next()) {
		res.addElement(rs.getDate(1)); 
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
		res.addElement(new Place(rs.getInt(1),rs.getInt(2))); 
	    }
	} catch (SQLException e) {
	    throw new CategorieException (" Problème dans l'interrogation des Places disponibles..<br>"+requete+
					   "<br> Code Oracle " + e.getErrorCode()
					  + "Message " + e.getMessage());
	}
	BDConnexion.FermerTout(conn, stmt, rs);	
	return res;
    }


    // public static void addCategorie(Utilisateur user, String nom , int prix )
    // 	throws CategorieException, ExceptionConnexion {
    // 	String requete ;
    // 	Statement stmt ;
    // 	ResultSet rs ;
    // 	Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
    // 	requete = "insert into LesCategories values(\'" +nom+ "\'," +prix+")";
		
		
	
    // 	try {
    // 	    stmt = conn.createStatement();
    // 	    rs = stmt.executeQuery(requete);
    // 	} catch (SQLException e) {
    // 	    throw new CategorieException (" Probleme dans l'ajout de la catégorie "
    // 					  + "Code Oracle " + e.getErrorCode()	 
    // 					  + "Message " + e.getMessage());
    // 	}
    // 	BDConnexion.FermerTout(conn, stmt, rs);
	
    // }


}
