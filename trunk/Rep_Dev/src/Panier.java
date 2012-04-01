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



public class Panier extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	ServletOutputStream out = res.getOutputStream();
	res.setContentType("text/html");
	
	try{
	    out.println("<HEAD><TITLE>Panier</TITLE></HEAD><BODY>");
	    out.println("<h1>Contenu du panier </h1>"); 
	    out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	    out.println("<p align=\"Right\"><font face=\"Monotype Corsiva\"style=\"font-size: 16pt\">");
	    String firstName = req.getParameter("firstname");
	    String lastName= req.getParameter("lastname");
	    if (firstName != null) {
		out.println("<h1>La representation :" + firstName  + " a bien ete ajoute au panier!</h1><br>");
		
	    }	    
	    out.println("<h1>Veuillez selectionner un place pour la representation :</h1><br>");
	    Utilisateur user = Utilitaires.Identification(this);
	    out.println(Utilitaires.ListerPlacesDispo(user,101,firstName));
	}catch (Exception e){//Catch exception if any
	    out.println("Error: " + e.getMessage());
	}
    }
    public String getServletInfo() {
        return "Reservation servlet";
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }


}