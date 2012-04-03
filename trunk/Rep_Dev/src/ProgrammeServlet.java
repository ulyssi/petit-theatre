/*
 * @(#)ProgrammeServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import java.util.Arrays;
import java.awt.Frame;


import utils.Constantes;
import utils.Utilitaires;
import modele.Utilisateur;
import modele.Categorie;
import modele.Spectacle;


import exceptions.ExceptionUtilisateur;
import exceptions.ExceptionConnexion;
import exceptions.CategorieException;
import java.io.File;

/**
 * Proramme Servlet.
 *
 * This servlet dynamically returns the theater program.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ProgrammeServlet extends HttpServlet {

   /**
    *	cette methode permet de Recuperation de la liste de tous les spectacles de la saison.
    * ent construisant une une page web decrivant ces spectacles.
    *
    *
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws ServletException   if the request for the GET could not be handled
    * @throws IOException	   if an input or output error is detected 
    *					   when the servlet handles the GET request
    */
   
  
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
        ServletOutputStream out = res.getOutputStream();   
	HttpSession session = req.getSession(true);
	res.setContentType("text/html");
	out.println("<HEAD><TITLE> Programme de la saison </TITLE></HEAD>");
	out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	out.println("<font color=\"#FFFFFF\"><h1> Programme de la saison </h1>");
	out.println("<FORM METHOD=POST>");	
	if(session.isNew()) {
	   
	    session.putValue("expand", null);
	    session.putValue("rslt", null);
	}

	int[] expand = (int[])session.getValue("expand");
	Vector<Spectacle> rslt = (Vector<Spectacle>)session.getValue("rslt");
	try{
	    Utilisateur user = Utilitaires.Identification(this);
	    

	    if(rslt==null){
		out.println("<p><i><font color=\"#FFFFFF\">actualy NEW</i></p>");

	
			
		rslt=Utilitaires.getProgramme(user);
		session.putValue("rslt", rslt);
	    	if (rslt.isEmpty()) {
		    out.println(" Liste vide <br>");
		} else {
		    expand= new int[rslt.size()];
		    Arrays.fill(expand,0); 
		    session.putValue("expand", expand);
		    for (int i = 0; i < rslt.size(); i++) {
			out.println("<INPUT TYPE=SUBMIT NAME=LR"+rslt.elementAt(i).getNum()+" VALUE="+"\"+\" >  "+rslt.elementAt(i).getNom()+ "<br>");
			
		    }
		}
	    }
	    else{
	
		for (int i = 0; i < rslt.size(); i++) {
		    if(req.getParameter("LR"+rslt.elementAt(i).getNum())!=null){
			expand[i]=1;
		    }
		    else if(req.getParameter("less"+rslt.elementAt(i).getNum())!=null){
			expand[i]=0;
		    }
		    if(expand[i]==0){
			out.println("<INPUT TYPE=SUBMIT NAME=LR"+rslt.elementAt(i).getNum()+" VALUE="+"\"+\" >"+rslt.elementAt(i).getNom()+ "<br>");
			
		    }
		    else{
			out.println("<INPUT TYPE=SUBMIT NAME=less"+rslt.elementAt(i).getNum()+" VALUE="+"\"-\" >"+rslt.elementAt(i).getNom()+ "<br>");	
			out.println(Utilitaires.listerRepresentations(user,rslt.elementAt(i).getNum()));
		    }
		}
	    }
	   
	}
    
	catch(Exception e){
	    out.println(e.getMessage());
	}
	out.println("</FORM></BODY>");	
	out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Accueil</a></p>");
	
	out.close();
	
    }

   /**
    * HTTP POST request entry point.
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws ServletException   if the request for the POST could not be handled
    * @throws IOException	   if an input or output error is detected 
    *					   when the servlet handles the POST request
    */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	doGet(req, res);
    }


   /**
    * Returns information about this servlet.
    *
    * @return String information about this servlet
    */
    
    public String getServletInfo() {
        return "Retourne le programme du theatre";
    }

}
