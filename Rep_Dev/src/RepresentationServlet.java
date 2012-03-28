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

public class RepresentationServlet extends HttpServlet {

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
	System.out.println("in\n");
	String numS;

	ServletOutputStream out = res.getOutputStream();   
	
	res.setContentType("text/html");
	 out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	 out.println("<font color=\"#FFFFFF\"><h1> Lister les representation  </h1>");
	
	numS= req.getParameter("numS");
	if (numS == null) {
	    
	    out.println("<font color=\"#FFFFFF\">Veuillez saisir le numero de spectacle");
	    out.println("<P>");
	    out.print("<form action=\"");
	    out.print("RepresentationServlet\" ");
	    out.println("method=POST>");
	    out.println("Num&eacute;ro de spectacle :");
	    out.println("<input type=text size= 5 name=numS>");
	    out.println("<input type=submit>");
	    out.println("</form>");
	} else {	   
	    try{
		Utilisateur user = Utilitaires.Identification(this);
		out.println(Utilitaires.listerRepresentations(user,Integer.parseInt(numS)));
	    }
	    catch(Exception e){
		out.println("aaarrrrghhh<br>");
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
