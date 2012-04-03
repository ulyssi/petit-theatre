/*
 * @(#)NouvelleRepresentationServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import utils.Utilitaires;
import modele.Utilisateur;
import modele.Representation;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * NouvelleRepresentation Servlet.
 *
 * Cette servlet ajoute une representation dans la base
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class NouvelleRepresentationServlet extends HttpServlet {

    /**
     * HTTP GET request entry point.
     *
     * @param req	an HttpServletRequest object that contains the request 
     *			the client has made of the servlet
     * @param res	an HttpServletResponse object that contains the response 
     *			the servlet sends to the client
     *
     * @throws ServletException   if the request for the GET could not be handled
     * @throws IOException	 if an input or output error is detected 
     *				 when the servlet handles the GET request
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	String numS, jourS,moisS,anneeS, heureS;

	ServletOutputStream out = res.getOutputStream();   
	SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyyHH");
	res.setContentType("text/html");

	out.println("<HEAD><TITLE> Ajouter une nouvelle representation </TITLE></HEAD>");
	out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	out.println("<font color=\"#FFFFFF\"><h1> Ajouter une nouvelle repr&eacute;sentation </h1>");
	
	numS		= req.getParameter("numS");
	jourS		= req.getParameter("jour");
	moisS           = req.getParameter("mois");
	anneeS		= req.getParameter("annee");
	Date date=null;
	Representation rep=null;
	heureS	= req.getParameter("heure");
	if (numS != null && jourS != null && moisS!=null && anneeS!=null && heureS != null) {
	    try{
		date=sdf.parse(jourS+moisS+anneeS+anneeS);
		Calendar now=Calendar.getInstance();
		Calendar tmp=Calendar.getInstance();
		tmp.setTime(date);
		if(now.after(tmp))
		    {out.println("Erreur:la date est passée<br> ");
			date=null;
		}
	    }
	    catch (Exception e){out.println("Erreur: Format de date erron&eacute;<br> ");
		date=null;
	    }

	}
	
	   
    
	if(date==null){
	    out.println("<P>");
	    out.print("<form action=\"");
	    out.print("NouvelleRepresentationServlet\" ");
	    out.println("method=POST>");
	    out.println("Num&eacute;ro de spectacle :");
	    out.println("<input type=text size=20 name=numS>");
	    out.println("<br>");
	    out.println("                                  jj/mm/aaaa");
	    out.println("<br>");
	    out.println("Date de la repr&eacute;sentation :");
	    out.println("<input type=text size=1 name=jour>");
	    out.println("<input type=text size=1 name=mois>");
	    out.println("<input type=text size=2 name=annee>");
	    // out.println("<br>");
	    out.println("Heure de d&eacute;but de la repr&eacute;sentation :");
	    out.println("<input type=text size=20 name=heure>");
	    out.println("<br>");
	    out.println("<input type=submit>");
	    out.println("</form>");
	} else {
	  
		out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	    // TO DO
	    // Transformation des parametres vers les types adequats.
	    // Ajout de la nouvelle representation.
	    // Puis construction dynamique d'une page web de reponse.
	    
	    //'101', TO_DATE('06/11/2009 20h','DD/MM/YYYY HH24"h"')
	   
	    
	    try{
		Calendar now=Calendar.getInstance();
		 rep=new Representation(Integer.parseInt(numS),date);
		Utilisateur user = Utilitaires.Identification(this);
		out.println(Utilitaires.AjouterRepresentation(user,rep));
	    }
	    catch(Exception e){
		out.println(e.getMessage());
	    }
	  
	    //out.println("<p><i><font color=\"#FFFFFF\"> you entered n:"+numS+"  d: "+jourS+"/etc.."+" h: "+heureS+"</i></p>");
	 
	}

	out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/admin/admin.html\">Page d'administration</a></p>");
	out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Page d'accueil</a></p>");
	out.println("</BODY>");
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
        return "Ajoute une representation a une date donnee pour un spectacle existant";
    }

    private void addRep(int date){
	/*	try {
	    //    BDRepresentation.addRepresentation(,nom,prix);
	}
	catch (CategorieException e) {
	    IO.afficherln("Erreur dans l'ajout de la categorie" + e.getMessage());
	}
	catch (ExceptionConnexion e) {
	    IO.afficherln(" Erreur dans l'ajout de la categorie: " + e.getMessage());
	}
	*/
    }

}
