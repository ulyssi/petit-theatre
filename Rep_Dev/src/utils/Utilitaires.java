package utils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import java.util.Date;

import java.awt.Frame;


import accesBD.BDCategories;
import accesBD.BDProgramme;
import accesBD.BDConnexion;

import modele.Utilisateur;
import modele.Categorie;
import modele.Spectacle;
import exceptions.ExceptionUtilisateur;
import exceptions.ExceptionConnexion;
import exceptions.CategorieException;
import java.io.File;



/**
 * les operations de l'application
 * 
 * @author fauvet
 * 
 */
public class Utilitaires {

    public Utilitaires() {
    }
    
    
    public static String AfficherProgramme(Utilisateur user) throws IOException {
	Vector<Spectacle> res = new Vector<Spectacle>();
	String resultat ="";
	    try {
	
		resultat=resultat+"Listes des Spectacles de la Saison<br>";
		res = BDProgramme.getSpectacle(user);
		if (res.isEmpty()) {
		    resultat=resultat+" Liste vide <br>";
		} else {
		    for (int i = 0; i < res.size(); i++) {
			resultat=resultat+" num "
			+ res.elementAt(i).getNum() + " :  "+res.elementAt(i).getNom()+"<br>";
		    }
		}
	
	    } catch (CategorieException e) {
		resultat=resultat+" Erreur dans l'affichage du programme 1: "+ e.getMessage()+"<br>";
	    } catch (ExceptionConnexion e) {
		resultat=resultat+" Erreur dans l'affichage du programme 2: "+ e.getMessage()+"<br>";}
	return resultat;
    }

    public static String AjouterRepresentation(Utilisateur user,int numS, String dateS){
	String resultat ="Ajout des valeurs "+numS+"dateS <br>";
	try {
	    BDProgramme.addRepresentation(user,numS,dateS);
	    resultat=resultat+"Valeurs ins&eacute;r&eacute; avec succ&egrave<br>";
	}
	
	catch (CategorieException e) {
	    resultat= resultat+" Erreur dans l'insertion: "+ e.getMessage()+"<br>";
	} catch (ExceptionConnexion e) {
	    resultat=resultat+" Erreur dans l'insertion: "+ e.getMessage()+"<br>";}
	return resultat;
    }
    

    public static String listerRepresentations(Utilisateur user,int numS){
	Vector<Date> res = new Vector<Date>();
	String resultat ="";
	    try {
		resultat=resultat+"Listes des représentation pour le spectacle "+numS+"<br>";
		res = BDProgramme.getRepresentation(user,numS);
		if (res.isEmpty()) {
		    resultat=resultat+" Aucune repr&eacute;sentation disponible pour votre requete <br>";
		} else {
		    for (int i = 0; i < res.size(); i++) {
			resultat=resultat+"<br>" + res.elementAt(i);
		    }
		}
		
	    } catch (CategorieException e) {
		resultat=resultat+" Erreur dans l'affichage du programme 1: "+ e.getMessage()+"<br>";
	    } catch (ExceptionConnexion e) {
		resultat=resultat+" Erreur dans l'affichage du programme 2: "+ e.getMessage()+"<br>";}
	    return resultat;	
    }

    public static Utilisateur Identification(HttpServlet servletParent) throws ExceptionConnexion,
						      ExceptionUtilisateur, IOException {

	Utilisateur user = null;
	String login;
	String passwd;
	// lecture des parametres de connexion dans connection.conf
	Properties p = new Properties();
	InputStream is = null;
	String relativeWebPath =utils.Constantes.Config;
	String absoluteDiskPath = servletParent.getServletContext().getRealPath(relativeWebPath);
	File file = new File(absoluteDiskPath);
	is = new FileInputStream(file);
	p.load(is);
	login = p.getProperty("user");
	passwd = p.getProperty("mdp");
	/* test de la connexion */
	Connection conn = BDConnexion.getConnexion(login, passwd);
	if (conn != null) {
	    BDConnexion.FermerTout(conn, null, null);
	    user = new Utilisateur(login, passwd);
	} else {
	    throw new ExceptionConnexion("Connexion impossible\n");
	}
	return user;
    }

}
