import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import utils.Utilitaires;
import modele.Utilisateur;
import modele.Representation;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.text.ParseException;
import java.lang.NumberFormatException;

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
     * Cette methode permet d'ajouter une nouvelle representation dans la base a l'aide d'une page HTML.
     * Utilise un Utilisateur et realise des operations sur la base a l'aide des classes d'acces a la bd dependance implicite.
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
	String numS, daterep;

	ServletOutputStream out = res.getOutputStream();   
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH'h'");
	res.setContentType("text/html");

	out.println("<HEAD><TITLE> Ajouter une nouvelle representation </TITLE></HEAD>");
	out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	out.println("<font color=\"#FFFFFF\"><h1> Ajouter une nouvelle repr&eacute;sentation </h1>");
	
	numS		= req.getParameter("numS");
	daterep		= req.getParameter("daterep");
	

	
	
	Date date=null;
	Representation R=null;
       	int noSpec;

	if (numS != null && daterep!=null) {
	    try{
		
		noSpec=Integer.parseInt(numS);
		date=sdf.parse(daterep);
		Calendar now=Calendar.getInstance();
		Calendar tmp=Calendar.getInstance();
		tmp.setTime(date);
		if(now.after(tmp))
		    {out.println("Erreur:la date est passée<br> ");
			out.println("Erreur: le num&eacute;ro de spectacle doit etre donne par un entier<br> ");
			date=null;
		}
	    }
	    catch(NumberFormatException e){
		out.println("Erreur: le num&eacute;ro de spectacle doit etre donne par un entier<br> ");
		date=null;
	    }
	    catch (ParseException e){out.println("Erreur: Format de date erron&eacute;<br> ");
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
	    out.println("                                  jj/mm/aaaa HHh");
	    out.println("<br>");
	    out.println("Date de la repr&eacute;sentation :");
	    out.println("<input type=text size=13 name=daterep>");
	   
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
		 R=new Representation(Integer.parseInt(numS),date);
		Utilisateur user = Utilitaires.Identification(this);
		out.println(Utilitaires.AjouterRepresentation(user,R));
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


}
