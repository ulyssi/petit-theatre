import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import modele.*;
import modele.Place;
import utils.Utilitaires;
import modele.Utilisateur;


public class ResaSave extends HttpServlet {
    /**
     * Permet de repondre a une requete web utilise un acces a la bd pour verifier et 
     * Ensuite valider le pannier affiche un feedback direct a l'utilisateur poru lui 
     * assurer la validation de la reservation des places 
     * @param HttpServletRequest request requete 
     * @param HttpServletResponse response réponse
     * @throw IOException, ServletException
     * @return void 
     */

    public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

	HttpSession session = req.getSession(true);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Log</title>");
        out.println("</head>");
        out.println("<body>");
	String nom = req.getParameter("nom");
	String delog = req.getParameter("delog");
	
	PanierListe p;
	if(session.getAttribute("session.log")==null){
	    String name = "";
	    
	    if(nom!=null){
		name=nom;
		//in base load 
		session.setAttribute("session.log",nom);
		try{
		    Utilisateur user = Utilitaires.Identification(this);
		    out.println(" <br>");
		    
		    p=Utilitaires.getPanier(user,nom);
		    if(p==null){
			out.println("Probleme accès BD");
			return;
		    }
		
		    if(p.Liste.size()==0)
			out.println("PANIER VIDE vous etes desormais loggé");
		    else {
			session.setAttribute("session.PanierListe",p); 
			out.println("OK! panier charge");
		    }  
	        }
		
		catch (Exception e){}

		    
	    }
	    else 
		{
		    out.println("<P>");
		    out.print("<form action=\"");
		    out.print("ResaSave\" ");
		    out.println("method=POST>");
		    out.println("login");
		    out.println("<input type=text size=20 name=nom>");
		    out.println("<br>");
		    
		
		}
	    
	}
	else {// deja loggue
	    if (delog==null){
		out.println("vous etes deja logge sous le nom "+(String)session.getAttribute("session.log"));
		out.println("<P>");
		out.println("<form class=\"link\" action=ResaSave \"method=POST>\n");
		out.println("<button type=\"submit\"name = \"delog\" value=\"delog\">Se delogger</button>\n");
		out.println("</form>");
		out.println("<br>");}
	    else{ 
		session.setAttribute("session.PanierListe",null);
		session.setAttribute("session.log",null);
		out.println("session terminee");
	    }
	    
	}
	
	
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}