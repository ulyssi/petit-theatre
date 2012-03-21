/*
 * File : GestionRequete.java
 */
 
package accesbd;

import java.sql.*;
import java.util.ArrayList;
import modele.*;

/**
  * Classe pour la gestion des requetes. 
  * Cette classe contient toutes les methodes associees aux differentes requetes 
  * necessaires a l'application.
  */
public class GestionRequete {

  /**
    * Requete permettant de recuperer un spectacle depuis la BD.
    * @param numS_  l'identificateur unique d'un spectacle
    * @return  un objet de type spectacle spectacle, representant les donnees 
    *          associees a l'identificateur dans la BD
    * @throws SQLException  si aucun spectacle avec l'ID fourni n'existe, 
    *                       ou s'il y a un probleme d'acces a la BD
    */
  public static Spectacle trouveSpectacle(int numS_) throws SQLException {
    try {
		Connection conn = GestionAcces.getConnexion();
		Statement stmt = conn.createStatement();
		String req = "select nomS from LesSpectacles where numS = " + numS_;
		ResultSet rset = stmt.executeQuery(req);
		if(rset.next()) {
		  Spectacle result = new Spectacle(numS_, rset.getString("nomS"));
		  stmt.close();
		  rset.close();
		  // le commit ne sert ici qu'a liberer les ressources (verrous..)
		  // reservees par la requete
		  GestionAcces.commit();
		  return result;
		}
		else {
		  stmt.close();
		  rset.close();
		  GestionAcces.rollback();
		  throw new SQLException("Aucun spectacle avec numS = " + numS_);
		}
	} catch (SQLException e) {
		GestionAcces.rollback();
		throw e;
	}
    
  }
  
  /**
    * Requete permettant de recuperer toutes les spectacles existants dans la BD.
    * @return  une collection de spectacles
    * @throws SQLException  s'il y a un probleme d'acces a la BD
    */
  public static ArrayList<Spectacle> trouveSpectacles() throws SQLException {
    try {
		ArrayList<Spectacle> result = new ArrayList<Spectacle>();
		Connection conn = GestionAcces.getConnexion();
		Statement stmt = conn.createStatement();
		String req = "select numS, nomS from LesSpectacles";
		ResultSet rset = stmt.executeQuery(req);
		while(rset.next()) {
		  Spectacle s = new Spectacle(rset.getInt("numS"), rset.getString("nomS"));
		  result.add(s);
		}
		stmt.close();
		rset.close();
		GestionAcces.commit();
		return result;
	} catch (SQLException e) {
		GestionAcces.rollback();
		throw e;
	}
  }
  
  /**
    * Requete permettant d'ajouter une nouvelle representation dans la BD, 
    * associee a un spectacle existant.
    * @param r_  l'objet de type Representation a ajouter dans la BD
    * @return  le nombre de lignes ajoutees dans la BD
    * @throws SQLException  si la representation n'a pas de spectacle associe, 
    *                       ou s'il y a un probleme d'acces a la BD
    */
  public static int ajouteRepresentation(Representation r_) throws SQLException {
    try {
		if(r_.getSpectacle() == null) {
		  throw new SQLException("Pas de spectacle associe a la representation" +
		      " donnee " + r_);
		}
		Connection conn = GestionAcces.getConnexion();
		Statement stmt = conn.createStatement();
		String req = "insert into LesRepresentations values (" + 
		    r_.getSpectacle().getNumS() + "," +
		    "TO_DATE('" + r_.getDateRepText() + "','DD-MM-YYYY HH24'))";
		int ret = stmt.executeUpdate(req);
		stmt.close();
		GestionAcces.commit();
		return ret;
	} catch (SQLException e) {
		GestionAcces.rollback();
		throw e;
	}
  }
  
  /**
    * Requete permettant d'ajouter une nouvelle representation dans la BD, 
    * en connaissant l'identifiant du spectacle associe.
    * @param numS_  l'identifiant du spectacle associe a la representation
    * @param r_  l'objet de type Representation a ajouter dans la BD
    * @return  le nombre de lignes ajoutees dans la BD
    * @throws SQLException  s'il y a un probleme d'acces a la BD
    */
  public static int ajouteRepresentation(int numS_, Representation r_) 
    throws SQLException {
    try {
		Connection conn = GestionAcces.getConnexion();
		Statement stmt = conn.createStatement();
		String req = "insert into LesRepresentations values (" + 
		    numS_ + "," +
		    "TO_DATE('" + r_.getDateRepText() + "','DD-MM-YYYY HH24'))";
		int ret = stmt.executeUpdate(req);
		stmt.close();
		GestionAcces.commit();
		return ret;
	} catch (SQLException e) {
		GestionAcces.rollback();
		throw e;
	}
  }
  
}
