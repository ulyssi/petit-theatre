import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import modele.*;
/**
 * Classe dedition du panier
 * @author Cadour ulysse Penkler Alexandre
 * @version 2.6
 */

public class EditPanier extends HttpServlet {
    /**
     * Permet de répondre à une requête web
     * @param HttpServletRequest request requete 
     * @param HttpServletResponse response réponse
     * @throw IOException, ServletException
     * @return void 
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException
    {
	HttpSession session = req.getSession(true);
	
	res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Edition du panier</title>");
        out.println("</head>");
        out.println("<body>");
	out.println("Edition du panier<br>");
	out.println("contenu actuel du panier:<br>");
		    
	
	String num = req.getParameter("num");
	String date = req.getParameter("date");
	String place2 = req.getParameter("place");
	String rang = req.getParameter("rang");
       
	
	
	if(session.getAttribute("session.PanierListe")!=null){
	    
	    PanierListe p =(PanierListe)session.getAttribute("session.PanierListe");
	   
	    if(num!=null&&date!=null&&place2!=null&&rang!=null){
		out.println(num+date+place2+rang);
		int i=0, j=0,ti=-1,tj=-1;
		for(Item item : p.Liste)
		    {
			for(Place place : item.lesPlaces){
			    out.println("recherche");
			    if(item.representation.toString().equals(date) && item.representation.getNum()==new Integer(num)&&place.getNoPlace()==new Integer(place2)&&place.getNoRang()==new Integer(rang)){
				out.println("trouve");
				tj=j; ti=i;
				//item.lesPlaces.remove(item.lesPlaces.lastIndexOf(item));
			    }
			    j++;
			}
			j=0;
			i++;
		    }
		if(ti!=-1&&tj!=-1)
		    p.Liste.get(ti).lesPlaces.remove(tj);
		
	    }
		
	    
	    for(Item item : p.Liste)
		for(Place place : item.lesPlaces){
			out.println("Place (n,rang) : ("+place.getNoPlace()+","+place.getNoRang()+") Spectacle n"+item.representation.getNum()+" date "+item.representation);
			out.println("<form class=\"link\" action=EditPanier \"method=POST>\n");
			out.println("<input type=\"hidden\" name=\"num\" value=\""+item.representation.getNum()+"\" />\n");
			out.println("<input type=\"hidden\" name=\"date\" value=\""+item.representation.toString()+"\" />\n");
			out.println("<input type=\"hidden\" name=\"rang\" value=\""+place.getNoRang()+"\" />\n");
			out.println("<input type=\"hidden\" name=\"place\" value=\""+place.getNoPlace()+"\" />\n");
			out.println("<button type=\"submit\"name = \"delete\" value=\"delete\">Supprimer</button>\n");
			out.println("</form>");
			out.println("<br>");
		}
	
	}
	else
	    out.println("Vous ne possedez pas de panier<br>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}