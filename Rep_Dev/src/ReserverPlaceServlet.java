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
 * Cette servlet pernet de reserver une place selon le numero de zone
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ReserverPlaceServlet extends HttpServlet {

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

	HttpSession session = req.getSession(true);
	String numS, jourS,moisS,anneeS, heureS;
	String numZ;
	ServletOutputStream out = res.getOutputStream();   
	SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyyHH");
	res.setContentType("text/html");

	out.println("<HEAD><TITLE> Ajouter une nouvelle representation </TITLE></HEAD>");
	out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	out.println("<font color=\"#FFFFFF\"><h1> Ajouter une nouvelle repr&eacute;sentation </h1>");

	numZ=(String)session.getValue("numZ");
	numZ            =req.getParameter("numZ");
	numS		= req.getParameter("numS");
	jourS		= req.getParameter("jour");
	moisS           = req.getParameter("mois");
	anneeS		= req.getParameter("annee");

	Date date=null;
	Representation rep=null;
	int noSpec=0;
	int noZone=0;
	heureS	= req.getParameter("heure");

	rep=(Representation)session.getValue("rep");

	if (numS != null && jourS != null && moisS!=null && anneeS!=null && heureS != null) {
	    try{
	
		noSpec=Integer.parseInt(numS);
		date=sdf.parse(jourS+moisS+anneeS+anneeS);
		Calendar now=Calendar.getInstance();
		Calendar tmp=Calendar.getInstance();
		tmp.setTime(date);
		if(now.after(tmp)){
		    out.println("Erreur:la date est passée<br> ");
		    out.println("Erreur: le num&eacute;ro de spectacle doit etre donne par un entier<br> ");
		    date=null;
		}
		else{
		rep= new Representation(noSpec,date);
		session.putValue("rep",rep);
		
		}
	    }
	    catch(NumberFormatException e){
		out.println("Erreur: le num&eacute;ro de spectacle doit etre donne par un entier<br> ");
		date=null;
	    }
	    catch (ParseException e){out.println("Erreur: Format de date erron&eacute;<br> ");
		date=null;
	    }
	    try{
		noZone=Integer.parseInt(numZ);	
	    }
	    catch(NumberFormatException e){
		out.println("Erreur: le num&eacute;ro de zone doit etre donne par un entier<br> ");
		numZ=null;
	    }
	}
	
	
	   
    
	if(rep==null){
	    out.println("<P>");
	    out.print("<form action=\"");
	    out.print("ReserverPlaceServlet\" ");
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
	   
	    out.println("Heure de d&eacute;but de la repr&eacute;sentation :");
	    out.println("<input type=text size=20 name=heure>");
	    out.println("<br>");
	    out.println("<input type=submit>");
	    out.println("</form>");
	} 
	if(numZ==null){
	    out.println("<form action=\"");
	    out.println("ReserverPlaceServlet\" ");
	    out.println("num&eacute;ro de zone");
	    out.println("method=POST>");
	    out.println("<input type=text size=1 name=numZ>");
	    
	}
	else{
	    session.putValue("numZ",numZ);
	}
	if(numZ!=null&&date!=null) {
	  
	    out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	    session.putValue("numZ",null);
	    session.putValue("",null);
	    try{
		Calendar now=Calendar.getInstance();
		 rep=new Representation(Integer.parseInt(numS),date);
		Utilisateur user = Utilitaires.Identification(this);
		out.println(Utilitaires.getPlaceZone(user,rep,noZone));
		
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
