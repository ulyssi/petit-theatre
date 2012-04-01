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


/**
 * NouvelleRepresentation Servlet.
 *
 * Cette servlet donne la liste des .
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class NoPlaceServlet extends HttpServlet {

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

	String numS;
	String  jourS,moisS,anneeS, heureS;

// Ecrire une nouvelle servlet qui permet de consulter l'ensemble des places 
// disponibles pour une représentation donnée, chaque place étant décrite par le
// numéro du rang et le numéro de place dans le rang. Intégrer cette servlet à 
// l'application et la tester.
	ServletOutputStream out = res.getOutputStream();   
	
	res.setContentType("text/html");
	 out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	 out.println("<font color=\"#FFFFFF\"><h1> Lister les places disponible pour une representation donne </h1>");
	
	numS= req.getParameter("numS");
	jourS= req.getParameter("jourS");
	moisS= req.getParameter("moisS");
	anneeS= req.getParameter("anneeS");
	heureS= req.getParameter("heureS");
	if (numS == null|jourS== null|moisS== null|anneeS== null| heureS== null) {
	    
	    out.println("<font color=\"#FFFFFF\">Veuillez saisir le numero de spectacle");
	    out.println("<P>");
	    out.print("<form action=\"");
	    out.print("RepresentationServlet\" ");
	    out.println("method=POST>");
	    out.println("Num&eacute;ro de spectacle :");
	    out.println("<input type=text size= 5 name=numS>");
	    out.println("Date de la repr&eacute;sentation :");
	    out.println("<input type=text size=1 name=jour>");
	    out.println("<input type=text size=1 name=mois>");
	    out.println("<input type=text size=2 name=annee>");
	   
	    out.println("Heure de d&eacute;but de la repr&eacute;sentation :");
	    out.println("<input type=text size=1 name=heure>");  
	    out.println("<input type=submit>");
	    out.println("</form>");
	    
	} else {	   
	    try{
		String date="";
		date=date+jourS+"/"+moisS+"/"+anneeS+" "+heureS+"h";
		Utilisateur user = Utilitaires.Identification(this);
		out.println(Utilitaires.ListerPlacesDispo(user,Integer.parseInt(numS),date));
	    }
	    catch(Exception e){
		
		out.println(e.getMessage());
	    }
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
        return "donne les representations associé à un spectacle existant";
    }


}
