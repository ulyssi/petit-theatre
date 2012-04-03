import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import modele.*;
import modele.Place;
import utils.Utilitaires;
import modele.Utilisateur;


public class Validate extends HttpServlet {
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
        out.println("<title>Valider le caddie</title>");
        out.println("</head>");
        out.println("<body>");
	String valid = req.getParameter("valide");

	if(valid!=null){
	    out.println("Reservation valide<br>");
	    if (valid.equals("valide")){
		// effectuer la validation sur le caddie 
		if(session.getAttribute("session.PanierListe")!=null){
		    if(((PanierListe)session.getAttribute("session.PanierListe")).contientPlace()){
			try{
			    Utilisateur user = Utilitaires.Identification(this);	
			    out.println(Utilitaires.ValiderPanier(user,(PanierListe)session.getAttribute("session.PanierListe")));
			}
			catch (Exception e ){
			    out.println("probleme de connexion a la base de donnee");
			}
		    }
		    else 
			out.println("panierVide");
		}
		else
		    out.println("Panier Non trouvé");
		
	    }
	}
	else{
	    out.println("Contenu du caddie<br>");
	    if(session.getAttribute("session.PanierListe")!=null){
		out.println("cookie ok");
		out.println(((PanierListe)session.getAttribute("session.PanierListe")).toString());
		out.println("<form class=\"link\" action=Validate \"method=POST>\n");
		out.println("<button name=\"valide\" value =\"valide\"type=\"submit\">Valider</button>\n");
		out.println("</form>");	
	    }
	    else
		out.println("le panier est vide");
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