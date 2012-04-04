import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import utils.Utilitaires;
import modele.Utilisateur;
import modele.Representation;
import modele.Spectacle;
import modele.Place;
import modele.Item;
import modele.PanierListe;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import accesBD.BDProgramme;

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
	String daterep;
	String numS;
	String numZ;
	ServletOutputStream out = res.getOutputStream();   
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH'h'");
	res.setContentType("text/html");

	out.println("<HEAD><TITLE> reserver une place dans une zone </TITLE></HEAD>");
	out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	out.println("<font color=\"#FFFFFF\"><h1> reserver un place dans une zone </h1>");

	numZ=(String)session.getValue("numZ");
		
	if(numZ==null)
	    numZ =req.getParameter("numZ");
	daterep=req.getParameter("daterep");
	numS=req.getParameter("numS");
	
	Date date=null;
	Representation rep=null;
	int noSpec=0;
	int noZone=0;


	rep=(Representation)session.getValue("rep");

	if (daterep!=null&&numS!=null) {
	    try{
	
		noSpec=Integer.parseInt(numS);
		date=sdf.parse(daterep);
		Calendar now=Calendar.getInstance();
		Calendar tmp=Calendar.getInstance();
		tmp.setTime(date);
		if(now.after(tmp)){
		    out.println("Erreur:la date est passée<br> ");
		    
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
	}
	if(numZ!=null){
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
	    out.println("<input type=text size=4 name=numS>");
	    out.println("<br>");
	    out.println("                                  jj/mm/aaaa HH");
	    out.println("<br>");
	    out.println("Date de la repr&eacute;sentation :");
	    out.println("<input type=text size=13 name=daterep>");
	   
	    out.println("<br>");
	   
	} 
	if(numZ==null){

	    if(rep!=null){
		out.print("<form action=\"");
		out.print("ReserverPlaceServlet\" ");
	    }
	    out.println("num&eacute;ro de zone <br>");
	
	    out.println("<input type=text size=1 name=numZ>");
	}
	else{
	    
	    session.putValue("numZ",numZ);
	}
	
	
	if(numZ!=null&&rep!=null) {
	  
	    out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	    session.putValue("numZ",null);
	    session.putValue("rep",null);
	    try{
	
		if(noSpec==0)
		    noSpec=rep.getNum();
		Utilisateur user = Utilitaires.Identification(this);
		Place P=BDProgramme.getPlaceZone(user,rep,noZone);
		String nom= BDProgramme.getName(user,noSpec);
		
		if(P==null)
		    out.println("<p><i><font color=\"#FFFFFF\">aucune place trouv&eacute</i></p>");
		else{
		    out.println("nom: "+nom+" "+P.toString()+"<br>");


		    if (session.getAttribute("session.PanierListe")!=null){ 
		    	//utilisateur loggé
		    	if(!((PanierListe)session.getAttribute("session.PanierListe")).In(noSpec,rep.toString()))
		    	    ((PanierListe)session.getAttribute("session.PanierListe")).Liste.add(new Item(new Spectacle (nom,noSpec), rep));
			
		    	((PanierListe)session.getAttribute("session.PanierListe")).addPlace(Integer.toString(noSpec),rep.toString(),Integer.toString(P.getNoPlace()),Integer.toString(P.getNoRang()));
			
		    	if((String)session.getAttribute("session.log")!=null){
			    
		    	    out.println(Utilitaires.enregistrerPlacePanier(user,(String)session.getAttribute("session.log"),Integer.toString(noSpec),rep.toString(),Integer.toString(P.getNoPlace()),Integer.toString(P.getNoRang())));


		    	}
						
		    }
		    else{
		    	PanierListe p =new PanierListe();	
		    	p.Liste.add(new Item(new Spectacle (new String(nom),noSpec), rep));
		    	session.setAttribute("session.PanierListe",p);		
		    	((PanierListe)session.getAttribute("session.PanierListe")).addPlace(Integer.toString(noSpec),rep.toString(),Integer.toString(P.getNoPlace()),Integer.toString(P.getNoRang()));

		    	if((String)session.getAttribute("session.log")!=null){
		    	    out.println(Utilitaires.enregistrerPlacePanier(user,(String)session.getAttribute("session.log"),Integer.toString(noSpec),rep.toString(),Integer.toString(P.getNoPlace()),Integer.toString(P.getNoRang())));

		    	}
		    }
		}
	    }
	    catch(Exception e){
		out.println(e.getMessage());
	    }
	    
	    //  out.println("<p><i><font color=\"#FFFFFF\"> you entered n:"+rep+"</i></p>");
	    
	}
	else{
	    out.println("<input type=submit>");
	    out.println("</form>");
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
