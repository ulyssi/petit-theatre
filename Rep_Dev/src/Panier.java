

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



public class Panier extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	HttpSession session = req.getSession(true);

	ServletOutputStream out = res.getOutputStream();
	res.setContentType("text/html");
	
	try{
	    out.println("<HEAD><TITLE>Panier</TITLE></HEAD><BODY>");
	    out.println("<h1>Contenu du panier </h1>"); 
	    out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
	    
	    String nom = req.getParameter("nom");
	    String num = req.getParameter("num");
	    String date = req.getParameter("date");
	    out.println("<h1>Veuillez selectionner un place pour la representation"+nom +num+date+ ":</h1><br>");
  
	    if (nom != null&& num!=null&&date!=null) {
		if (session.getAttribute("session.PanierListe")!=null){ out.println("dejacookie!:<br>");
		    ((PanierListe)session.getAttribute("session.PanierListe")).Liste.add(new Item(new Spectacle (nom,new Integer(num)), new Representation(new Integer(num),new Date())));
		}		
		else{out.println("pascookie!:<br>");
		    //creation d'un nouveau panier 
		    PanierListe p =new PanierListe();
		    p.Liste.add(new Item(new Spectacle (new String(nom),new Integer(num)), new Representation(new Integer(num),new Date())));
		    session.setAttribute("session.PanierListe",p);		
		    
		
		}
	    }
	    //attention desormais le caddie est obligatoirement alloué
	    if(session.getAttribute("session.PanierListe")!=null){
		out.println("contenu du caddie:<br>");
		out.println(((PanierListe)session.getAttribute("session.PanierListe")).toString());
		out.println("<h1>Veuillez selectionner un place pour la representation :</h1><br>");
	    }
	    else 
		out.println("Le caddie est vide<br>");
	    
	    out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Accueil</a></p>");
	    out.close();
	    
	
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