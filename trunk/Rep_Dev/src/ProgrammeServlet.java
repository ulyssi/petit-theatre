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
    * HTTP GET request entry point.
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
	
	// TO DO
	// Recuperation de la liste de tous les spectacles de la saison.
	// Puis construction dynamique d'une page web decrivant ces spectacles.

	//	out.println("<p><i><font color=\"#FFFFFF\"> jkazjzajhazj</i></p>");

	out.println("<FORM METHOD=POST>");	
	if(session.isNew()) {
	    out.println("<p><i><font color=\"#FFFFFF\"> NEW</i></p>");
	    session.putValue("expand", null);
	    session.putValue("rslt", null);
	}
	else{
	    out.println("<p><i><font color=\"#FFFFFF\">Not NEW</i></p>");
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
		out.println("<p><i><font color=\"#FFFFFF\">really not NEW</i></p>");
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
/*

  import java.io.*;
  import javax.servlet.*;
  import javax.servlet.http.*;

  public class ProgrammeServlet extends HttpServlet
  {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();
      out.print("<HTML><HEAD><TITLE>Online Shop</TITLE>"+
                "</HEAD><BODY><FORM METHOD=POST>"+
                "<INPUT TYPE=SUBMIT NAME=foo VALUE="+
                "\"Put a FOO into the shopping cart\">"+
                "<INPUT TYPE=SUBMIT NAME=bar VALUE="+
                "\"Put a BAR into the shopping cart\">"+
                "<INPUT TYPE=SUBMIT NAME=see VALUE="+
               "\"See the shopping cart contents\">"+
                "<INPUT TYPE=SUBMIT NAME=buy VALUE="+
                "\"Buy the shopping cart contents\">"+
               "</FORM></BODY></HTML>");
      out.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException
    {
      String msg;

      HttpSession session = req.getSession(true);
      if(session.isNew())
      {
       session.putValue("foo", new int[] { 0 });
        session.putValue("bar", new int[] { 0 });
      }

      int[] foo = (int[])session.getValue("foo");
      int[] bar = (int[])session.getValue("bar");
      if(req.getParameter("foo") != null)
      {
        foo[0]++;
        msg = "Bought a FOO. You now have "+foo[0]+".";
      }
      else if(req.getParameter("bar") != null)
      {
        bar[0]++;
        msg = "Bought a BAR. You now have "+bar[0]+".";
      }
      else if(req.getParameter("buy") != null)
      {
        session.invalidate();
        msg = "Your order for "+foo[0]+" FOOs and "+bar[0]+
          " BARs has been accepted. Your shopping cart is empty now.";
      }
      else
      {
        msg = "You have "+foo[0]+" FOOs and "+bar[0]+
          " BARs in your shopping cart.";
      }

      res.setContentType("text/html");
      res.setHeader("pragma", "no-cache");
      PrintWriter out = res.getWriter();
      out.print("<HTML><HEAD><TITLE>Shopping Cart</TITLE></HEAD><BODY>");
      out.print(msg);
      out.print("<HR><A HREF=\"");
      out.print(req.getRequestURI());
      out.print("\">Back to the shop</A></BODY></HTML>");
      out.close();
    }

    public String getServletInfo()
    {
      return "ShoppingCartServlet 1.0 by Stefan Zeiger";
    }
  }
*/