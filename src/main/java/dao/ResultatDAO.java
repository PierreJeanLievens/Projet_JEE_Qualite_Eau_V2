package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Resultat;
/**
 * Class ResultatDAO
 * @author PIERRE-JEAN
 *
 */
public class ResultatDAO {
    
	 private Connection connection;
	 	/**
	 	 * Constructor with a connection 
	 	 * @param connection
	 	 */
	    public ResultatDAO(Connection connection) {
	        this.connection = connection;
	    }
	/**
	 * Method to get a list of resultats linked to the prelevement with reference_prelevement
	 * @param reference_prelevement
	 * @return
	 * @throws SQLException
	 */
    public ArrayList<Resultat> getAllResultats(String reference_prelevement) throws SQLException {
    	// create an empty list of Resultat
        ArrayList<Resultat> resultats = new ArrayList<>();
        // prepare the query and prevent SQL injection, we search only TEAU and PH
        String query = "SELECT *\r\n"
        		+ "FROM resultat\r\n"
        		+ "WHERE reference_prelevement = ? AND (cd_sise = 'TEAU' OR cd_sise = 'PH');";

        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reference_prelevement);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	// create a new Resultat
                Resultat resultat = new Resultat(
                    resultSet.getString("reference_prelevement"),
                    resultSet.getString("cd_sise"),
                    resultSet.getString("cd_parametre"),
                    resultSet.getString("parametre"),
                    resultSet.getBoolean("qual_parametre"),
                    resultSet.getString("valeur_string"),
                    resultSet.getString("unite"),
                    resultSet.getString("limite_qual"),
                    resultSet.getDouble("valeur_nombre")
                );
                // add the current Resultat to the list
                resultats.add(resultat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the list of resultats
        return resultats;
    }

    
    // Vous pouvez ajouter d'autres méthodes (addResultat, updateResultat, deleteResultat) ici
}
