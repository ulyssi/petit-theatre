package utils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import java.awt.Frame;


import accesBD.BDCategories;
import accesBD.BDConnexion;

import modele.Utilisateur;
import modele.Categorie;
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
    
    
    public static String AfficherCategories(Utilisateur user) throws IOException {
	Vector<Categorie> res = new Vector<Categorie>();
	String resultat ="";
	    try {
		resultat=resultat+"===================<br>";
		resultat=resultat+"Listes des categories tarifaires<br>";
		res = BDCategories.getCategorie(user);
		if (res.isEmpty()) {
		    resultat=resultat+" Liste vide <br>";
		} else {
		    for (int i = 0; i < res.size(); i++) {
			resultat=resultat+res.elementAt(i).getCategorie() + " (prix : "
			+ res.elementAt(i).getPrix() + ")<br>";
		    }
		}
		resultat=resultat+"===================<br>";
	    } catch (CategorieException e) {
		resultat=resultat+" Erreur dans l'affichage des categories : "+ e.getMessage()+"<br>";
	    } catch (ExceptionConnexion e) {
		resultat=resultat+" Erreur dans l'affichage des categories : "+ e.getMessage()+"<br>";}
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
