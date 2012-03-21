/*
 * @(#)NouvelleRepresentationServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * NouvelleRepresentation Servlet.
 *
 * This servlet dynamically adds a new date a show.
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
	  String numS, dateS, heureS;
          ServletOutputStream out = res.getOutputStream();   

	  res.setContentType("text/html");

	  out.println("<HEAD><TITLE> Ajouter une nouvelle repr�sentation </TITLE></HEAD>");
	  out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	  out.println("<font color=\"#FFFFFF\"><h1> Ajouter une nouvelle repr&eacute;sentation </h1>");

	  numS		= req.getParameter("numS");
	  dateS		= req.getParameter("date");
	  heureS	= req.getParameter("heure");
	  if (numS == null || dateS == null || heureS == null) {
            	out.println("<font color=\"#FFFFFF\">Veuillez saisir les informations relatives &agrave; la nouvelle repr&eacute;sentation :");
            	out.println("<P>");
            	out.print("<form action=\"");
            	out.print("NouvelleRepresentationServlet\" ");
            	out.println("method=POST>");
            	out.println("Num&eacute;ro de spectacle :");
            	out.println("<input type=text size=20 name=numS>");
            	out.println("<br>");
            	out.println("Date de la repr&eacute;sentation :");
            	out.println("<input type=text size=20 name=date>");
            	out.println("<br>");
            	out.println("Heure de d&eacute;but de la repr&eacute;sentation :");
            	out.println("<input type=text size=20 name=heure>");
            	out.println("<br>");
            	out.println("<input type=submit>");
            	out.println("</form>");
	  } else {
	  	// TO DO
		// Transformation des param�tres vers les types ad�quats.
	  	// Ajout de la nouvelle repr�sentation.
	  	// Puis construction dynamique d'une page web de r�ponse.
	  	out.println("<p><i><font color=\"#FFFFFF\">A compl&eacute;ter</i></p>");
	  	out.println("<p><i><font color=\"#FFFFFF\">...</i></p>");
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
        return "Ajoute une repr�sentation � une date donn�e pour un spectacle existant";
    }

}
