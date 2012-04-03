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
import modele.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

/**
 * NouvelleRepresentation Servlet.
 *
 * Cette Servlet permet de selectionner un Spectacle pour permmetre ensuite 
 * de faire des reservations.
 * @author ulysse cadour
 * @version
 */

public class RepresentationServlet extends HttpServlet {
    /**
     * Permet de répondre à une requête web En affichant la liste des Spectacles et representations : 
     * Utiliste JQuery javascript pour la mise en forme
     * @param HttpServletRequest request requete 
     * @param HttpServletResponse response réponse
     * @throw IOException, ServletException
     * @return void 
     */

    public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	//Get the session object
	HttpSession session = req.getSession(true);
	//Get the output stream
	ServletOutputStream out = res.getOutputStream();
	res.setContentType("text/html");
	out.println("<HEAD><TITLE>Reservation de tickets </TITLE></HEAD><BODY>");
	out.println("<h1> Reservations de tickets </h1>"); 
	out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	out.println("<p align=\"Right\"><font face=\"Monotype Corsiva\"style=\"font-size: 16pt\">");
	try{
	    // Open the file that is the first 
	    // command line parameter
	    String relativeWebPath ="/WEB-INF/files/JAVASCRIPTPROG.txt";
	    String absoluteDiskPath = this.getServletContext().getRealPath(relativeWebPath);
	    File file = new File(absoluteDiskPath);
	    FileInputStream fstream = new FileInputStream(file);
	    // Get the object of DataInputStream
	    DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    //Read File Line By Line
	    while ((strLine = br.readLine()) != null)   {
		// Print the content on the console
		out.println (strLine);
	    }
	    //Close the input stream
	    in.close();
	}catch (Exception e){//Catch exception if any
	    out.println("Error: " + e.getMessage());
	}
	if(session.isNew()||session.getAttribute("session.PanierListe")==null)
	    out.println("<a href=\"admin/admin.html\">Caddie (vide)</a></font><br></p>");
	else if(session.getAttribute("session.PanierListe")!=null)
	    if(((PanierListe)session.getAttribute("session.PanierListe")).getSize()>0)
		out.println("<a href=\"admin/admin.html\">afficher caddie("+((PanierListe)session.getAttribute("session.PanierListe")).Liste.size()+"Representations dans le panier)"+"</a></font><br></p>");
	try{
	    Utilisateur user = Utilitaires.Identification(this);
	    out.println(Utilitaires.AffichageAchat(user));
	}
	catch(Exception e){
	    out.println(e.getMessage());
	}
	out.println("</BODY>");
	out.close();
    }

    public String getServletInfo() {
        return "Reservation servlet";
    }
    /**
     * HTTP POST request entry point. Renvoie sur la méthode do get de cette meme classe 
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

}
