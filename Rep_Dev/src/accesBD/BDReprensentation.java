// package accesBD;

// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.Vector;


// import exceptions.ExceptionConnexion;

// import modele.Categorie;
// import modele.Utilisateur;

// public class BDCategories {

// 	public BDCategories () {
		
// 	}
// 	/**
// 	 * insert une nouvelle representation dans lesRepresentations 
// 	 * 
// 	 * @param Utilisateur
// 	 * @return String
// 	 * @throws CategorieException
// 	 * @throws ExceptionConnexion
// 	 */
//     public static String addRepresentation (int numS,date)
// 	throws CategorieException, ExceptionConnexion {
// 	    //insert into LESREPRESENTATIONS values ('101', TO_DATE('06/11/2009 20h','DD/MM/YYYY HH24"h"'));		
// 		String requete ;
// 		Statement stmt ;
// 		ResultSet rs ;
// 		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		
// 		requete = "insert into LesRepresentations values";
// 		try {
// 			stmt = conn.createStatement();
// 			rs = stmt.executeQuery(requete);
// 			while (rs.next()) {
// 				res.addElement(new Categorie (rs.getString(1), rs.getFloat(2)));
// 			}


// 		} catch (SQLException e) {
// 			throw new CategorieException (" Problème dans l'interrogation des catégories.."
// 					+ "Code Oracle " + e.getErrorCode()
// 					+ "Message " + e.getMessage());
// 		}
// 		BDConnexion.FermerTout(conn, stmt, rs);
// 		return res;
// 	}


    





// }
