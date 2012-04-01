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

import java.io.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

/**
 * NouvelleRepresentation Servlet.
 *
 * Cette servlet donne la liste des .
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class RepresentationServlet extends HttpServlet {
    /* SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
     * THIS SOFTWARE OR ITS DERIVATIVES.
     *
     * CopyrightVersion 1.0
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
	if(session.isNew()||((ArrayList)session.getAttribute("sessiontest.list"))!=null&&((ArrayList)session.getAttribute("sessiontest.list")).size()>0)
	    out.println("<a href=\"admin/admin.html\">afficher caddie (vide)</a></font><br></p>");
	else
	    out.println("<a href=\"admin/admin.html\">afficher caddie("+((ArrayList)session.getAttribute("sessiontest.list")).size()+"Places)"+"</a></font><br></p>");
	out.println("<br>Session ID: " + req.getRequestedSessionId());
	out.println("New Session: " + session.isNew()+"<br>");
	session.setAttribute("sessiontest.list", new ArrayList<String>());
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

}
